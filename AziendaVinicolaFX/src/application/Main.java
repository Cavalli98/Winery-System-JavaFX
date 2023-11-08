package application;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


public class Main extends Application {
	public static final String ARGS = "hibernate.cfg.xml";
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure(ARGS).buildSessionFactory();
		} catch (final Throwable ex) {
			System.out.println("Initial Session Factory creation failed! " + ex);
			// ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	private static Stage primaryStage;
	private static Winery LaVineria = new Winery("La Vineria");
	private static Scene access_scene;
	private static Scene userMP_scene ;
	private static Scene employeeMP_scene ;
	private static ControllerUserMainPageScene userMPController;
	private static ControllerEmployeeMainPageScene employeeMPController;
	
	private static String accessSceneFXML = "application/AccessPage.fxml";
	private static String userMainPageSceneFXML = "application/UserMainPage.fxml";
	private static String employeeMainPageSceneFXML = "application/EmployeeMainPage.fxml";
	private static String imageAccessBackground = "application/resources/viniitalianiImage.jpg";
	private static String imageUserBackground = "application/resources/background_WoodDark.jpg";
	private static String imageEmployeeBackground = "application/resources/background_White.jpg";

	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public static void setAccessScene() {
		primaryStage.setScene(access_scene);
	}
	
	public static void setUserMainPage(String mail, String password) {
		userMPController.setUser(LaVineria.getUser(mail, password));
		primaryStage.setScene(userMP_scene);
	}
	
	public static void setEmployeeMainPage(String code) {
		employeeMPController.setEmployee(LaVineria.getEmployee(code));
		primaryStage.setScene(employeeMP_scene);
	}
	
	@Override
	public void start(Stage stage) {
		try
		{
			FXMLLoader accessLoader = new FXMLLoader(getClass().getClassLoader().getResource(accessSceneFXML));
			Parent rootAccess = accessLoader.load();
			ControllerLoginScene accessController = accessLoader.getController();
			accessController.setWinery(LaVineria);
			access_scene = new Scene(rootAccess);
			access_scene.setFill(new ImagePattern(new Image(imageAccessBackground)));

			FXMLLoader userMPLoader = new FXMLLoader(getClass().getClassLoader().getResource(userMainPageSceneFXML));
			Parent rootUserMP = userMPLoader.load();
			userMPController = userMPLoader.getController();
			userMPController.setWinery(LaVineria);
			userMP_scene = new Scene(rootUserMP);
			userMP_scene.setFill(new ImagePattern(new Image(imageUserBackground)));
			
			FXMLLoader employeeMPLoader = new FXMLLoader(getClass().getClassLoader().getResource(employeeMainPageSceneFXML));
			Parent rootEmployeeMP = employeeMPLoader.load();
			employeeMPController = employeeMPLoader.getController();
			employeeMPController.setWinery(LaVineria);
			employeeMP_scene = new Scene(rootEmployeeMP);
			employeeMP_scene.setFill(new ImagePattern(new Image(imageEmployeeBackground)));
			

			primaryStage = stage;
			primaryStage.setScene(access_scene);
			primaryStage.setTitle("ViniItaliani");
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/* CODICE UTILIZZATO PER LE PROVE
		User UX = new User("ux@gmail.com", "uxpassw", LaVineria);
		User UY = new User("uy@gmail.com", "uypassw", LaVineria);
		User UZ = new User("uz@gmail.com", "uzpassw", LaVineria);
		User prova = new User("a@a.aa", "aaaa", LaVineria);
		Employee vinaio = new Employee("00287755", LaVineria);

		Bottle[] botts = {new Bottle(1990, "Malvasia", "", "San Giovanni"),
				  new Bottle(1992, "Lambrusco", "", "San Giovanni"),
				  new Bottle(1980, "Montepulciano", "", "Montepulciano"),};
		
		Bottle[] botts2 = {new Bottle(1990, "Malvasia", "", "San Giovanni"),
				  new Bottle(1992, "Lambrusco", "", "San Giovanni")};
		
		switch(2)
		{
			case 1:
			{
				vinaio.addWine(botts);
				UX.buyWine("Montepulciano", 1980);
				UX.buyWine("Montepulciano", 1980);
				vinaio.addWine(botts);
				UY.buyWine("Montepulciano", 1980);
				break;
			}
			case 2:
			{
				vinaio.addWine(botts2);
				UY.buyWine("Lambrusco", 1992);
				UY.buyWine("Lambrusco", 1992);
				UY.buyWine("Lambrusco", 1992);
				break;
			}
		}*/

		launch(args);
		System.out.println("Closing Main...");
	}
}
