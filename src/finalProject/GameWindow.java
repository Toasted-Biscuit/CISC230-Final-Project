package finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameWindow extends Application {
    private Circle[][] circles = new Circle[6][7];
    private Connect4 game = new Connect4();
    private Label turnLabel;
    Button[] chipButtons;
    Button startButton;
    private Label questionText;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Button questionButton;
    private boolean questionActive = false;
    private int correctAnswer;
    
  
    //this keeps track of which question we are currently showing
    private int questionIndex = 0;

    /*
     * These are the questions for the sustainability trivia part of the game.
     * The matching answer choices are stored in the answers array below.
     */
    private String[] questions = {
        "Which of the following is NOT a way to conserve energy?",
        "What is the name of the series of gardens around campus that attract pollinators?",
        "Where is there a bike repair station located on campus?",
        "Which of the following is NOT a helpful tip for reducing food waste?",
        "Which of the following is NOT a way to conserve water?",
        "The Sustainability minor is available to students in which fields?",
        "Which of the following are clubs at St. Thomas?",
        "Dining Services reduces waste by...",
        "At St. Thomas, which item is accepted in the blue recycling bins?",
        "All of the following are accepted for organics recycling EXCEPT..."
    };

    /*
     * Each row belongs to one question.
     * Index 0 = Answer A
     * Index 1 = Answer B
     * Index 2 = Answer C
     * Index 3 = Answer D
     */
    private String[][] answers = {
        {
            "A. Unplugging devices when not in use",
            "B. Turning off lights when leaving the room",
            "C. Keeping windows closed in winter",
            "D. Washing laundry in small loads"
        },
        {
            "A. Bee Garden",
            "B. Pollinator Path",
            "C. Butterfly Garden",
            "D. Bee Path"
        },
        {
            "A. South side of O'Shaughnessy Stadium",
            "B. Bike storage in Frey Residence Hall",
            "C. Bike storage in Schoenecker Hall North",
            "D. All of the above"
        },
        {
            "A. Storing produce properly",
            "B. Planning meals at the beginning of the week",
            "C. Only purchasing what you need",
            "D. Storing all items in the refrigerator"
        },
        {
            "A. Eating more plant-based meals",
            "B. Taking shorter showers",
            "C. Leaving the sink on while brushing teeth",
            "D. Only washing full loads of laundry"
        },
        {
            "A. Arts and Sciences",
            "B. Engineering",
            "C. Business",
            "D. All of the above and more"
        },
        {
            "A. Sustainability Club",
            "B. Earth, Environment, and Society Club",
            "C. Tommie Outdoors",
            "D. All of the above"
        },
        {
            "A. Recovering leftover food",
            "B. Offering reusable to-go containers",
            "C. Offering reusable cup discounts",
            "D. All of the above"
        },
        {
            "A. Glass",
            "B. Lightbulbs",
            "C. Plastic bags",
            "D. Batteries"
        },
        {
            "A. All food scraps",
            "B. Napkins",
            "C. All paper cups",
            "D. Flower trimmings"
        }
    };

    /*
     * These are the correct answers.
     * 0 = A
     * 1 = B
     * 2 = C
     * 3 = D
     */
    private int[] correctAnswers = {
        3, // D
        1, // B
        3, // D
        3, // D
        2, // C
        3, // D
        3, // D
        3, // D
        0, // A
        2  // C
    };
    
    public void start(Stage ps) {
        GridPane root = new GridPane();
        root.setAlignment(javafx.geometry.Pos.CENTER); //looked this up
        root.setHgap(15);
        root.setVgap(15);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;"); //had to look this up

        //Title and control Buttons
        Label title = new Label("Connect 4");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;"); //looked up
        turnLabel = new Label("Yellow's Turn");
        turnLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;"); //looked up
        Label messageLabel = new Label("Game messages will appear here.");

        startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;"); //looked up
        startButton.setDisable(false);
        startButton.setOnAction(this::startButtonAction);
        questionButton = new Button("Question");
        questionButton.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;"); //looked up
        questionButton.setDisable(true);
        questionButton.setOnAction(this::askQuestion1);

        GridPane topControls = new GridPane();
        topControls.setHgap(15);
        topControls.setAlignment(javafx.geometry.Pos.CENTER);
        topControls.add(startButton, 0, 0);
        topControls.add(questionButton, 1, 0);
        
        GridPane buttonRow = new GridPane();
        buttonRow.setHgap(5);
        buttonRow.setAlignment(javafx.geometry.Pos.CENTER);
        
        // An array full of buttons meant to place chips when pressed
        chipButtons = new Button[7];
        for (int i = 0; i < chipButtons.length; i++) {
        	chipButtons[i] = new Button("▼");
        	chipButtons[i].setUserData(i);
        	chipButtons[i].setOnAction(this::placeChip);
        	chipButtons[i].setMinWidth(50);
        	chipButtons[i].setPrefWidth(50);
        	chipButtons[i].setMinHeight(35);
        	chipButtons[i].setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1; -fx-border-color: #95a5a6; -fx-border-width: 2;");
        	chipButtons[i].setDisable(true);
        	buttonRow.add(chipButtons[i], i, 0);
        }

        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);
        boardGrid.setStyle("-fx-padding: 15; -fx-background-color: #3498db; -fx-background-radius: 10;");
        boardGrid.setAlignment(javafx.geometry.Pos.CENTER);

        for (int row = 0; row < circles.length; row++) {
            for (int col = 0; col < circles[row].length; col++) {
                Circle c = new Circle(25);
                c.setFill(Color.WHITE);
                c.setStroke(Color.DARKGRAY);

                circles[row][col] = c;

                boardGrid.add(c, col, row);
            }
        }

        GridPane questionArea = new GridPane();
        questionArea.setAlignment(javafx.geometry.Pos.CENTER);
        questionArea.setHgap(10);
        questionArea.setVgap(10);
        questionArea.setStyle("-fx-padding: 15; -fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-width: 2; -fx-border-radius: 5;");

        Label questionTitle = new Label("Question Area");
        questionTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        questionText = new Label("Question text will go here.");

        questionText = new Label("Click 'Question' to begin");
        questionText.setStyle("-fx-font-size: 14px;");
        questionText.setWrapText(true);
        questionText.setMaxWidth(400);
        
     
        answerA = new Button("Answer A");
        answerB = new Button("Answer B");
        answerC = new Button("Answer C");
        answerD = new Button("Answer D");
        
        
        String answerButtonStyle = "-fx-min-width: 150; -fx-padding: 10; -fx-font-size: 13px;";
        answerA.setStyle(answerButtonStyle);
        answerB.setStyle(answerButtonStyle);
        answerC.setStyle(answerButtonStyle);
        answerD.setStyle(answerButtonStyle);
        
        answerA.setUserData(0);
        answerB.setUserData(1);
        answerC.setUserData(2);
        answerD.setUserData(3);
        
        setQuetionsDisabled();
        
        answerA.setOnAction(this::checkAnswer);
        answerB.setOnAction(this::checkAnswer);
        answerC.setOnAction(this::checkAnswer);
        answerD.setOnAction(this::checkAnswer);
        
        questionArea.add(questionTitle, 0, 0);
        questionArea.add(questionText, 0, 1);
        questionArea.add(answerA, 0, 2);
        questionArea.add(answerB, 1, 2);
        questionArea.add(answerC, 0, 3);
        questionArea.add(answerD, 1, 3);

        root.add(title, 0, 0);
        root.add(topControls, 0, 1);
        root.add(turnLabel, 0, 2);
        root.add(buttonRow, 0, 3);
        root.add(boardGrid, 0, 4);
        root.add(questionArea, 0, 5);

    
        Scene scene = new Scene(root, 700, 850);

        ps.setTitle("Connect 4");
        ps.setScene(scene);
        ps.show();
    }
    
    // When the chip buttons are pressed, places a chip in the column assigned to the button
    public void placeChip(ActionEvent e) {
    	questionButton.setDisable(false);
    
    	int col = (int)((Button)e.getSource()).getUserData();
    	game.placeChip(col);
    	updateBoard();
    	
    	if (game.getTurn() == 1) {
    		turnLabel.setText("Yellow's Turn");
    	} else {
    		turnLabel.setText("Red's Turn");
    	}
    	
    	int winner = game.checkWinner();
    	// TODO Replace println with gui visuals and make it stop the game
    	if (winner == Connect4.YELLOW) {
    		System.out.println("Yellow Wins!!!");
    		setButtonsDisabled();
    		setQuetionsDisabled();
    		questionButton.setDisable(true);
    	} else if (winner == Connect4.RED) {
    		System.out.println("Red Wins!!!");
    		setButtonsDisabled();
    		setQuetionsDisabled();
    		questionButton.setDisable(true);
    	}
    	setButtonsDisabled();
    }
    
    // Updates the onscreen board to reflect the game board
    public void updateBoard() {
  
    	int[][] board = game.getBoard();
    	
    	for (int r = 0; r < board.length; r++) {
    		for (int c = 0; c < board[r].length; c++) {
    			if (board[r][c] == Connect4.YELLOW) {
    				circles[r][c].setFill(Color.YELLOW);
    			} else if (board[r][c] == Connect4.RED) {
    				circles[r][c].setFill(Color.RED);
    			} else {
    				circles[r][c].setFill(Color.WHITE);
    			}
    		}
    	}
    }
    
    
    public void startButtonAction(ActionEvent evnet) {
    	startButton.setDisable(true);
    	setQuetionsDisabled();
    	questionButton.setDisable(false);
    	
    }
    
    public void setButtonsDisabled() {
    	for (Button button : chipButtons) {
    		button.setDisable(true);
    	}
    }
    public void setButtonsEnabled() {
    	for (Button button : chipButtons) {
    		button.setDisable(false);
    	}
    }
    public void askQuestion1(ActionEvent event) {
    	askQuestion();
    	questionButton.setDisable(true);
    }
    
    public void askQuestion() {
    	//this will show the current question on the screen
    	questionText.setText(questions[questionIndex]);
    	
    	//this will update the four answer buttons based on the current question
    	answerA.setText(answers[questionIndex][0]);
    	answerB.setText(answers[questionIndex][1]);
    	answerC.setText(answers[questionIndex][2]);
    	answerD.setText(answers[questionIndex][3]);
    	
    	//this stores which answer is correct for the current question
    	correctAnswer = correctAnswers[questionIndex];
    	
    	//after showing the question, the player should be able to choose an answer
    	setQuetionsEnabled();
    	
    	//move to the next question for next time
    	questionIndex++;
    	
    	//if we reach the end of the question list, start from the first question again
    	if (questionIndex == questions.length) {
    		questionIndex = 0;
    	}
    }
    
    public void checkAnswer(ActionEvent event) {
    	int selectedAnswer = (int)((Button)event.getSource()).getUserData();
    	setQuetionsDisabled();
    	if (selectedAnswer == correctAnswer) {
    		questionText.setText("Correct, Now place your chip.");
    		setButtonsEnabled();
    		questionActive = false;
    	} else {
    		questionText.setText("Wrong! Next players turn.");
    		game.changeTurn();
    		questionButton.setDisable(false);
    		
    		if(game.getTurn() == Connect4.YELLOW) {
    			turnLabel.setText("Yellow's Turn");
    		}else {
    			turnLabel.setText("Red's Turn");
    		}
    	}
    }
    
    public void setQuetionsDisabled() {
    	answerA.setDisable(true);
    	answerB.setDisable(true);
    	answerC.setDisable(true);
    	answerD.setDisable(true);
    }
    
    public void setQuetionsEnabled() {
    	answerA.setDisable(false);
    	answerB.setDisable(false);
    	answerC.setDisable(false);
    	answerD.setDisable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}