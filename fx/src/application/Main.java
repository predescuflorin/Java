package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root,400,400);

			this.createLabeledTextField("Username:", root, 0);
			this.createLabeledTextField("Email:", root, 1);
			this.createLabeledTextField("Password:", root, 2);
			
			
			Button registerBtn = new Button("Register");
			registerBtn.setId("my-button");
			root.add(registerBtn, 0, 3);
			
			registerBtn.setOnMouseClicked(event -> {
				System.out.println(event);
				root.getChildren().clear();
				
				this.createLabeledTextField("Username:", root, 0);
				this.createLabeledTextField("Email:", root, 1);
				
				Button loginBtn = new Button("Login");
				root.add(loginBtn, 0, 2);
				
				loginBtn.setStyle("-fx-background-color: blue; -fx-text-fill:white;");

				loginBtn.setOnMouseClicked(event2 -> {
					
					root.getChildren().clear();
					
					
				});
			});
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void createLabeledTextField(String text, GridPane parent, int row) {
		Label label = new Label(text);
		TextField textField = new TextField();
		
		parent.add(label, 0, row); // element, column, row
		parent.add(textField, 1, row);
	}
}
