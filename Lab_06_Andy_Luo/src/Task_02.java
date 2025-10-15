
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luoan
 */
public class Task_02 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Rectangle base_of_house = new Rectangle(150, 200 , 200, 150);
        Polygon roof = new Polygon ();
        roof.getPoints().addAll(
                140.0, 200.0,
                260.0, 100.0,
                380.0, 200.0);
        
        
        // Door
        Rectangle door = new Rectangle(230, 290, 40, 60);
        door.setFill(Color.BROWN);
        door.setStroke(Color.BLACK);

        // Windows
        Rectangle leftWindow = new Rectangle(170, 230, 50, 50);
        leftWindow.setFill(Color.SKYBLUE);
        leftWindow.setStroke(Color.BLACK);

        Rectangle rightWindow = new Rectangle(280, 230, 50, 50);
        rightWindow.setFill(Color.SKYBLUE);
        rightWindow.setStroke(Color.BLACK);

        // Add horizontal and vertical window panes
        Line leftPaneH = new Line(170, 255, 220, 255);
        Line leftPaneV = new Line(195, 230, 195, 280);
        Line rightPaneH = new Line(280, 255, 330, 255);
        Line rightPaneV = new Line(305, 230, 305, 280);

        leftPaneH.setStroke(Color.WHITE);
        leftPaneV.setStroke(Color.WHITE);
        rightPaneH.setStroke(Color.WHITE);
        rightPaneV.setStroke(Color.WHITE);

        // Chimney
        Rectangle chimney = new Rectangle(255, 130, 20, 50);
        chimney.setFill(Color.CRIMSON);
        chimney.setStroke(Color.BLACK);

        // Sun
        Circle sun = new Circle(420, 80, 30);
        sun.setFill(Color.YELLOW);
        sun.setStroke(Color.ORANGE);

        // Grass
        Rectangle grass = new Rectangle(100, 350, 300, 40);
        grass.setFill(Color.GREEN);
        grass.setStroke(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().addAll(roof, base_of_house, door,leftWindow,rightWindow,
                leftPaneV,rightPaneH,leftPaneH,rightPaneV,chimney,sun, grass);
        
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
}
