/*(Move the Ball) You will write a program that moves a ball in a pane. You should
define a pane class for displaying the ball and provide methods for moving the ball
left, right, up, and down. Check the boundary to prevent the ball from moving out of sight completely.*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class MoveTheBall extends Application {  
  @Override
  public void start(Stage primaryStage) {
    Circle ball = new Circle(10);
    Button btUp = new Button("Up");
    Button btDown = new Button("Down");
    Button btLeft = new Button("Left");
    Button btRight = new Button("Right");
    
    
    HBox pane = new HBox();
    Pane bPane = new Pane();
    
    
    
    ball.setFill(Color.RED);
    ball.setStroke(Color.BLACK);
    pane.setSpacing(10);
    pane.setAlignment(Pos.CENTER);
    ball.relocate(200, 200);
    pane.getChildren().addAll(btUp, btDown, btLeft, btRight);
    bPane.getChildren().addAll(ball, pane);
    
    btUp.setOnAction((ActionEvent e) -> BallControl.moveUp(ball));
    btDown.setOnAction((ActionEvent e) -> BallControl.moveDown(ball));
    btLeft.setOnAction((ActionEvent e) -> BallControl.moveLeft(ball));
    btRight.setOnAction((ActionEvent e) -> BallControl.moveRight(ball));
    
    
  
  Scene scene = new Scene(bPane, 400, 400);
  primaryStage.setScene(scene);
  primaryStage.setTitle("Move the Ball");
  primaryStage.show();
  }
  
  public static void main (String[] args)  
  {  
      launch(args);  
  }  
  
  
}

class BallControl{
  public static void moveUp(Circle circle){
    if(circle.getCenterY() - 10 > 0) return;
      circle.setCenterY(circle.getCenterY() - 10);
  }
      
  public static void moveDown(Circle circle){
    if(circle.getCenterY() + 10 > 400) return;
    circle.setCenterY(circle.getCenterY() + 10);
  }
          
  public static void moveLeft(Circle circle){
    if(circle.getCenterX() - 10 > 0) return;
    circle.setCenterX(circle.getCenterX() - 10);
  }
              
    public static void moveRight(Circle circle){
      if(circle.getCenterX() + 10 > 400) return;
      circle.setCenterX(circle.getCenterX() + 10);
    }
}
    


  