import javafx.animation.PathTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import java.time.*;

public class RectanglePath extends Application{
	@Override
	public void start(Stage primaryStage){
		Pane pane = new Pane();
		
		Polygon pentagon = new Polygon();
		pentagon.getPoints().addAll(new Double[]{
			200.0, 100.0,
			250.0, 200.0,
			250.0, 300.0,
			150.0, 300.0,
			150.0, 200.0});
		pentagon.setStroke(Color.BLACK);
		pentagon.setFill(Color.WHITE);
		
		Rectangle rectangle = new Rectangle(200.0, 100.0, 50, 100);
		rectangle.setFill(Color.BLUE);
		rectangle.setStroke(Color.BLACK);
		
		pane.getChildren().add(pentagon);
		pane.getChildren().add(rectangle);
		
		PathTransition pt = new PathTransition();
		pt.setDuration(Duration.millis(4000));
		pt.setPath(pentagon);
		pt.setNode(rectangle);
		pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setCycleCount(Timeline.INDEFINITE);
		pt.setAutoReverse(true);
		pt.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(3000), rectangle);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);
		ft.play();
		
		pentagon.setOnMousePressed(e -> pt.pause());
		rectangle.setOnMousePressed(e -> ft.pause());
		pentagon.setOnMouseReleased(e -> pt.play());
		rectangle.setOnMouseReleased(e -> ft.play());
		
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Moving Rectangle");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}