package application;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.TableElement;
import utilities.Notification;

/**
 * This class represent the winery (Cantina, Enoteca). The winery can add
 * employees to his list (so hire them) and has all the protected and private
 * methods to modify the system.
 * 
 * @author Dario Cavalli
 *
 */

@Entity
@Table(name="Winery")
public class Winery {
	@Id
	@Column(name="name", length=100)
	private String name;
	@Transient
	private TreeMap<String, LinkedList<Bottle>> bottleList;
	@Transient
	private Set<Notification> notifyList;
	@Transient
	private List<User> userList;
	@Transient
	private List<Employee> employeeList;
	@Transient
	private ObservableList<TableElement> toSendList;
	@ElementCollection (fetch=FetchType.EAGER)
	@CollectionTable(name="WineToAdd", joinColumns=@JoinColumn(name="name"))
	@Column(name="wineToAdd")
	private Set<String> wineToAddSet;
	@Transient
	private static SessionFactory sessionFactory;


	@SuppressWarnings("unused")
	private Winery() {}
	
	public Winery(String name) {
		this.name = name;
		bottleList = new TreeMap<String, LinkedList<Bottle>>();
		notifyList = new HashSet<Notification>();
		userList = new LinkedList<User>();
		employeeList = new LinkedList<Employee>();
		wineToAddSet = new HashSet<>();
		toSendList = FXCollections.observableArrayList();
		setSessionFactory();
		databaseInitializeLists();
	}

