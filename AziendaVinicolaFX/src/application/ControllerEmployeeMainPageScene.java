package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.TableElement;

public class ControllerEmployeeMainPageScene implements Initializable {
	
	@SuppressWarnings("unused")
	private Winery LaVineria;
	private Employee employee;
	private String welcomeText = "Welcome to your Control Panel.\n" + 
								 "Here you can add wines, see which wines are finished " +
								 "and see which wines have to be sent and get them sent.\n" +
								 "Use the tabs in the top-left corner to switch between panels.\n" +
								 "ViniItaliani.";

	public TabPane EmpTabPane;
	public Tab EmpTab1;
	public Tab EmpTab2;
	public TextField EmpNameField;
	public TextField EmpYearField;
	public TextField EmpVinesField;
	public TextField EmpQuantityField;
	public Button EmpAddWinesButton;
	public Label EmpAddErrorLabel;
	public ListView<String> EmpListView;
	public Button EmpDeleteButton;
	public TableView<TableElement> EmpSendTable;
	public TableColumn<TableElement, String> TableViewMailC;
	public TableColumn<TableElement, String> TableViewNameC;
	public TableColumn<TableElement, Integer> TableViewYearC;
	public TableColumn<TableElement, Integer> TableViewQuantityC;
	public Button EmpSendButton;
	public Label EmpWelcomeLabel;
	public Button EmpLogoutButton;
	
	
	public void handleEmpDeleteButtonPressed() {
		ObservableList<String> obs_temp = EmpListView.getSelectionModel().getSelectedItems();
		if (obs_temp.size() != 0)
		{
			for (String s : obs_temp)
			{
				employee.setWineToAdd(s);
			}
		}
		
		setEmpListView();
	}
	
	public void setEmpListView() {
		EmpListView.getItems().clear();
		EmpListView.getItems().addAll(employee.getWineToAdd());
	}
	
	public void handleEmpAddWinesButtonPressed() {
		String name = EmpNameField.getText();
		int year;
		try {
			if (name.replaceAll("\\s", "").equals(""))
				throw new NullPointerException();
			year = Integer.parseInt(EmpYearField.getText());
			if (!(1000 < year && year < 2020))
				throw new NumberFormatException();
		}
		catch(NumberFormatException e) {
			EmpAddErrorLabel.setText("Invalid year!");
			return;
		}
		catch( NullPointerException n)
		{
			EmpAddErrorLabel.setText("Invalid name!");			
			return;
		}
		String vines = EmpVinesField.getText();
		int qnty;
		try {
			qnty = Integer.parseInt(EmpQuantityField.getText());
			if (!(0 < qnty && qnty < 500))
				throw new NumberFormatException();
		}
		catch(NumberFormatException e) {
			EmpAddErrorLabel.setText("Invalid quantity!");
			return;
		}
		
		Bottle[] bottles_to_add = new Bottle[qnty];
		for (int i = 0; i < qnty; i++)
			bottles_to_add[i] = new Bottle(year, name, "", vines);
		
		employee.addWine(bottles_to_add);
		EmpAddErrorLabel.setText(" ");
		EmpNameField.clear();
		EmpYearField.clear();
		EmpVinesField.clear();
		EmpQuantityField.clear();
		setEmpListView();
	}
	
	public void handleEmpTab2Clicked() {
		setEmpSendTable();
	}
	
	public void handleEmpSendButtonPressed() {
		ObservableList<TableElement> obs_temp = EmpSendTable.getSelectionModel().getSelectedItems();
		employee.send(obs_temp);
		setEmpSendTable();
	}
	
	private void setEmpSendTable() {
		EmpSendTable.setItems(employee.getToSendList());
	}
	
	public void handleEmpLogoutButton() {
		EmpNameField.clear();
		EmpYearField.clear();
		EmpVinesField.clear();
		EmpQuantityField.clear();
		EmpAddErrorLabel.setText(" ");
		
		Main.setAccessScene();
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setWinery(Winery winery) {
		this.LaVineria = winery;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableViewMailC.setCellValueFactory(new PropertyValueFactory<>("mail"));
		TableViewNameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableViewYearC.setCellValueFactory(new PropertyValueFactory<>("year"));
		TableViewQuantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		EmpSendTable.getSelectionModel().setSelectionMode(
			    SelectionMode.MULTIPLE
			);
		EmpWelcomeLabel.setText(welcomeText);
		EmpWelcomeLabel.setWrapText(true);
	}
}
