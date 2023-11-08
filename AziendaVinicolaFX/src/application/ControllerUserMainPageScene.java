package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import utilities.Elements;
import utilities.NotificationBox;

public class ControllerUserMainPageScene implements Initializable{
	
	private Winery LaVineria;
	private User user;
	private String howToText = "If you want to search some wine, insert the name and/or the year press Enter.\n" +
							   "To search through all our catalogue, press Enter without typing anything.\n" + 
							   "Clicking on Notification you can see if the wines you searched is available now.";
	
	private String aboutUsText = "We are a little company located near Firenze, in Italy.\n" +
								 "Our first goal is to sell the best wine a customer can have, all over the World!\n" +
								 "Our products are 100% biological.\n" +
								 "Please have fun looking in our catalogue.";
	
	public BorderPane UserMPBorderPane;
	public Label UserSearchErrorLabel;
	public TextField SearchNameField;
	public TextField SearchYearField;
	public VBox UserSearchVBox;
	public Label UserSearchWineLabel;
	public Button UserNotificationButton;
	public Button UserLogoutButton;
	public GridPane UserNotificationGridPane;
	public ListView<String> UserNotificationListView;
	public Label UserClearNotificationErrorLabel;
	public Button UserNotificationClearButton;
	public GridPane UserSearchGridPane;
	public Button UserBuyNowButton;
	public Label UserBuyErrorLabel;
	public TabPane UserWelcomePageTabPane;
	public Label UserHowToLabel;
	public Label UserAboutUsLabel;
	public Label UserWelcomeLabel;
	
	
	public void handleUserNotificationButtonPressed() {
		UserNotificationListView.getItems().clear();
		UserNotificationListView.getItems().addAll(user.getNotification_obslist());
		
		UserNotificationGridPane.setVisible(true);
		UserSearchGridPane.setVisible(false);
		UserWelcomePageTabPane.setVisible(false);
	}
	
	public void handleUserLogoutButtonPressed() {
		reset();
		Main.setAccessScene();
	}
	
	public void handleUserNotificationClearButtonPressed() {
		user.clearList();
		handleUserNotificationButtonPressed();
	}

	public void handleUserSearch(Event event) {
		UserNotificationGridPane.setVisible(false);
		UserSearchGridPane.setVisible(true);
		UserWelcomePageTabPane.setVisible(false);
		
		String name = SearchNameField.getText();
		String yearS = SearchYearField.getText();
		
		int year = 0;
		if (!yearS.equals(""))
		{
			try { year = Integer.parseInt(yearS); }
			catch (NumberFormatException e) {
				UserSearchErrorLabel.setText("Invalid Year!");
				return;
			}
			
			if (year < 1000 || year > 2020) 
			{
				UserSearchErrorLabel.setText("Invalid Year!");
				return;
			}
		}
		if (name.replaceAll("\\s", "").equals(""))
			name = "";
		
		UserSearchErrorLabel.setText("  ");
		searchWine(name, year);
	}

	public void handleUserBuyNowButtonPressed() {
		if(UserSearchVBox.getChildren().isEmpty())
			return;
		UserBuyErrorLabel.setText("  ");
		
		ToolBar tool;
		ChoiceBox<?> choice = null;
		String str = "";
		int yr = 0;
		int count = 0;
		try
		{
			for (Node n : UserSearchVBox.getChildren())
			{
				tool = (ToolBar) n;
				for (Node c : tool.getItems())
				{
					if (c instanceof Label)
					{
						str = ((Label) c).getText().substring(0, ((Label) c).getText().length()-5);
						yr = Integer.parseInt(((Label) c).getText().substring( ((Label) c).getText().length()-4, ((Label) c).getText().length()));
					}
					if (c instanceof ChoiceBox<?>)
						choice = (ChoiceBox<?>) c;
				}
				
				if (choice == null || str.equals("") || yr == 0)
					throw new Exception();
				
				count = (int) choice.getSelectionModel().getSelectedItem();
				
				for (int i = 0; i < count; i++)
					user.buyWine(str, yr);
			}
		}
		catch(Exception e)
		{
			UserBuyErrorLabel.setText("Error! Restart the application!");
			return;
		}
		
		NotificationBox.display("Confirmation", "The purchase has been confirmed!", 3);
		user.getNotification_obslist().add("Purchase confirmed!");
		handleUserNotificationButtonPressed();
	}
	
