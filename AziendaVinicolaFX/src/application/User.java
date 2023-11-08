package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This Class represent a customer of the winery, who, once registered, can buy wine and
 * search for any wine in the catalog.
 * 
 * @author Dario Cavalli
 *
 */

@Entity
@Table(name="User")
public class User {
	@Id
	@Column(name="mail", length=250)
	private String mail;
	@Column(name="password")
	private String password;
	@Transient
	private Winery siteSubscription;
	@ElementCollection (fetch=FetchType.EAGER)
	@CollectionTable(name="User_Notification", joinColumns=@JoinColumn(name="mail"))
	@Column(name="notification")
	private List<String> notification_list;
	@Transient
	private ObservableList<String> notification_obslist;

	public User() {
		this.mail = null;
		this.password = null;
		this.siteSubscription = null;
		this.notification_list = new ArrayList<>();
		this.notification_obslist = FXCollections.observableList(notification_list);
	}

	protected User(String mail, String password) {
		this.mail = mail;
		this.password = password;
		this.siteSubscription = null;
		this.notification_list = new ArrayList<>();
		this.notification_obslist = FXCollections.observableList(notification_list);
	}
	
	protected User(String mail, String password, Winery site) {
		this.mail = mail;
		this.password = password;
		this.siteSubscription = site;
		this.notification_list = new ArrayList<>();
		this.notification_obslist = FXCollections.observableList(notification_list);
	}
	
	/**
	 * The method asks for two strings, the mail and the password, and the winery in which you want to subscribe.
	 * If the user is already registered in the system or if the mail or password are not correct, the methods of
	 * the winery return a message error and nothing is done.
	 * 
	 * @param mail The mail you want to use in the system of the winery.
	 * @param password The password you want to use in the system of the winery.
	 * @param winery The winery in which you want to register.
	 */
	public void subscribe(String mail, String password, Winery winery) {
		if (winery.addUser(this, mail, password))
		{
			this.mail = mail;
			this.password = password;
			this.siteSubscription = winery;
		}
	}
	
	public void subscribe(Winery winery) {
		if (winery.addUser(this, this.getMail(), this.getPassword()))
			siteSubscription = winery;
	}
	
	/**
	 * This method is used by the user to buy a wine in the catalog.
	 * 
	 * @param name The name of the wine you want to buy.
	 * @param year The year of the wine.
	 * @return true if the user succeeds in buying the wine, false otherwise.
	 * @throws NullPointerException if an user not registered try to buy some wine.
	 */
	public boolean buyWine(String name, int year) {
		try
		{
			return siteSubscription.removeBottle(name, year, this.mail);
		}
		catch (NullPointerException e)
		{
			System.out.println("Utente non registrato nel sistema!");
			return false;
		}
	}

	/**
	 * Through the methods search the user can look for a wine in the catalog.
	 * This one is used to search for name and year of the wine.
	 * 
	 * @param name The name of the wine you want to search.
	 * @param year The year of the wine you want to search.
	 */
	public void search(String name, int year) {
		siteSubscription.print(name, year);
	}
	
	/**
	 * Through the methods search the user can look for a wine in the catalog.
	 * This one is used to search only for name.
	 * 
	 * @param name The name of the wine you want to search.
	 */
	public void search(String name) {
		siteSubscription.print(name, 0);
	}
	
	/**
	 * Through the methods search the user can look for a wine in the catalog.
	 * This one is used to return all the wine in the catalog.
	 * 
	 * @param year The year of the wine you want to search.
	 */
	public void search() {
		siteSubscription.print("", 0);
	}
	
	/* Method notification for console output.
	protected void notifications(String name, int year) {
		System.out.println("Ciao " + this.mail + ", il vino " + name + " del " + year + " e' tornato disponibile!");
	}*/
	
	/**
	 * Method used to add in a observablelist a String containing the name and the year of the wine just added in the winery,
	 * and which has to be notified to the user.
	 * 
	 * @param name Name of the wine
	 * @param year Year of the wine
	 */
	protected void notifications(String name, int year) {
		notification_obslist.add(name + " " + year);
		siteSubscription.databaseAddUserNotification(this.mail, name, year);
	}

	public void clearList() {
		notification_obslist.clear();
		siteSubscription.databaseRemoveUserNotification(this.mail);		
	}
	
	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public boolean isInCatalogue(String name) {
		return this.siteSubscription.isInCatalogue(name);
	}

	public ObservableList<Bottle> searchWines(String name, int year) {
		return this.siteSubscription.searchWines(name, year);
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	protected List<String> getNotification_list() {
		return notification_list;
	}

	protected void setNotification_list(List<String> notification_list) {
		setNotification_obslist(notification_list);
	}

	protected ObservableList<String> getNotification_obslist() {
		return notification_obslist;
	}

	protected void setNotification_obslist(List<String> notification_obslist) {
		this.notification_obslist.addAll(notification_obslist);
	}
	
	public Winery getSiteSubscription() {
		return siteSubscription;
	}

	public void setSiteSubscription(Winery siteSubscription) {
		this.siteSubscription = siteSubscription;
	}
	
	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(mail, user.mail);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(mail);
    }
	
	@Override
	public String toString() {
		return mail;
	}
}
