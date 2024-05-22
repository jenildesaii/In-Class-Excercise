package com.example.lab03;


import javafx.application.Application;
import javafx.event.ActionEvent;
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
 * PaintAppAnonymous is a simple JavaFX application that allows users to draw shapes (circles and squares)
 * on a canvas using different colors. The application uses anonymous inner classes to handle button clicks
 * and mouse events for drawing shapes.
 *
 * @author jenil
 */
public class PaintAppAnonymous extends Application {

    // The GraphicsContext used for drawing on the canvas
    private GraphicsContext gc;
    // The ColorPicker for selecting the color to fill the shapes
    private ColorPicker colorPicker;
    // The currently selected shape to draw ("Circle" or "Square")
    private String selectedShape = "Circle";

    /**
     *
     * Draws the selected shape at the specified coordinates.
     * @param x The x-coordinate where the shape will be drawn.
     * @param y The y-coordinate where the shape will be drawn.
     */
    public void drawShape(double x, double y) {
        // Get the selected color from the color picker
        Color fillColor = colorPicker.getValue();
        // Draw the selected shape at the specified coordinates
        switch (selectedShape) {
            case "Circle":
                // Set the fill color and draw the circle
                gc.setFill(fillColor);
                gc.fillOval(x - 10, y - 10, 20, 20);
                // Set the stroke color and draw the circle border
                gc.setStroke(Color.BLACK);
                gc.strokeOval(x - 10, y - 10, 20, 20);
                break;
            case "Square":
                // Set the fill color and draw the square
                gc.setFill(fillColor);
                gc.fillRect(x - 10, y - 10, 20, 20);
                // Set the stroke color and draw the square border
                gc.setStroke(Color.BLACK);
                gc.strokeRect(x - 10, y - 10, 20, 20);
                break;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create a Pane as the root layout
        Pane root = new Pane();
        // Create a Scene with the specified dimensions and set it to the stage
        Scene scene = new Scene(root, 400, 225);
        stage.setTitle("Paint App with Anonymous Inner Classes");
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
        // Set the action for the Circle button using an anonymous inner class
        circleButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedShape = "Circle";
            }
        });

        // Create a Button for selecting the Square shape
        Button squareButton = new Button("Square");
        // Set the action for the Square button using an anonymous inner class
        squareButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedShape = "Square";
            }
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

        // Add a mouse event handler to the canvas for drawing shapes
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                // Draw the selected shape when the primary mouse button is pressed
                if (me.getButton() == MouseButton.PRIMARY) {
                    drawShape(me.getX(), me.getY());
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
