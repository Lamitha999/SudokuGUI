package com.example.scenebuilder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MoveableSquare extends Application {
    private static final int SQUARE_SIZE = 50;
    private Rectangle square;
    private ArrayList<Integer> numbers = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create the square
        square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        square.setFill(Color.BLUE);

        // Set the initial position of the square
        double initialX = 100;
        double initialY = 100;
        square.setTranslateX(initialX);
        square.setTranslateY(initialY);

        // Create the scene
        Group root = new Group(square);
        Scene scene = new Scene(root, 400, 400);

        // Set up keyboard event handling
        scene.setOnKeyPressed(this::handleKeyPress);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        double stepSize = 10.0;

        // Move the square based on the arrow key pressed
        if (keyCode == KeyCode.LEFT) {
            square.setTranslateX(square.getTranslateX() - stepSize);
        } else if (keyCode == KeyCode.RIGHT) {
            square.setTranslateX(square.getTranslateX() + stepSize);
        } else if (keyCode == KeyCode.UP) {
            square.setTranslateY(square.getTranslateY() - stepSize);
        } else if (keyCode == KeyCode.DOWN) {
            square.setTranslateY(square.getTranslateY() + stepSize);
        } else {
            // Check if the pressed key is a numerical key
            String keyText = event.getText();
            if (keyText.matches("[0-9]")) {
                int number = Integer.parseInt(keyText);
                numbers.add(number);
                System.out.println("Added number: " + number);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}