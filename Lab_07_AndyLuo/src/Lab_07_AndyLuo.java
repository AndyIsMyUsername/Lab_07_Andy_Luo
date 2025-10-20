
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Lab_07_AndyLuo extends Application {

    // Rectangle path points (M, N, P, Q)
    private static final double M_X = 100, M_Y = 50;
    private static final double N_X = 500, N_Y = 50;
    private static final double P_X = 500, P_Y = 250;
    private static final double Q_X = 100, Q_Y = 250;

    private static final double SCENE_WIDTH = 640;
    private static final double SCENE_HEIGHT = 480;

    // total duration in seconds for the full loop
    private static final double TOTAL_SECONDS = 5;

    private PathTransition pathTransitionA;
    private SequentialTransition sequentialB;
    private PauseTransition endDelay;

    // nodes
    private Circle objectA;   
    private Ellipse objectB;
    
     public static void main(String[] args) {
        launch(args);
    }
      
    @Override
    public void start(Stage stage) {
        
        Pane animationPane = new Pane();
        animationPane.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT - 100);

        //Circle
        objectA = new Circle(0, 0, 15);
        // position
        objectA.setTranslateX(M_X);
        objectA.setTranslateY(M_Y);

        // Ellipse
        objectB = new Ellipse(0, 0, 20, 12);
        //position
        double bStartX = (M_X + P_X) / 2.0;
        double bStartY = (M_Y + P_Y) / 2.0;
        objectB.setTranslateX(bStartX);
        objectB.setTranslateY(bStartY);


        // Build rectangular Path M->N->P->Q->M
        Path rectPath = new Path();
        rectPath.getElements().addAll(
            new MoveTo(M_X, M_Y),
            new LineTo(N_X, N_Y),
            new LineTo(P_X, P_Y),
            new LineTo(Q_X, Q_Y),
            new LineTo(M_X, M_Y)
        );
        
        rectPath.setStroke(javafx.scene.paint.Color.GRAY);
        rectPath.setStrokeWidth(2);
        rectPath.setOpacity(0.6);
        rectPath.setFill(null);

        // PathTransition for object A
        pathTransitionA = new PathTransition(Duration.seconds(TOTAL_SECONDS), rectPath, objectA);
        pathTransitionA.setCycleCount(1);
        pathTransitionA.setInterpolator(Interpolator.LINEAR);

        // Sequential animation B: Fade (M->N), Scale (N->P), Rotate (P->Q), Translate up (Q->M)
        double segmentSeconds = TOTAL_SECONDS / 4.0; // equal segments

        FadeTransition fade = new FadeTransition(Duration.seconds(segmentSeconds), objectB);
        fade.setFromValue(1.0);
        fade.setToValue(0.2);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(segmentSeconds), objectB);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.8);
        scale.setToY(1.8);

        RotateTransition rotate = new RotateTransition(Duration.seconds(segmentSeconds), objectB);
        rotate.setByAngle(360);

        // Translate upward by some pixels
        TranslateTransition translate = new TranslateTransition(Duration.seconds(segmentSeconds), objectB);
        translate.setByY(-80); // move upward

        sequentialB = new SequentialTransition(fade, scale, rotate, translate);

        // When both animations finish: small pause, then terminate
        endDelay = new PauseTransition(Duration.seconds(2));
        endDelay.setOnFinished(e -> Platform.exit());

        // Buttons
        Button startBtn = new Button("Start");
        Button resetBtn = new Button("Reset");
        Button exitBtn = new Button("Exit");

        startBtn.setOnAction(e -> startAnimations());

        resetBtn.setOnAction(e -> resetAnimations());

        exitBtn.setOnAction(e -> Platform.exit());

        HBox controls = new HBox(10, startBtn, resetBtn, exitBtn);
        
        animationPane.getChildren().add(rectPath);
        animationPane.getChildren().addAll(objectA, objectB);

        BorderPane root = new BorderPane();
        root.setCenter(animationPane);
        root.setBottom(controls);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();

        // prepare finishing chain: when pathTransition finishes, ensure sequential is finished too
        pathTransitionA.setOnFinished(evt -> {
            // if sequential still running, wait for it (but by design durations match)
            if (sequentialB.getStatus() != Animation.Status.STOPPED) {
                // do nothing here; let sequential finish
            }
            endDelay.play();
        });

        // Also set sequential finished handler to play endDelay if path already finished
        sequentialB.setOnFinished(evt -> {
            if (pathTransitionA.getStatus() == Animation.Status.STOPPED) {
                endDelay.play();
            }
        });
    }

    private void startAnimations() {
        resetNodePropertiesForStart();

        // Play both animations 
        pathTransitionA.playFromStart();
        sequentialB.playFromStart();
    }

    private void resetAnimations() {
        // Stop animations
        pathTransitionA.stop();
        sequentialB.stop();
        endDelay.stop();

        // Reset object A to M
        objectA.setTranslateX(M_X);
        objectA.setTranslateY(M_Y);
        objectA.setOpacity(1.0);
        objectA.setRotate(0);
        objectA.setScaleX(1.0);
        objectA.setScaleY(1.0);

        // Reset object B to initial properties
        double bStartX = (M_X + P_X) / 2.0;
        double bStartY = (M_Y + P_Y) / 2.0;
        objectB.setTranslateX(bStartX);
        objectB.setTranslateY(bStartY);
        objectB.setOpacity(1.0);
        objectB.setRotate(0);
        objectB.setScaleX(1.0);
        objectB.setScaleY(1.0);
    }

    private void resetNodePropertiesForStart() {
        
        objectB.setOpacity(1.0);
        objectB.setRotate(0);
        objectB.setScaleX(1.0);
        objectB.setScaleY(1.0);
        
        double bStartX = (M_X + P_X) / 2.0;
        double bStartY = (M_Y + P_Y) / 2.0;
        objectB.setTranslateX(bStartX);
        objectB.setTranslateY(bStartY);

    
        objectA.setTranslateX(M_X);
        objectA.setTranslateY(M_Y);
    }
}
