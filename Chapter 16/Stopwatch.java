/*(Count-down Stopwatch) You will write a program that allows the user to enter a time in seconds in the text field
and press the enter key to count down the seconds, as shown below. The remaining seconds are re-displayed every one second. 
When the seconds are expired, the program starts to play music continuously.*/

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import javax.print.attribute.standard.*;


public class Stopwatch extends Application{
	
	private static final String MEDIA_URL = "https://drive.google.com/file/d/14X00bEFstNeUTGQrUwMrqyuu6la6s5Yy/view?usp=drive_link";
	@Override
	public void start(Stage primaryStage){
		TextField countdown = new TextField("0");
		countdown.setAlignment(Pos.CENTER);
		countdown.setFocusTraversable(false);
		
		Pane pane = new Pane();
		pane.getChildren().add(countdown);
		
		Timeline timeline = new Timeline(
			new KeyFrame(Duration.millis(1000), e -> {
				countdown.setText((Integer.parseInt(countdown.getText()) - 1) + "");
			})
		);
		
		countdown.setOnAction(e -> {
			if(timeline.getStatus() == Animation.Status.RUNNING){
				timeline.stop();
			}
			timeline.setCycleCount(Integer.parseInt(countdown.getText()));
			countdown.setEditable(false);
			timeline.play();
		});
		
		Media sound = new Media(MEDIA_URL);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		timeline.setOnFinished(e -> {
			mediaPlayer.play();
		});
		
		primaryStage.setScene(new Scene(pane));
		primaryStage.setTitle("Stopwatch");
		primaryStage.show();
	}
}