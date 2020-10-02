package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

//		alertInfo();
//		alertWarning();
//		alertError();
//		alertConfirm();
		alertConfirmCustom();

		VBox vbox = new VBox();

		Scene scene = new Scene(vbox, 300, 500);
		scene.getStylesheets().add(getClass().getResource("application.css")
				.toExternalForm());

		primaryStage.setScene(scene);
//		primaryStage.show();
	}

	private void alertInfo() {
		// information dialog
		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle("Information Dialog");
		alert.setHeaderText("Hi, I am an alert dialog!");
		alert.setContentText("I have message for you!");

		alert.showAndWait();
	}

	private void alertWarning() {
		// warning dialog
		Alert alert = new Alert(AlertType.WARNING);

		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Hi, I am an alert dialog!");
		alert.setContentText("I have message for you!");

		alert.showAndWait();
	}

	private void alertError() {
		// error dialog
		Alert alert = new Alert(AlertType.ERROR);

		alert.setTitle("Error Dialog");
		alert.setHeaderText("Hi, I am an alert dialog!");
		alert.setContentText("I have message for you!");

		alert.showAndWait();
	}

	private void alertConfirm() {
		// confirmation dialog
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Confirmation Dialog");
		confirm.setHeaderText("Hi, I am confirmation dialog!");
		confirm.setContentText("Is this ok?");

		Optional<ButtonType> result = confirm.showAndWait();

		if (result.get() == ButtonType.OK) {
			System.out.println("User said ok");
		} else {
			System.out.println("User said cancel");
		}
	}

	private void alertConfirmCustom() {
		// confirmation dialog with custom actions
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Confirmation Dialog");
		confirm.setHeaderText("Hi, I am confirmation dialog!");
		confirm.setContentText("What do you want to buy?");
		
		ButtonType buttonApples = new ButtonType("Apples");
		ButtonType buttonPeaches = new ButtonType("Peaches");
		ButtonType buttonPlums = new ButtonType("Plums");
		ButtonType buttonNothing = new ButtonType("Nothing", ButtonData.CANCEL_CLOSE);
		ButtonType buttonNext = new ButtonType("Next", ButtonData.NEXT_FORWARD);

		confirm.getButtonTypes().setAll(buttonApples, buttonPeaches, buttonPlums, buttonNext, buttonNothing);

		Optional<ButtonType> result = confirm.showAndWait();
		
		if (result.get() == buttonApples){
		    System.out.println("Apples!!!");
		} else if (result.get() == buttonPeaches) {
		    System.out.println("Peaches!!!");
		} else if (result.get() == buttonPlums) {
		    System.out.println("Plums!!!");
		} else if (result.get().getButtonData() == ButtonData.NEXT_FORWARD){
			System.out.println("Show more!");
		} else {
		    System.out.println("Nothing...");
		}
	}



}
