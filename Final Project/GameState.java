import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import java.util.*;

public class GameState {
    private static final Config config = Config.getInstance();

    private ObservableList<Brick> bricks;
    private Ball ball;
    private Paddle paddle;
    private boolean gameOver;

    public GameState() {
        init();
    }

    public void init() {
        ball = new Ball();
        paddle = new Paddle();
        bricks = FXCollections.observableArrayList();

        int numColumns = config.getInt("bricks.numColumns");
        int numRows = config.getInt("bricks.numRows");
        int numColors = config.getInt("bricks.numColors");
        Double spacing = config.getDouble("bricks.spacing");
        Double yOffset = config.getDouble("bricks.yOffset");

        for (int x = 0; x < numColumns; x++) {
            for (int y = 0; y < numRows; y++) {
                Brick brick = new Brick();
                Shape brickShape = brick.getShape();

                int brickColorIndex = 1 + (int) Math.floor(y / (numRows * 1.0 / numColors));
                brickShape.getStyleClass().add("color" + brickColorIndex);

                double xPos =
                        x * (brickShape.getLayoutBounds().getWidth() + spacing);
                double yPos =
                        y * (brickShape.getLayoutBounds().getHeight() + spacing) + yOffset;
                brick.setPosition(
                        new Point2D(xPos, yPos)
                );

                bricks.add(brick);
            }
        }
    }

    public ObservableList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Sprite> getAllSprites() {
        List<Sprite> allSprites = new ArrayList<>(bricks);
        allSprites.add(ball);
        allSprites.add(paddle);

        return allSprites;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}