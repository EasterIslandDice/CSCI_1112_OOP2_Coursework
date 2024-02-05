import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import java.util.*;

public class Sprite {
    private Shape shape;
    private Point2D pos;
    private Point2D velocity;
    private double defaultSpeed;

    public Sprite() {
        this(null);
    }

    public Sprite(Shape shape) {
        this(shape, 0, 0);
    }

    public Sprite(Shape shape, double x, double y) {
        this(shape, 0, 0, 0, 0);
    }

    public Sprite(Shape shape, double x, double y, double dx, double dy) {
        this.shape = shape;
        pos = new Point2D(x, y);
        velocity = new Point2D(dx, dy);

        updateShapePos();
    }

    public void applyVelocity() {
        pos = pos.add(velocity);

        updateShapePos();
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;

        updateShapePos();
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getPosition() {
        return pos;
    }

    public void setPosition(Point2D pos) {
        this.pos = pos;

        updateShapePos();
    }

    public double getDefaultSpeed() {
        return defaultSpeed;
    }

    public void setDefaultSpeed(double defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public boolean intersects(Sprite otherSprite) {
        Shape intersection = Shape.intersect(getShape(), otherSprite.getShape());
        Bounds intersectionBounds = intersection.getLayoutBounds();

        return intersectionBounds.getWidth() > 0 || intersectionBounds.getHeight() > 0;
    }

    private void updateShapePos() {
        if (shape != null) {
            shape.relocate(pos.getX(), pos.getY());
        }
    }
}
