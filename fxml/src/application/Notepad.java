package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Notepad extends Application {

	String memory;

	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("notepad.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			MenuBar menuBar = (MenuBar)root.lookup("#menu");
			Menu menu = menuBar.getMenus().get(0);

			MenuItem cut = menu.getItems().get(0);
			MenuItem copy = menu.getItems().get(1);
			MenuItem paste = menu.getItems().get(2);

			TextArea ta = (TextArea) root.lookup("#textarea");

			cut.setOnAction(event -> {

				// bug: replaces all occurrences 
				this.memory = ta.getSelectedText();

				int position = ta.getCaretPosition();

				String allText = ta.getText();
				String first, last;

				if (this.memory.equals(allText.substring(position - this.memory.length(), position))) {
					// left to right selection
					first = allText.substring(0, position - this.memory.length());
					last = allText.substring(position, allText.length());
				} else {
					// right to left selection
					first = allText.substring(0, position);
					last = allText.substring(position + this.memory.length(), allText.length());
				}

				System.out.println(first);
				System.out.println(last);

				allText = first + last;
				ta.setText(allText);


			});

			copy.setOnAction(event -> {
				this.memory = ta.getSelectedText();
			});

			paste.setOnAction(event -> {
				// paste at the end
				//				ta.appendText(this.memory);
				//				this.memory = "";

				int position = ta.getCaretPosition();

				String allText = ta.getText();
				String first = allText.substring(0, position);
				String last = allText.substring(position, allText.length());

				allText = first + this.memory + last;
				ta.setText(allText);

				this.memory = "";
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}