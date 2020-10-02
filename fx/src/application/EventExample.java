package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventExample extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Type of events
//		--- InputEvent ------- KeyEvent -------- KEY_PRESSED
//						|				|------- KEY_RELEASED
//						|				|------- KEY_TYPED
//						|					  
//						|----- MouseEvent ------ MOUSE_PRESSED
//							  			  |----- MOUSE_RELEASED
//							  			  		 ...
//		--- ActionEvent
//		--- WindowEvent ------- WINDOW_SHOWING
//						|------ WINDOWS_SHOWN
//								...
		
		VBox vbox = new VBox();
		
//		vbox.setOnMouseClicked(event -> {
//			event.consume();
//		});

		Scene scene = sceneEvents(vbox);
//		keyEvents(vbox);
//		mouseEvents(vbox);
//		stageEvents(primaryStage);
		
		eventBubbling(vbox);

		scene.getStylesheets().add(getClass().getResource("application.css")
				.toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void eventBubbling(VBox vbox) {
		VBox container = new VBox();
		container.setPadding(new Insets(10, 50, 50, 50));
		container.setSpacing(10);
		container.setStyle("-fx-background-color: white; -fx-border-color: green; -fx-border-width: 3");
		vbox.getChildren().add(container);
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 50, 50, 50));
		hb.setSpacing(10);
		hb.setStyle("-fx-background-color: yellow;");
		Button b1 = new Button("OK");
		Button b2 = new Button("Cancel");
		
		hb.getChildren().addAll(b1, b2);
		container.getChildren().add(hb);
		
		container.setOnMouseClicked(event -> {
			System.out.println(event);
		});
	}

	private void stageEvents(Stage primaryStage) {
		primaryStage.setOnShowing(event -> {
			System.out.println("Showing stage");
		});		
		
		primaryStage.setOnShown(event -> {
			System.out.println("Shown stage");
		});		
		
		primaryStage.setOnHidden(event -> {
			System.out.println("Hidden stage");
		});
		
		primaryStage.setOnHiding(event -> {
			System.out.println("Hiding stage");
		});
		
	}

	private Scene sceneEvents(VBox vbox) {
		Scene scene = new Scene(vbox, 300, 500);
		
		scene.setOnMouseClicked(event -> {
			System.out.println("Mouse click on scene " + event);
		});
		
		scene.setOnMouseEntered(event -> {
			System.out.println("Mouse enter on scene");
		});
		return scene;
	}

	private void mouseEvents(VBox vbox) {
		Button btn = new Button("Click me");
		vbox.getChildren().add(btn);
		
		btn.setOnAction(event -> {
			System.out.println("Default action of button: " + event.getEventType().getName());
		});
		
		btn.setOnMouseEntered(event -> {
			System.out.println("Mouse enter on button");
			btn.setStyle("-fx-background-color: pink");
		});
		
		btn.setOnMouseExited(event -> {
			System.out.println("Mouse exit on button");
			btn.setStyle("-fx-background-color: lightblue");
		});
	}

	private void keyEvents(VBox vbox) {
		TextField tf = new TextField();
		tf.setOnAction(event -> {
			System.out.println("Default action of textfield: " + event.getEventType().getName());
		});
		
		tf.setOnKeyTyped(event -> {
			System.out.println("User typed: " + event.getCharacter());
		});
		
		vbox.getChildren().add(tf);
	}
}
