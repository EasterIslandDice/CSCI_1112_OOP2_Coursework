/*Revise Listing 18.9 to develop a program that lets the user use the + and - buttons to increase or decrease the current
order by 1. The initial order is 0. If the current order is 0, the Decrease button is ignored.*/

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.awt.*;

public class Sierpinski extends Application{
	@Override
	public void start(Stage primaryStage){
		RecursiveTrianglePane pane = new RecursiveTrianglePane();
		Button btAdd = new Button("+");
		Button btRemove = new Button("-");
		btAdd.setOnAction(e -> {
			pane.setOrder(pane.order + 1);

		});
		btRemove.setOnAction(e -> {
			if(pane.order > 0)
				pane.setOrder(pane.order - 1);
			else{
				pane.getChildren().clear();
			}	
		});

		
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(btAdd, btRemove);
		hbox.setAlignment(Pos.CENTER);
		
		BorderPane bPane = new BorderPane();
		bPane.setCenter(pane);
		bPane.setBottom(hbox);
		
		Scene scene = new Scene(bPane, 200, 210);;
		primaryStage.setTitle("Forbidden Triforce");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pane.widthProperty().addListener(ov -> pane.paint());
		pane.heightProperty().addListener(ov -> pane.paint());
	}
	
	static class RecursiveTrianglePane extends Pane {
		int order = 0;
		
		public void setOrder(int order){
			this.order = order;
			paint();
		}
		
		public int getOrder(){
			return order;
		}
		
		RecursiveTrianglePane(){
			
		}
		
		protected void paint(){
			Point2D p1 = new Point2D(getWidth() /2, 10);
			Point2D p2 = new Point2D(10, getHeight() - 10);
			Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);
			
			this.getChildren().clear();
			
			displayTriangles(order, p1, p2, p3);
		}
		
		private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3){
			if (order == 0){
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
				triangle.setStroke(Color.BLACK);
				triangle.setFill(Color.WHITE);
				
				this.getChildren().add(triangle);
			}
			else{
				Point2D p12 = p1.midpoint(p2);
				Point2D p23 = p2.midpoint(p3);
				Point2D p31 = p3.midpoint(p1);
				
				displayTriangles(order -1, p1, p12, p31);
				displayTriangles(order - 1, p12, p2, p23);
				displayTriangles(order - 1, p31, p23, p3);
			}
		}

}
}
