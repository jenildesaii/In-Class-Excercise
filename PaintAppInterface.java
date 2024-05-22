package com.example.lab03;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * PaintAppInterface is a simple JavaFX application that allows users to draw shapes (circles and squares)
 * on a canvas using different colors. The application uses functional interfaces to handle shape drawing,
 * allowing for flexible and reusable code.
 *
 * @author jenil
 */
public class PaintAppInterface extends Application {

    // The GraphicsContext used for drawing on the canvas
    private GraphicsContext gc;
    // The ColorPicker for selecting the color to fill the shapes
    private ColorPicker colorPicker;
    // The ShapeDrawer functional interface for drawing shapes
    private ShapeDrawer shapeDrawer;

    /**
     * Sets up the primary stage and initializes the user interface.
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error during setup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Create a Pane as the root layout
        Pane root = new Pane();
        // Create a Scene with the specified dimensions and set it to the stage
        Scene scene = new Scene(root, 400, 225);
        stage.setTitle("Paint App with Functional Interfaces");
        stage.setScene(scene);

        // Create a Canvas for drawing
        Canvas canvas = new Canvas(400, 225);
        // Get the GraphicsContext from the canvas
        gc = canvas.getGraphicsContext2D();
        // Set the background color of the canvas
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, 400, 225);

        // Create a ColorPicker for selecting the fill color
        colorPicker = new ColorPicker();

        // Create a Button for selecting the Circle shape
        Button circleButton = new Button("Circle");
        // Set the action for the Circle button using a lambda expression to define the shapeDrawer
        circleButton.setOnAction(e -> shapeDrawer = (x, y) -> {
            // Get the selected color from the color picker
            Color fillColor = colorPicker.getValue();
            // Set the fill color and draw the circle
            gc.setFill(fillColor);
            gc.fillOval(x - 10, y - 10, 20, 20);
            // Set the stroke color and draw the circle border
            gc.setStroke(Color.BLACK);
            gc.strokeOval(x - 10, y - 10, 20, 20);
        });

        // Create a Button for selecting the Square shape
        Button squareButton = new Button("Square");
        // Set the action for the Square button using a lambda expression to define the shapeDrawer
        squareButton.setOnAction(e -> shapeDrawer = (x, y) -> {
            // Get the selected color from the color picker
            Color fillColor = colorPicker.getValue();
            // Set the fill color and draw the square
            gc.setFill(fillColor);
            gc.fillRect(x - 10, y - 10, 20, 20);
            // Set the stroke color and draw the square border
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x - 10, y - 10, 20, 20);
        });

        // Add the canvas, color picker, and buttons to the root layout
        root.getChildren().addAll(canvas, colorPicker, circleButton, squareButton);

        // Set the positions of the color picker and buttons
        colorPicker.setLayoutX(320);
        colorPicker.setLayoutY(10);
        circleButton.setLayoutX(10);
        circleButton.setLayoutY(10);
        squareButton.setLayoutX(60);
        squareButton.setLayoutY(10);

        // Add a mouse event handler to the canvas for drawing shapes using a lambda expression
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, me -> {
            // Draw the selected shape when the primary mouse button is pressed
            if (me.getButton() == MouseButton.PRIMARY && shapeDrawer != null) {
                shapeDrawer.draw(me.getX(), me.getY());
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Functional interface for drawing shapes.
     */
    @FunctionalInterface
    interface ShapeDrawer {
        void draw(double x, double y);
    }
}
