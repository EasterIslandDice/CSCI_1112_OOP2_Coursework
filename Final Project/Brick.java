import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import java.util.*;

public class Brick extends Sprite {
    private static final Config config = Config.getInstance();

    public Brick() {
        Shape brickShape = new Rectangle(
                config.getDouble("brick.width"),
                config.getDouble("brick.height")
        );
        brickShape.getStyleClass().add("brick");

        setShape(brickShape);
    }
}
