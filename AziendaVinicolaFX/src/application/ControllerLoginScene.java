package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ControllerLoginScene implements Initializable{
	private Winery LaVineria;
	
	public BorderPane AccessBorderPane;
	public StackPane AccessStackCenter;
	
	public GridPane LoginGridPane;
	public TextField LoginUserField;
	public Label LoginErrorLabel;
	public Button LoginButton;
	public PasswordField LoginPasswField;
	
	public GridPane EmployeeGridPane;
	public TextField EmployeeCodeField;
	public Label EmployeeLoginErrorLabel;
	public Button EmployeeLoginButton;
	
	public GridPane SubscribeGridPane;
	public TextField SubscribeUserField;
	public PasswordField SubscribePasswField;
	public TextField SubscribePasswFieldVisible;
	public CheckBox SubscribePasswCheckBox;
	public Label SubscribeErrorLabel;
	public Button SubscribeButton;

	public Rectangle LoginRect;
	public Rectangle LoginRect1;
	public Rectangle LoginRect2;
	
	public Rectangle SubscribeRect;
	public Rectangle SubscribeRect1;
	public Rectangle SubscribeRect2;
	
	public Rectangle EmployeeRect;
	public Rectangle EmployeeRect1;
	public Rectangle EmployeeRect2;
	
	public void handleLoginRectClicked() {
		LoginRect.setStyle("-fx-fill: #27e514");
		LoginRect1.setStyle("-fx-fill: #27e514");
		LoginRect2.setStyle("-fx-fill: #27e514");
		SubscribeRect.setStyle("-fx-fill: #24a000");
		SubscribeRect1.setStyle("-fx-fill: #24a000");
		SubscribeRect2.setStyle("-fx-fill: #24a000");
		EmployeeRect.setStyle("-fx-fill: #24a000");
		EmployeeRect1.setStyle("-fx-fill: #24a000");
		EmployeeRect2.setStyle("-fx-fill: #24a000");
		
		LoginGridPane.setVisible(true);
		SubscribeGridPane.setVisible(false);
		EmployeeGridPane.setVisible(false);
	}
	
	public void handleSubscribeRectClicked() {
		LoginRect.setStyle("-fx-fill: #24a000");
		LoginRect1.setStyle("-fx-fill: #24a000");
		LoginRect2.setStyle("-fx-fill: #24a000");
		SubscribeRect.setStyle("-fx-fill: #27e514");
		SubscribeRect1.setStyle("-fx-fill: #27e514");
		SubscribeRect2.setStyle("-fx-fill: #27e514");
		EmployeeRect.setStyle("-fx-fill: #24a000");
		EmployeeRect1.setStyle("-fx-fill: #24a000");
		EmployeeRect2.setStyle("-fx-fill: #24a000");

		LoginGridPane.setVisible(false);
		SubscribeGridPane.setVisible(true);
		EmployeeGridPane.setVisible(false);
	}
	
	public void handleEmployeeRectClicked() {
		LoginRect.setStyle("-fx-fill: #24a000");
		LoginRect1.setStyle("-fx-fill: #24a000");
		LoginRect2.setStyle("-fx-fill: #24a000");
		SubscribeRect.setStyle("-fx-fill: #24a000");
		SubscribeRect1.setStyle("-fx-fill: #24a000");
		SubscribeRect2.setStyle("-fx-fill: #24a000");
		EmployeeRect.setStyle("-fx-fill: #27e514");
		EmployeeRect1.setStyle("-fx-fill: #27e514");
		EmployeeRect2.setStyle("-fx-fill: #27e514");
		
		LoginGridPane.setVisible(false);
		SubscribeGridPane.setVisible(false);
		EmployeeGridPane.setVisible(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		handleLoginRectClicked();
		SubscribePasswFieldVisible.managedProperty().bind(SubscribePasswCheckBox.selectedProperty());
		SubscribePasswFieldVisible.visibleProperty().bind(SubscribePasswCheckBox.selectedProperty());
		SubscribePasswField.managedProperty().bind(SubscribePasswCheckBox.selectedProperty().not());
		SubscribePasswField.visibleProperty().bind(SubscribePasswCheckBox.selectedProperty().not());
	    SubscribePasswFieldVisible.textProperty().bindBidirectional(SubscribePasswField.textProperty());
	}

	public void handleLoginButtonClicked() {
		String user = LoginUserField.getText();
		String passw = LoginPasswField.getText();
		
		if(!LaVineria.logIn(user, passw))
		{
			LoginErrorLabel.setText("Username o Password errati!");
			return;
		}
		
		try {
			LoginErrorLabel.setText("Login Effettuato!");
			Timeline fiveSecondsWonder = new Timeline(
					new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() 
					{ 
						public void handle(ActionEvent event) 
						{
							LoginErrorLabel.setText(" ");
						} 
					}),
					new KeyFrame(Duration.seconds(1.1), new EventHandler<ActionEvent>() 
					{ 
						public void handle(ActionEvent event) 
						{
							Main.setUserMainPage(user, passw);
							reset();
						}
					}));
			fiveSecondsWonder.setCycleCount(1);
			fiveSecondsWonder.play();
		}
		catch (Exception e) {
			LoginErrorLabel.setText("Applicazione temporaneamente non disponibile.");
		}
	}

	public void handleSubscribeButtonClicked() {
		String user = SubscribeUserField.getText();
		String passw = SubscribePasswField.getText();
		String response = LaVineria.subscribeUser(user, passw);
		
		
		if (!response.toLowerCase().equalsIgnoreCase("registrazione avvenuta!"))
		{
			SubscribeErrorLabel.setText(response);
			return;
		}
		
		try {
			SubscribeErrorLabel.setText(response);
			Timeline fiveSecondsWonder = new Timeline(
					new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() 
					{ 
						public void handle(ActionEvent event) 
						{
							SubscribeErrorLabel.setText(" ");
						} 
					}),
					new KeyFrame(Duration.seconds(1.1), new EventHandler<ActionEvent>() 
					{ 
						public void handle(ActionEvent event) 
						{
							Main.setUserMainPage(user, passw);
							reset();
						}
					}));
			fiveSecondsWonder.setCycleCount(1);
			fiveSecondsWonder.play();
		}
		catch (Exception e) {
			SubscribeErrorLabel.setText("Applicazione temporaneamente non disponibile.");
		}
	}

	public void handleEmployeeButtonClicked() {
		String code = EmployeeCodeField.getText();

		if(!LaVineria.authenticationEmployee(code)) {
			EmployeeLoginErrorLabel.setText("Codice errato!");
			return; }
		
		EmployeeLoginErrorLabel.setText(" ");
		Main.setEmployeeMainPage(code);
		reset();
	}
	
	private void reset() {
		handleLoginRectClicked();
		LoginUserField.clear();
		LoginErrorLabel.setText("");
		LoginPasswField.clear();
		EmployeeCodeField.clear();
		EmployeeLoginErrorLabel.setText("");
		SubscribeUserField.clear();
		SubscribePasswField.clear();
		SubscribePasswFieldVisible.clear();
		SubscribeErrorLabel.setText("");
	}
	
	public Winery getWinery() {
		return LaVineria;
	}

	public void setWinery(Winery laVineria) {
		LaVineria = laVineria;
	}
}