	public void setSessionFactory()
	{
		Winery.sessionFactory = Main.getSessionFactory();
	}
	/**
	 * Through this method the winery can hire an employee.
	 * 
	 * @param employee       The employee you want to add.
	 * @param identification The identification code, decided by the winery, to
	 *                       classify the employee.
	 */
	public void addEmployee(Employee employee) {
		employee.hire(this);
		employeeList.add(employee);
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected void print(String name, int year) {
		System.out.println("\nEcco i risultati della tua ricerca: ");

		if (!name.equals("")) {
			if (year != 0) {
				bottleList.get(name).stream().filter(x -> x.getYear() == (year)).distinct().forEach(x -> {
					System.out.println(x.getName() + " " + x.getYear() + " " + x.getFeatures() + " " + x.getVines());
				});
			} else {
				bottleList.get(name).stream().distinct().forEach(x -> {
					System.out.println(x.getName() + " " + x.getYear() + " " + x.getFeatures() + " " + x.getVines());
				});
			}
		} else {
			for (LinkedList<Bottle> ll : bottleList.values()) {
				ll.stream().distinct().forEach(x -> {
					System.out.println(x.getName() + " " + x.getYear() + " " + x.getFeatures() + " " + x.getVines());
				});
			}
		}
		System.out.println();
	}
	
	private void addWineToList(List<Bottle> wines) {
		for (Bottle b : wines) {
			if (!bottleList.containsKey(b.getName())) {
				bottleList.put(b.getName(), new LinkedList<Bottle>());
				bottleList.get(b.getName()).add(b);
			} else {
				bottleList.get(b.getName()).add(b);
			}
		}
	}
	
	protected void addWine(Bottle[] wines) {
		for (Bottle b : wines) {
			if (!bottleList.containsKey(b.getName())) {
				bottleList.put(b.getName(), new LinkedList<Bottle>());
				bottleList.get(b.getName()).add(b);
				databaseAddBottle(b);
			} else {
				bottleList.get(b.getName()).add(b);
				databaseAddBottle(b);
			}

			for (Notification n : notifyList.stream().filter(x -> x.getData().equals(b.getName() + b.getYear()))
					.collect(Collectors.toList())) {
				Notify(n);
			}
			for (String e : wineToAddSet.stream().filter(x -> x.equals(b.getName())).collect(Collectors.toList())) {
				databaseRemoveToAddBottle(e);
			}
		}
	}

	/**
	 * The method try to remove the bottle of wine asked by the user. If the bottle
	 * isn't available, it adds the mail of the user to notifyList, to notify the
	 * customer once the wine is again in the catalog.
	 * 
	 * @param name The name of the bottle the user want to buy.
	 * @param year The year of the bottle the user want to buy.
	 * @param mail The mail of the user.
	 * @return true if the bottle is present, and remove it, false otherwise.
	 */
	protected boolean removeBottle(String name, int year, String mail) {

		if (bottleList.containsKey(name)) {
			Bottle b = bottleList.get(name).stream().filter(x -> x.getYear() == year).findAny().orElse(null);
			if (b != null) {
				bottleList.get(name).remove(b);
				System.out.println("L'utente " + mail + " ha comprato una bottiglia di " + name + " del " + year + ".");
				databaseRemoveBottle(b, mail);
				checkLast(name);
				TableElement tbEl = new TableElement(mail, name, year, 1);
				if (!toSendList.contains(tbEl))
					toSendList.add(tbEl);
				else {
					TableElement tb = toSendList.get(toSendList.indexOf(tbEl));
					tb.setQuantity(tb.getQuantity() + tbEl.getQuantity());
				}
				databaseAddToSendBottle(toSendList.get(toSendList.indexOf(tbEl)));
				return true;
			} else {
				System.out.println("Vino non disponibile! Ti notificheremo una volta che sara' tornato in lista!");
				Notification notf = new Notification(mail, name, year);
				notifyList.add(notf);
				databaseAddNotification(notf);
				return false;
			}
		} else {
			System.out.println("Vino non presente nel catalogo!");
			return false;
		}
	}

	protected boolean addUser(User user, String mail, String password) {
		if (!controlUserData(mail, password))
			return false;

		if (!userList.contains(user)) {
			userList.add(user);
			databaseAddUser(user);
			System.out.println("Utente " + mail + " registrato nel sistema.");

			return true;
		} else {
			System.out.println("L'utente e' gia' presente!");
			return false;
		}
	}

	/**
	 * Called by the method addWine, it tries to search through the userList if the
	 * winery has to notify someone of the arrival of the wine.
	 * 
	 * @param n One object in the list notifyList.
	 * @throws NullPointerExceptions if an error has occurred in the previous
	 *                               methods.
	 */
	private void Notify(Notification n) {
		try {
			User us = userList.stream().filter(x -> x.getMail().equals(n.getMail())).findAny().orElse(null);
			userList.get(userList.indexOf(us)).notifications(n.getWine_name(), n.getWine_year());
			notifyList.remove(n);
			databaseRemoveNotification(n);
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}

	private void checkLast(String name) {
		if (bottleList.get(name).isEmpty())
			databaseAddToAddBottle(name);
	}

	private boolean controlUserData(String mail, String password) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
		Matcher matcher = pattern.matcher(mail);
		if (!matcher.matches()) {
			System.out.println("Mail " + mail + " non valida!");
			return false;
		}

		if (password.length() < 4) {
			System.out.println("Password non valida! Minimo 4 caratteri!");
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	// ---------------------------------------------------------------------- Da qui
	// in poi metodi solo per progetto FX

	protected boolean isInCatalogue(String name) {
		return bottleList.containsKey(name);
	}

	protected ObservableList<Bottle> searchWines(String name, int year) {
		List<Bottle> list_temp = new ArrayList<>();
		ObservableList<Bottle> obslist_temp = FXCollections.observableList(list_temp);
		if (!name.equals("")) {
			if (year != 0) {
				bottleList.get(name).stream().filter(x -> x.getYear() == (year)).forEach(x -> {
					obslist_temp.add(x);
				});
			} else {
				bottleList.get(name).stream().forEach(x -> {
					obslist_temp.add(x);
				});
			}
		} else {
			if (year != 0) {
				for (LinkedList<Bottle> ll : bottleList.values()) {
					ll.stream().filter(x -> x.getYear() == (year)).forEach(x -> {
						obslist_temp.add(x);
					});
				}
			} else {
				for (LinkedList<Bottle> ll : bottleList.values()) {
					ll.stream().forEach(x -> {
						obslist_temp.add(x);
					});
				}
			}
		}

		return obslist_temp;
	}

	protected boolean logIn(String mail, String password) {
		User user_test = new User(mail, password, this);
		if (!userList.contains(user_test))
			return false;

		User us = userList.get(userList.indexOf(user_test));
		if (!us.getPassword().equals(user_test.getPassword()))
			return false;

		return true;
	}

	protected String subscribeUser(String mail, String password) {
		String response = controlUserDataFX(mail, password);

		if (response.length() != 0)
			return response;

		User user_test = new User(mail, password, this);
		if (!userList.contains(user_test)) {
			userList.add(user_test);
			databaseAddUser(user_test);

			System.out.println("Utente " + mail + " registrato nel sistema.");
			return "Registrazione avvenuta!";
		} else
			return "Email gia' presente!";
	}

	private String controlUserDataFX(String mail, String password) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
		Matcher matcher = pattern.matcher(mail);
		if (!matcher.matches())
			return "Mail non valida!";

		if (password.length() < 4)
			return "Password troppo corta!";

		return "";
	}

	public boolean authenticationEmployee(String code) {
		Employee employee_test = new Employee(code, this);
		if (!employeeList.contains(employee_test))
			return false;

		return true;
	}

	protected User getUser(String mail, String password) {
		User user_temp = new User(mail, password, this);
		return userList.get(userList.indexOf(user_temp));
	}

	protected Employee getEmployee(String code) {
		Employee employee_temp = new Employee(code, this);
		return employeeList.get(employeeList.indexOf(employee_temp));
	}

	protected Set<String> getWineToAddSet() {
		return wineToAddSet;
	}

	protected void setWineToAddSet(Set<String> wineToAddSet) {
		this.wineToAddSet = wineToAddSet;
	}

	protected ObservableList<TableElement> getToSendList() {
		return toSendList;
	}

	public void sendBottle(ObservableList<TableElement> obs_temp) {
		databaseRemoveToSendBottle(obs_temp);
		toSendList.removeAll(obs_temp);
	}

	// ---------------------------------------------------------------------- Da qui
	// in poi metodi solo per persistenza

	public boolean databaseAddUser(User user) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.saveOrUpdate(user);

			transaction.commit();
			return true;
		} catch (final HibernateException hex) {

			transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}
	
	public boolean databaseAddBottle(Bottle b) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.save(b);			

			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean databaseRemoveBottle(Bottle b, String mail) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			Query query;
			try {
			query = session.createQuery("from Bottle where name = :nm and year = :yr");
			} catch (NullPointerException e) { e.printStackTrace(); return false;}
			query.setParameter("nm", b.getName());
			query.setParameter("yr", b.getYear());
			List<Bottle> bott = query.getResultList();
			if (bott.isEmpty())
				throw new NullPointerException();
			
			
			session.remove(bott.get(0));
			
			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}
	
