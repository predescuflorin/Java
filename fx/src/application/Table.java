package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Table extends Application {
	@Override
	public void start(Stage primaryStage) { 
		try {
			
			TableView<Item> tView = new TableView<Item>();
			
			TableColumn<Item, String> tc1 = new TableColumn<Item, String>("Name");
			TableColumn<Item, String> tc2 = new TableColumn<Item, String>("Quantity");
			TableColumn<Item, String> tc3 = new TableColumn<Item, String>("Price");
			
			tc1.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
			tc2.setCellValueFactory(new PropertyValueFactory<Item, String>("quantity"));
			tc3.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
			
			tView.getColumns().addAll(tc3, tc2, tc1);
			
			final ObservableList<Item> data = FXCollections.observableArrayList();
			
			data.add(new Item("Bread", "2", "3$"));
			data.add(new Item("Eggs", "10", "7$"));
			data.add(new Item("Milk", "1", "2$"));
			data.add(new Item("Flour", "1", "1$"));
			
			tView.setItems(data);
			
			HBox h1 = new HBox();
			Label l1 = new Label("Name:");
			TextField tf1 = new TextField();
			h1.getChildren().addAll(l1, tf1);
			
			HBox h2 = new HBox();
			Label l2 = new Label("Quantity:");
			TextField tf2 = new TextField();
			h2.getChildren().addAll(l2, tf2);
			
			HBox h3 = new HBox();
			Label l3 = new Label("Price:");
			TextField tf3 = new TextField();
			h3.getChildren().addAll(l3, tf3);
			
			Button b = new Button("ADD");	
			
			b.setOnMouseClicked(event -> {
				String name = tf1.getText();
				String quantity = tf2.getText();
				String price = tf3.getText();
				
				Item newItem = new Item(name, quantity, price);
				
				data.add(newItem);
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
			});
			
			VBox vbox = new VBox();
			vbox.getChildren().addAll(tView, h1, h2, h3, b);
			
			Scene scene = new Scene(vbox, 300, 500);
			scene.getStylesheets().add(getClass().getResource("application.css")
					.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