	private void searchWine(String name, int year) {
		UserSearchVBox.getChildren().clear();
		if (!user.isInCatalogue(name) && !name.equals(""))
		{
			UserSearchWineLabel.setText("Not available in the catalogue! ");
			return;
		}
		
		ObservableList<Bottle> obsListSearch = user.searchWines(name, year);
		String text;
		
		if (obsListSearch.size() == 0)
		{
			UserSearchWineLabel.setText("No Results. ");
			if (name.equals("") && year == 0) {
				text = "There is a problem with the database. Try again later. ";
				NotificationBox.display("Notification", text, 0);
				text = "dontCare"; }
			else if (!name.equals("") && year == 0) {
				text = "You will be notified when the wines will be available";
				text = NotificationBox.display("Notification", text, 1); 
				if (!text.equals("dontCare"))
					year = Integer.parseInt(text); }
			else if (name.equals("") && year != 0) {
				text = "You will be notified when the wines will be available";
				text = NotificationBox.display("Notification", text, 2); 
				if (!text.equals("dontCare"))
					name = text; }
			else {
				text = "You will be notified when the wines will be available";
				text = NotificationBox.display("Notification", text, 0); }
			
			if (!text.equals("dontCare"))
				user.buyWine(name, year);
			return;
		}
		
		if (name.equals("") && year == 0)
			text = "Search Results: ";
		else if (year == 0)
			text = "Results for " + name + ": ";
		else
			text = "Results for " + name + " " + year + ": ";
		UserSearchWineLabel.setText(text);
		
		List<Elements> elementsList = new ArrayList<>();
		for (Bottle b : obsListSearch)
		{
			Elements e = new Elements(b.getName(), String.valueOf(b.getYear()), 1);
			if (!elementsList.contains(e))
			{
				elementsList.add(e);
			}
			else
			{
				elementsList.get(elementsList.indexOf(e)).incrementVal();
			}
		}
		
		ToolBar[] toolarray = new ToolBar[elementsList.size()];
		for (int i = 0; i < elementsList.size(); i++)
		{
			Label lab = new Label();
			lab.setText(elementsList.get(i).getVals());
			ChoiceBox<Integer> choice = new ChoiceBox<>();
			
			for (int j = 0; j <= elementsList.get(i).getVal(); j++)
			{
				choice.getItems().add(j);
			}
			choice.setValue(0);
			
			ToolBar tool = new ToolBar();
			
			tool.getItems().addAll(lab, choice);
			toolarray[i] = tool;
			toolarray[i].setId("ToolBar");
			UserSearchVBox.getChildren().add(toolarray[i]);
		}
	}

	public Winery getWinery() {
		return LaVineria;
	}

	public void setWinery(Winery laVineria) {
		LaVineria = laVineria;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Label label = new Label();
		label.setText("No notification so far. Keep checking, more wines will arrive soon!");
		label.setStyle("-fx-font-family: Arial;" + " -fx-font-size: 14");
		UserNotificationListView.setPlaceholder(label);
		UserHowToLabel.setText(howToText);
		UserAboutUsLabel.setText(aboutUsText);
		UserNotificationGridPane.setVisible(false);
		UserSearchGridPane.setVisible(false);
		UserWelcomePageTabPane.setVisible(true);
	}
	
	private void reset() {
		UserSearchErrorLabel.setText(" ");
		SearchNameField.clear();
		SearchYearField.clear();
		UserSearchWineLabel.setText("Search Results: ");
		UserBuyErrorLabel.setText(" ");
		user = null;
		UserNotificationGridPane.setVisible(false);
		UserSearchGridPane.setVisible(false);
		UserWelcomePageTabPane.setVisible(true);
	}
}
