import java.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import java.util.*;

public class Paddle extends Sprite {
    private static final Config config = Config.getInstance();

    public Paddle() {
        Shape paddleShape = new Rectangle(
                config.getDouble("paddle.width"),
                config.getDouble("paddle.height")
        );
        paddleShape.getStyleClass().add("paddle");

        setShape(paddleShape);

        setPosition(
                new Point2D(
                        config.getDouble("paddle.initX"),
                        config.getDouble("paddle.initY")
                )
        );

        setDefaultSpeed(
                config.getDouble("paddle.defaultSpeed")
        );
    }
}
