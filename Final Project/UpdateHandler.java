import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.*;

public class UpdateHandler {

    private static final Config config = Config.getInstance();

    private GameState gameState;

    private final Bounds wallBounds;

    private final Point2D paddleRightVelocity;
    private final Point2D paddleLeftVelocity;

    private static final Point2D LEFT_VECTOR = new Point2D(-1, 0);
    private static final Point2D RIGHT_VECTOR = new Point2D(1, 0);
    private static final Point2D UP_VECTOR = new Point2D(0, 1);
    private static final Point2D DOWN_VECTOR = new Point2D(0, -1);

    public UpdateHandler(GameState gameState) {
        this.gameState = gameState;

        wallBounds = new BoundingBox(
                config.getDouble("wall.minX"),
                config.getDouble("wall.minY"),
                config.getDouble("wall.width"),
                config.getDouble("wall.height")
        );

        double defaultPaddleSpeed = gameState.getPaddle().getDefaultSpeed();
        paddleLeftVelocity = LEFT_VECTOR.multiply(defaultPaddleSpeed);
        paddleRightVelocity = RIGHT_VECTOR.multiply(defaultPaddleSpeed);
    }

    public void update(long now, Set<KeyCode> activeKeys) {
        applyInputToPaddle(activeKeys);

        updateSpritePositions();

        restrictPaddleMovementByWalls();
        restrictBallMovementByWalls();

        handlePaddleCollision();
        handleBrickCollision();
    }

    private void applyInputToPaddle(Set<KeyCode> activeKeys) {
        Point2D paddleVelocity = Point2D.ZERO;

        if (activeKeys.contains(KeyCode.LEFT)) {
            paddleVelocity = paddleVelocity.add(paddleLeftVelocity);
        }

        if (activeKeys.contains(KeyCode.RIGHT)) {
            paddleVelocity = paddleVelocity.add(paddleRightVelocity);
        }

        gameState.getPaddle().setVelocity(paddleVelocity);
    }

    private void restrictPaddleMovementByWalls() {
        Paddle paddle = gameState.getPaddle();
        double paddleWidth = paddle.getShape().getLayoutBounds().getWidth();

        if (paddle.getPosition().getX() < wallBounds.getMinX()) {
            paddle.setVelocity(Point2D.ZERO);
            paddle.setPosition(new Point2D(wallBounds.getMinX(), paddle.getPosition().getY()));
        }

        if (paddle.getPosition().getX() + paddleWidth > wallBounds.getMaxX()) {
            paddle.setVelocity(Point2D.ZERO);
            paddle.setPosition(new Point2D(wallBounds.getMaxX() - paddleWidth, paddle.getPosition().getY()));
        }
    }

    private void restrictBallMovementByWalls() {
        Ball ball = gameState.getBall();

        if (ball.getPosition().getX() <= wallBounds.getMinX()) {
            ball.setVelocity(
                    reflect(ball.getVelocity(), RIGHT_VECTOR)
            );
        }

        if (ball.getPosition().getX() + ball.getShape().getLayoutBounds().getWidth() >= wallBounds.getMaxX()) {
            ball.setVelocity(
                reflect(ball.getVelocity(), LEFT_VECTOR)
            );
        }

        if (ball.getPosition().getY() <= wallBounds.getMinY()) {
            ball.setVelocity(
                reflect(ball.getVelocity(), DOWN_VECTOR)
            );
        }

        if (ball.getPosition().getY() + ball.getShape().getLayoutBounds().getHeight() >= wallBounds.getMaxY()) {
            gameState.setGameOver(true);
        }
    }

    private void handlePaddleCollision() {
        Ball ball = gameState.getBall();

        if (gameState.getBall().intersects(gameState.getPaddle())) {
            ball.setVelocity(
                    reflect(ball.getVelocity(), UP_VECTOR)
            );
        }
    }

    private void handleBrickCollision() {
        Ball ball = gameState.getBall();
        ObservableList<Brick> bricks = gameState.getBricks();

        for (Iterator<Brick> brickIterator = bricks.iterator(); brickIterator.hasNext();) {
            Brick brick = brickIterator.next();

            if (ball.intersects(brick)) {
                brickIterator.remove();

                // note this is simplistic, would better to find out which side of the brick was hit and do the correct reflection for the side.
                ball.setVelocity(
                        reflect(ball.getVelocity(), DOWN_VECTOR)
                );
            }
        }
    }

    private void updateSpritePositions() {
        gameState.getAllSprites().forEach(
                Sprite::applyVelocity
        );
    }

    private Point2D reflect(Point2D vector, Point2D normal) {
        return vector.subtract(normal.multiply(vector.dotProduct(normal) * 2));
    }

}
