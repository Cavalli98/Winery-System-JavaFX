package utilities;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class NotificationBox {
    static String string = "";

    public static String display(String title, String message, int control) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        Button okButton = new Button("OK");
        Button dontCare = new Button("Don't Care");
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(okButton, dontCare);
        
        dontCare.setOnAction(e -> { string = "dontCare"; window.close(); });

        VBox layout = new VBox(10);

        if (control == 1)
        {
        	TextField field = new TextField();
        	Label label2 = new Label();
        	label2.setText("Insert your preferred year for wines :");
        	field.setPrefSize(40, 80);
        	field.setStyle("-fx-font-size: 14px; -fx-font-family: Arial");
        	
        	okButton.setOnAction(e -> {
        		String yearS = field.getText();
        		int year = 0;
        		if (!yearS.equals(""))
        		{
        			try { year = Integer.parseInt(yearS); }
        			catch (NumberFormatException ex) {}
        		}
        		if (1000 < year && year < 2020)
        			window.close();
        		string = String.valueOf(year);
            });
        	
            layout.getChildren().addAll(label, label2, field, buttonBox);
        }
        else if (control == 2)
        {
        	TextField field = new TextField();
        	Label label2 = new Label();
        	label2.setText("Insert your preferred wine: ");
        	field.setPrefSize(40, 80);
        	field.setMaxSize(40, 80);
        	field.setStyle("-fx-font-size: 14px; -fx-font-family: Arial");
        	
        	okButton.setOnAction(e -> {
        		string = field.getText();
        		window.close();
            });
        	
            layout.getChildren().addAll(label, label2, field, buttonBox);
        }
        else if (control == 3)
        {
        	Label label2 = new Label();
        	label2.setText("You'll receive all the information about the delivery in your email.");
        	
        	okButton.setOnAction(e -> {
        		string = "OK";
        		window.close();
            });
        	
            layout.getChildren().addAll(label, label2, okButton);
        }
        else
        {        	
        	okButton.setOnAction(e -> {
        		string = "OK";
        		window.close();
            });
        	
            layout.getChildren().addAll(label, buttonBox);
        }
        
        
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return string;
    }
}