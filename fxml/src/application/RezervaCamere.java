package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class RezervaCamere extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("room.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			ArrayList<Button> myButtons = new ArrayList<Button>();
			myButtons.add((Button)root.lookup("#rez1"));
			myButtons.add((Button)root.lookup("#rez2"));
			myButtons.add((Button)root.lookup("#rez3"));
			myButtons.add((Button)root.lookup("#rez4"));
			
			Label nrRooms = (Label)root.lookup("#nrRoom");
			
			for (int i = 0; i < myButtons.size(); i ++) {
				myButtons.get(i).setOnMouseClicked(event -> {
					int count = Integer.parseInt(nrRooms.getText());
					count++;
					nrRooms.setText("" + count);
				});
			}
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}







//
//Button b1 = (Button) root.lookup("#b1");
//Button b2 = (Button) root.lookup("#b2");
//Button b3 = (Button) root.lookup("#b3");
//
//Label nr = (Label) root.lookup("#nr");
//
//b1.setOnMouseClicked(event -> {
//	String counter = nr.getText();
//	nr.setText("" + (Integer.parseInt(counter) + 1));
//});
//
//b2.setOnMouseClicked(event -> {
//	String counter = nr.getText();
//	nr.setText("" + (Integer.parseInt(counter) + 1));
//});
//
//b3.setOnMouseClicked(event -> {
//	String counter = nr.getText();
//	nr.setText("" + (Integer.parseInt(counter) + 1));
//});