	public boolean databaseAddNotification(Notification n) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.saveOrUpdate(n);

			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}

	public boolean databaseRemoveNotification(Notification n) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.remove(n);

			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}
	
	private boolean databaseAddToSendBottle(TableElement tableElement) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.saveOrUpdate(tableElement);

			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}

	private boolean databaseRemoveToSendBottle(ObservableList<TableElement> tables) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			for (TableElement t : tables)
			{
				session.remove(t);
			}
			
			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}
	
	public boolean databaseAddToAddBottle(String str) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			session.get(Winery.class, "La Vineria").getWineToAddSet().add(str);
			this.wineToAddSet.add(str);
			
			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			hex.printStackTrace();
			return false;
		}
	}
	
	public boolean databaseRemoveToAddBottle(String str) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			session.get(Winery.class, "La Vineria").getWineToAddSet().remove(str);
			this.wineToAddSet.remove(str);

			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			// transaction.rollback();
			hex.printStackTrace();
			return false;
		}
	}
	
	public boolean databaseAddUserNotification(String mail, String name, int year) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			session.get(User.class, mail).getNotification_list().add(name + " " + year);
			
			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			hex.printStackTrace();
			return false;
		}
	}
	
	public boolean databaseRemoveUserNotification(String mail) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.get(User.class, mail).getNotification_list().clear();
			
			transaction.commit();
			return true;
		} catch (final HibernateException hex) {
			hex.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void databaseInitializeLists() {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			session.saveOrUpdate(this);

			Stream<User> usersStream = session.createQuery("from User").stream();
			usersStream.forEach(u -> {
				u.setSiteSubscription(this);
				userList.add(u);
			});
			
			Stream<Employee> employeesStream = session.createQuery("from Employee").stream();
			employeesStream.forEach(e -> {
				addEmployee(e);
			});
			
			Stream<Bottle> bottlesStream = session.createQuery("from Bottle").stream();
			List<Bottle> bottles = new ArrayList<>();
			bottlesStream.forEach(b -> {
				bottles.add(b);
			});
			addWineToList(bottles);
			
			Stream<Notification> notifyStream = session.createQuery("from Notification").stream();
			notifyStream.forEach(n -> {
				notifyList.add(n);
			});
			
			Stream<TableElement> tableStream = session.createQuery("from TableElement").stream();
			tableStream.forEach(t -> {
				toSendList.add(t);
			});
			
			for (User us : userList)
			{
				us.setNotification_list(session.get(User.class, us.getMail()).getNotification_list());
			}
			
			transaction.commit();
			usersStream.close();
		} catch (final HibernateException hex) {
			hex.printStackTrace();
			throw hex;
		}
	}
}