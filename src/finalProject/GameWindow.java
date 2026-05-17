package finalProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameWindow extends Application {
    private Circle[][] circles = new Circle[6][7];

    public void start(Stage ps) {
        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);

        Label title = new Label("Connect 4");
        Label turnLabel = new Label("Yellow's Turn");
        Label messageLabel = new Label("Game messages will appear here.");

        Button startButton = new Button("Start Game");
        Button questionButton = new Button("Question");

        GridPane buttonRow = new GridPane();
        buttonRow.setHgap(20);

        Button col0 = new Button("0");
        Button col1 = new Button("1");
        Button col2 = new Button("2");
        Button col3 = new Button("3");
        Button col4 = new Button("4");
        Button col5 = new Button("5");
        Button col6 = new Button("6");

        buttonRow.add(col0, 0, 0);
        buttonRow.add(col1, 1, 0);
        buttonRow.add(col2, 2, 0);
        buttonRow.add(col3, 3, 0);
        buttonRow.add(col4, 4, 0);
        buttonRow.add(col5, 5, 0);
        buttonRow.add(col6, 6, 0);

        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);

        for (int row = 0; row < circles.length; row++) {
            for (int col = 0; col < circles[row].length; col++) {
                Circle c = new Circle(25);
                c.setFill(Color.WHITE);
                c.setStroke(Color.BLACK);

                circles[row][col] = c;

                boardGrid.add(c, col, row);
            }
        }

        GridPane questionArea = new GridPane();
        questionArea.setHgap(10);
        questionArea.setVgap(10);

        Label questionTitle = new Label("Question Area");
        Label questionText = new Label("Question text will go here.");

        Button answerA = new Button("Answer A");
        Button answerB = new Button("Answer B");
        Button answerC = new Button("Answer C");
        Button answerD = new Button("Answer D");

        questionArea.add(questionTitle, 0, 0);
        questionArea.add(questionText, 0, 1);
        questionArea.add(answerA, 0, 2);
        questionArea.add(answerB, 1, 2);
        questionArea.add(answerC, 0, 3);
        questionArea.add(answerD, 1, 3);

        root.add(title, 0, 0);
        root.add(startButton, 1, 0);
        root.add(questionButton, 2, 0);

        root.add(turnLabel, 0, 1);
        root.add(buttonRow, 0, 2);
        root.add(boardGrid, 0, 3);
        root.add(messageLabel, 0, 4);
        root.add(questionArea, 0, 5);

        Scene scene = new Scene(root, 650, 650);

        ps.setTitle("Connect 4");
        ps.setScene(scene);
        ps.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}