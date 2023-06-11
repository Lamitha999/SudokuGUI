package com.example.scenebuilder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    Rectangle square;
    String[][] gridMatrix;
    int lives = 3;

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        //set window properties
        int windowHeight = 370;
        int windowWidth = 600;

        int lives = 3;

        gridMatrix = new String[9][9];
        gridMatrix = createGridMatrix();

        square = new Rectangle(30, 30);
        square.setFill(Color.RED);

        square.setTranslateX(30);
        square.setTranslateY(70);

    //defining all needed groups for the window
        Group root = new Group();//main window
        Group grid = createGrid();//sudoku lines

    //Defining the heading of the Window
        Text heading = new Text(
                20,
                50,
                "Who will become sudocillionair?");
        heading.setFont(Font.font("Helvetica", 36));

    //Adding components to the main group
        root.getChildren().add(grid);
        root.getChildren().add(heading);
        root.getChildren().add(square);
        root.getChildren().add(createLivesBox());
        root.getChildren().add(createNumberFields());
        root.getChildren().add(createQuestionBox());

    //creating new window from the root instance
        Scene scene = new Scene(root, windowWidth, windowHeight);
        scene.setOnKeyPressed(this::handleKeyPress);

    // setting properties for the window
        stage.setResizable(false);
        stage.setTitle("who will become sudocillionair?");
        stage.setScene(scene);
        //show the application
        stage.show();
    }

    /**
     *
     * @return 9x9 Grid
     */
    public Group createGrid() {
        //setting grid size
        int gridHeight = 270;
        int gridWidth = 270;
        Group grid = new Group();

        //vertical lines and location
        for (int i = 0; i <= 9; i++) {
            Line line = new Line(i * gridWidth / 9 + 30, 70, i * gridWidth / 9 + 30, gridHeight + 70);
            if(i%3 == 0){
                line.setStrokeWidth(1.5);
            }
            grid.getChildren().add(line);
        }
        //horizontal lines and location
        for (int i = 0; i <= 9; i++) {
            Line line = new Line(30, i * gridHeight / 9 + 70, gridWidth + 30, i * gridHeight / 9 + 70);
            if(i%3 == 0){
                line.setStrokeWidth(1.5);
            }
            grid.getChildren().add(line);
        }
        return grid;
    }

    /**
     *
     * @param event
     */
    private void handleKeyPress(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        double stepSize = 30;

        // Move the square based on the arrow key pressed
        if (keyCode == KeyCode.LEFT) {
            if (square.getTranslateX() == 30) {
                return;
            }
            square.setTranslateX(square.getTranslateX() - stepSize);
        } else if (keyCode == KeyCode.RIGHT) {
            if (square.getTranslateX() == 270) {
                return;
            }
            square.setTranslateX(square.getTranslateX() + stepSize);
        } else if (keyCode == KeyCode.UP) {
            if (square.getTranslateY() == 70) {
                return;
            }
            square.setTranslateY(square.getTranslateY() - stepSize);
        } else if (keyCode == KeyCode.DOWN) {
            if (square.getTranslateY() == 310) {
                return;
            }
            square.setTranslateY(square.getTranslateY() + stepSize);
            // numbers command on the keyboard
        } else {
            String keyText = event.getText();
            // get the position of the keyboard commands

            int xPos = (int)(getActiveXposition()-30)%30;
            int yPos = (int)(getActiveYposition()-70)%30;




            if (keyText.matches("[1-9]")) {
                gridMatrix[xPos][yPos] = keyText;
                printMatrix(gridMatrix);
            }
        }
    }
    //debugging
    public void printMatrix(String[][] matrix){
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                System.out.print(matrix[i][j] + " ");
                if(j == 8){
                    System.out.print("\n");
                }
            }
        }
    }

    /**
     *
     * @return Livebox
     */
    public Group createLivesBox(){
        Group lifes = new Group();

        Rectangle box = new Rectangle(230, 60);
        box.setTranslateX(340);
        box.setTranslateY(70);
        box.setFill(Color.GRAY);

        Text lifesText = new Text(350, 105, "Lifes remaining: " + Integer.toString(lives));
        lifesText.setFont(Font.font("Helvetica", 20));


        lifes.getChildren().add(box);
        lifes.getChildren().add(lifesText);
        return lifes;
    }


    /**
     *
     * @return Quesionbox
     */

    public Group createQuestionBox() {
        Group question = new Group();

        Rectangle box2 = new Rectangle(230, 180);
        box2.setTranslateX(340);
        box2.setTranslateY(160);
        box2.setFill(Color.GRAY);

        Text questionText = new Text(350, 200, "Question: " + getRandomQuestion());
        questionText.setFont(Font.font("Helvetica", 20));
        questionText.setWrappingWidth(230);

        TextField answerField = new TextField();
        answerField.setTranslateX(350);
        answerField.setTranslateY(270);
        answerField.setPrefWidth(200);

        question.getChildren().addAll(box2, questionText, answerField);
        return question;
    }

    /**
     *
     * @return random question
     */
    private String getRandomQuestion() {
        List<String> questions = new ArrayList<>();
        questions.add("What is the capital of France?");
        questions.add("Who painted the Mona Lisa?");
        questions.add("What is the square root of 144?");
        // Add more questions!

        Random random = new Random();
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }


    /**
     *
     * @return 2D Grid
     */
    public String[][] createGridMatrix() {
        String[][] grid = new String[9][9];

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                grid[i][j] = "0";
            }
        }
        return grid;
    }

    /**
     *
     * @return Number from the matrix will be printed on the sudoku
     */
    public Group createNumberFields(){
        Group numbers = new Group();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                String num = gridMatrix[i][j];

                int fieldSize = 30;

                int offsetY = 70 + 20;
                int offsetX = 30 + fieldSize/3;

                Text number = new Text(
                        offsetX + fieldSize * j,
                        offsetY + fieldSize * i,
                        num);
                number.setFont(Font.font("Helvetica", 16));
                numbers.getChildren().add(number);
            }
        }
        return numbers;
    }
    // X and Y from the activefield will be retrieved
    public double getActiveXposition(){
        return square.getTranslateX();
    }

    public double getActiveYposition(){
        return square.getTranslateY();
    }

    public static void main (String[]args){
        launch();
    }
}