package application;
	
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


public class Calculator extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			TextField nr1 = (TextField) root.lookup("#nr1");
			TextField nr2 = (TextField) root.lookup("#nr2");
			
			Label nr3 = (Label) root.lookup("#nr3");
			
			ComboBox<String> dropdown = (ComboBox<String>) root.lookup("#operatie");
			
			Button calculeaza = (Button) root.lookup("#calculeaza");
			
			calculeaza.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					Operation myOp = new Operation(
							Integer.parseInt(nr1.getText()),
							Integer.parseInt(nr2.getText()));
					
					myOp.setOp(dropdown.getSelectionModel().getSelectedItem());
					myOp.calculate();
					nr3.setText("" + myOp.getResult());
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
