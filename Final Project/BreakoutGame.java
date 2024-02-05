import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.*;
import java.util.stream.Collectors;

public class BreakoutGame extends Application {

    private static final String CSS_RESOURCE_NAME = "/breakout.css";
    private static final Config config = Config.getInstance();

    private Scene gameScene;
    final private GameState gameState = new GameState();
    final private InputHandler inputHandler = new InputHandler();
    final private UpdateHandler updateHandler = new UpdateHandler(gameState);
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage window) {
        gameScene = createGameScene();
        gameScene.setOnKeyPressed(inputHandler);
        gameScene.setOnKeyReleased(inputHandler);
        gameLoop = createGameLoop();

        window.setTitle(config.getString("window.title"));
        window.setScene(gameScene);
        window.setResizable(false);
        window.show();

        gameLoop.start();
    }

    public Scene createGameScene() {
        Pane layout = new Pane();
        layout.setPrefSize(
                config.getDouble("scene.prefWidth"),
                config.getDouble("scene.prefHeight")
        );

        layout.getChildren().addAll(
                gameState.getAllSprites().stream()
                        .map(Sprite::getShape)
                        .collect(Collectors.toList())
        );
        gameState.getBricks().addListener((ListChangeListener<Brick>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    layout.getChildren().removeAll(
                            change.getRemoved().stream()
                                    .map(Brick::getShape)
                                    .collect(Collectors.toList())
                    );
                }
            }
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(
                getClass().getResource(CSS_RESOURCE_NAME).toExternalForm()
        );

        return scene;
    }

    private AnimationTimer createGameLoop() {
        return new AnimationTimer() {
            public void handle(long now) {
                updateHandler.update(now, inputHandler.getActiveKeys());
                if (gameState.isGameOver()) {
                    this.stop();
                }
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}



