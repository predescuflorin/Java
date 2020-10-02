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


public class Register extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button registerBtn = (Button)root.lookup("#registerbtn");
			
			registerBtn.setOnMouseClicked(event -> {
				TextField username = (TextField) root.lookup("#tfusername");
				TextField email = (TextField) root.lookup("#tfemail");
				TextField password = (TextField) root.lookup("#tfpassword");
				
				System.out.println(username.getText());
				System.out.println(email.getText());
				System.out.println(password.getText());
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
