
 import javafx.application.Application;
 import javafx.stage.Stage;
 import javafx.scene.Scene;
 import javafx.scene.layout.Pane;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Rectangle;
 import javafx.scene.shape.Line;
 import javafx.scene.shape.Circle;

 public class Task_1 extends Application {
    public static void main(String[] args)
        {
            launch(args);
        }

    @Override
    public void start(Stage primaryStage) {
        // Constants for the scene size
        final double SCENE_WIDTH = 520.0;
        final double SCENE_HEIGHT = 520.0;

        // Constants for each square's XY coordinates
        final int X1 = 10, Y1 = 10; // Square #1
        final int X2 = 60, Y2 = 60; // Square #1
        final int X3 = 110, Y3 = 110; // Square #3

        // Constants for each square's width and height
        final int WIDTH1 = 500, HEIGHT1 = 500; // Square #1
        final int WIDTH2 = 400, HEIGHT2 = 400; // Square #2
        final int WIDTH3 = 300, HEIGHT3 = 300; // Square #3

        // Constants for the circle's geometry
        final int CENTER_X = 260, CENTER_Y = 260, RADIUS = 150;
        
        
        // Create square #1 here. Set its stroke color to black
        // and set its fill color to null.
        Rectangle square_one = new Rectangle(X1,Y1,WIDTH1,HEIGHT1);
        square_one.setStroke(Color.BLACK);
        square_one.setFill(null);
        

        // Create square #2 here. Set its stroke color to black
        // and set its fill color to null.
        Rectangle square_two = new Rectangle(X2,Y2,WIDTH2,HEIGHT2);
        square_two.setStroke(Color.BLACK);
        square_two.setFill(null);
        
        
        // Create square #3 here. Set its stroke color to black
        // and set its fill color to null.
        Rectangle square_three = new Rectangle(X3,Y3,WIDTH3,HEIGHT3);
        square_three.setStroke(Color.BLACK);
        square_three.setFill(null);
        
        // Create the diagonal lines here.
        Line diagline_one = new Line(X1,Y1,X3,Y3);
        Line diagline_two= new Line(X1 + WIDTH1, Y1, (X3 + WIDTH3), Y3);
        Line diagline_three = new Line(X1, (Y1 + HEIGHT1), X3, (Y3 + HEIGHT3));
        Line diagline_four = new Line((X1 + WIDTH1),(Y1 + HEIGHT1), (X3 + WIDTH3), (Y3 + HEIGHT3));
        
        // Create the circle here.
        Circle circle = new Circle();
        circle.setCenterX(CENTER_X);
        circle.setCenterY(CENTER_Y);
        circle.setRadius(RADIUS);
        circle.setFill(Color.BLACK);
        // Add the nodes to a Pane here.
        Pane root = new Pane();
        root.getChildren().add(square_one);
        root.getChildren().add(square_two);
        root.getChildren().add(square_three);
        root.getChildren().add(circle);
        
        root.getChildren().add(diagline_one);
        root.getChildren().add(diagline_two);
        root.getChildren().add(diagline_three);
        root.getChildren().add(diagline_four);
        
        // Create a Scene with the Pane as the root node,
        // and display it here.
       
        Scene scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 }

