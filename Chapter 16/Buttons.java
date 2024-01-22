/*(Use Radio Buttons) You will be writing a GUI program to display the message “Programming is Fun”. 
You can use buttons to move the message to the left and right and use 
Radio buttons to change the color for the message displayed.*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class Buttons extends Application{
	Text text = new Text("Programming is Fun.");
	
	protected BorderPane getPane(){
		HBox paneForButtons = new HBox(20);
		HBox paneForRadio = new HBox(20);
		Button btLeft = new Button("Left");
		Button btRight = new Button("Right");
		paneForButtons.getChildren().addAll(btLeft, btRight);
		paneForButtons.setAlignment(Pos.CENTER);
		paneForRadio.setAlignment(Pos.CENTER);

		
		RadioButton rbRed = new RadioButton("Red");
		RadioButton rbBlue = new RadioButton("Blue");
		RadioButton rbGreen = new RadioButton("Green");
		paneForRadio.getChildren().addAll(rbRed, rbBlue, rbGreen);
		
		ToggleGroup group = new ToggleGroup();
		rbRed.setToggleGroup(group);
		rbBlue.setToggleGroup(group);
		rbGreen.setToggleGroup(group);
		
		Pane textPane = new Pane();
		textPane.getChildren().add(text);
		text.setLayoutX(200);
		text.setLayoutY(75);
		
		
		BorderPane pane = new BorderPane();
		pane.setBottom(paneForButtons);
		pane.setTop(paneForRadio);
		pane.setCenter(textPane);
		
		btLeft.setOnAction(e -> text.setX(text.getX() - 10));
		btRight.setOnAction(e -> text.setX(text.getX() + 10));
		rbRed.setOnAction(e -> {
			if (rbRed.isSelected()) {
				text.setFill(Color.RED);
			}
		});
		rbBlue.setOnAction(e -> {
			if (rbBlue.isSelected()) {
				text.setFill(Color.BLUE);
			}
		});
		rbGreen.setOnAction(e -> {
			if (rbGreen.isSelected()) {
				text.setFill(Color.GREEN);
			}
		});
		
		return pane;
	}
	
	@Override
	public void start(Stage primaryStage){
		Scene scene = new Scene (getPane(), 450, 200);
		primaryStage.setTitle("Button Demo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}