import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.*;
public class Clock extends Application{
	Random random = new Random();
	int rn = (random.nextInt(100) % 2);
	
	@Override
	public void start(Stage primaryStage) {
		ClockPane clock = new ClockPane();
		clock.setHour(random.nextInt(12-0));
		if(rn == 0) {
			clock.setMinute(0);
		}
		else {
			clock.setMinute(30);
		}
		clock.setSecondHandVisible(false);
		
		String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
		
		Label lblCurrentTime = new Label(timeString);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(clock);
		pane.setBottom(lblCurrentTime);
		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
		
		Scene scene = new Scene(pane, 250, 250);
		primaryStage.setTitle("Display Clock");
		primaryStage.setScene(scene);
		primaryStage.show();

}

class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;
	boolean hourHand = true;
	boolean minuteHand = true;
	boolean secondHand = true;
	
	ClockPane(){
		setCurrentTime();
	}
	ClockPane(int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}
	
	public boolean getHourHandVisible() {
		return hourHand;
	}
	public void setHourHandVisible(boolean input) {
		this.hourHand = input;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}
	
	public boolean getMinuteHandVisible() {
		return minuteHand;
	}
	public void setMinuteHandVisible(boolean input) {
		this.minuteHand = input;
	}
	
	public int getSecond() {
		return second;
	}
	
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}
	
	public boolean getSecondHandVisible() {
		return secondHand;
	}
	public void setSecondHandVisible(boolean input) {
		this.secondHand = input;
	}


void setCurrentTime() {
	Calendar calendar = new GregorianCalendar();
	
	this.hour = calendar.get(Calendar.HOUR_OF_DAY);
	this.minute = calendar.get(Calendar.MINUTE);
	this.second = calendar.get(Calendar.SECOND);
	
	paintClock();
	
	}

private void paintClock() {
	double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
	double centerX = getWidth()/2;
	double centerY = getHeight()/2;
	
	Circle circle = new Circle(centerX, centerY, clockRadius);
	circle.setFill(Color.WHITE);
	circle.setStroke(Color.BLACK);
	Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
	Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
	Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
	Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
	
	double sLength = clockRadius * 0.8;
	double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI /60));
	double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI /60));
	
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStroke(Color.RED);
	
	
	double mLength = clockRadius * 0.65;
	double minuteX = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
	double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
	
		Line mLine = new Line(centerX, centerY, minuteX, minuteY);
		mLine.setStroke(Color.BLUE);

	
	double hLength = clockRadius * 0.5;
	double hourX = centerX + hLength * Math.sin((hour % 12 + minute /60.0 ) * (2 *Math.PI / 12));
	double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
	
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		hLine.setStroke(Color.GREEN);
	
	getChildren().clear();
	getChildren().addAll(circle, t1, t2, t3, t4);
	if(secondHand == true) {
		getChildren().add(sLine);
	}
	if(minuteHand == true) {
		getChildren().add(mLine);
	}
	if(hourHand == true) {
		getChildren().add(hLine);
	}
}

@Override
public void setWidth(double width) {
	super.setWidth(width);
	paintClock();
}

@Override
public void setHeight(double height) {
	super.setHeight(height);
	paintClock();
}

}
}
