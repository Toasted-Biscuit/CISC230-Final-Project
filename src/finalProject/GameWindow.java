package finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.media.AudioClip;

import java.io.File;

public class GameWindow extends Application {
    private Circle[][] circles = new Circle[6][7];
    private Connect4 game = new Connect4();
    private Label turnLabel;
    private Button[] chipButtons;
    private Button startButton;
    private Label questionText;
    private Text explanationText;
    private Button answerA, answerB, answerC, answerD;
    private Button questionButton;
    private Text Winner;
    private boolean questionActive = false;
    GridPane topControls;
    private AudioClip backgroundAudio;
    private AudioClip chipPlacedAudio;
    private Quiz question;
    
    private boolean gameOver = false;
    
    private Stage stage;
    private Scene gameScreen;
    private int mode;
    // Game modes (constants meant for code readability)
    private final int VERSUS = 0;
    private final int SINGLE_PLAYER = 1;
    
    public void start(Stage ps) {
    	backgroundAudio = new AudioClip(new File("BackgroundAudio.mp3").toURI().toString());
    	backgroundAudio.setVolume(0.25);
    	backgroundAudio.setCycleCount(AudioClip.INDEFINITE);
    	backgroundAudio.play();
    	
    	File chipPlacedAudioFile = new File("ChipPlacedAudio.mp3");
    	String chipPlacedaudioURI = chipPlacedAudioFile.toURI().toString();
    	chipPlacedAudio = new AudioClip(chipPlacedaudioURI);
    	chipPlacedAudio.setVolume(0.7);
    	
    	Winner = new Text("winner");
    	Winner.setFont(Font.font("", FontWeight.BOLD, 70));
    	Winner.setVisible(false);
    	stage = ps;
    	// TITLE SCREEN ----------------------
    	// Title and credits
    	Label titleLabel = new Label("Connect 4");
    	titleLabel.setFont(Font.font("", FontWeight.BOLD, 70));
    	Label credits = new Label("By: Kason, Kay, and Nathan");
    	credits.setFont(Font.font(20));
    	
    	// Game mode buttons
    	Button vsButton = new Button("Vs.");
    	vsButton.setPrefHeight(50);
    	vsButton.setPrefWidth(180);
    	vsButton.setFont(Font.font(20));
    	vsButton.setOnAction(this::vsClick);
    	
    	Button singlePlayerButton = new Button("Single Player");
    	singlePlayerButton.setPrefHeight(50);
    	singlePlayerButton.setPrefWidth(180);
    	singlePlayerButton.setFont(Font.font(20));
    	singlePlayerButton.setOnAction(this::singlePlayerClick);
    	
    	
    	GridPane titleGrid = new GridPane();
    	titleGrid.add(titleLabel, 0, 0);
    	titleGrid.add(credits, 0, 1);
    	titleGrid.add(singlePlayerButton, 0, 2);
    	titleGrid.add(vsButton, 0, 3);
    	
    	titleGrid.setVgap(20);
    	titleGrid.setAlignment(Pos.CENTER);
    	// Sets horizontal alignment for all nodes in titleGrid
    	for (Node n : titleGrid.getChildren()) {
    		GridPane.setHalignment(n, HPos.CENTER);;
    	}
    	
    	Scene titleScreen = new Scene(titleGrid, 700, 870);
    	
    	stage.setScene(titleScreen);
        ps.setTitle("Connect 4");
        ps.show();
    	
        
    	// GAME SCREEN -----------------------
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER); //looked this up
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
        questionButton.setOnAction(this::askQuestionClick);

        topControls = new GridPane();
        topControls.setVisible(true);
        topControls.setHgap(15);
        topControls.setAlignment(Pos.CENTER);
        topControls.add(startButton, 0, 0);
        topControls.add(questionButton, 1, 0);
        
        GridPane buttonRow = new GridPane();
        buttonRow.setHgap(5);
        buttonRow.setAlignment(Pos.CENTER);
        
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
        boardGrid.setAlignment(Pos.CENTER);

        for (int row = 0; row < circles.length; row++) {
            for (int col = 0; col < circles[row].length; col++) {
                Circle c = new Circle(25);
                c.setFill(Color.WHITE);
                c.setStroke(Color.DARKGRAY);

                circles[row][col] = c;

                boardGrid.add(c, col, row);
            }
        }

        // QUESTION UI ------------------------------
        GridPane questionArea = new GridPane();
        questionArea.setAlignment(Pos.CENTER);
        questionArea.setHgap(10);
        questionArea.setVgap(10);
        questionArea.setStyle("-fx-padding: 15; -fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-width: 2; -fx-border-radius: 5;");

        Label questionTitle = new Label("Question Area");
        questionTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        questionText = new Label("Question text will go here.");

        questionText = new Label("Click 'Question' to begin");
        questionText.setStyle("-fx-font-size: 14px;");
        questionText.setWrapText(true);
        questionText.setMaxWidth(600);
        questionText.setWrapText(true);
        
        explanationText = new Text("");
        explanationText.setWrappingWidth(questionArea.getMinWidth());
     
        answerA = new Button("Answer A");
        answerB = new Button("Answer B");
        answerC = new Button("Answer C");
        answerD = new Button("Answer D");
        
        
        String answerButtonStyle = "-fx-min-width: 300; -fx-padding: 10; -fx-font-size: 13px;";
        answerA.setStyle(answerButtonStyle);
        answerB.setStyle(answerButtonStyle);
        answerC.setStyle(answerButtonStyle);
        answerD.setStyle(answerButtonStyle);
        
        answerA.setUserData(0);
        answerB.setUserData(1);
        answerC.setUserData(2);
        answerD.setUserData(3);
        
        setQuestionsDisabled();
        
        answerA.setOnAction(this::checkAnswer);
        answerB.setOnAction(this::checkAnswer);
        answerC.setOnAction(this::checkAnswer);
        answerD.setOnAction(this::checkAnswer);
        
        questionArea.add(questionTitle, 0, 0);
        questionArea.add(questionText, 0, 1, 2, 1);
        questionArea.add(answerA, 0, 2);
        questionArea.add(answerB, 1, 2);
        questionArea.add(answerC, 0, 3);
        questionArea.add(answerD, 1, 3);
        questionArea.add(explanationText, 0, 4);

        root.add(title, 0, 0);
        root.add(Winner, 0, 1);
        root.add(topControls, 0, 1);
        root.add(turnLabel, 0, 2);
        root.add(buttonRow, 0, 3);
        root.add(boardGrid, 0, 4);
        root.add(questionArea, 0, 5);
        GridPane.setHalignment(Winner, javafx.geometry.HPos.CENTER); 

    
        gameScreen = new Scene(root, 700, 870);
    }
    
    // Switches the screen to the game UI and sets the mode to versus
    public void vsClick(ActionEvent e) {
    	stage.setScene(gameScreen);
    	mode = VERSUS;
    }
    
    // Switches to screen to the game UI and sets the mode to single player
    public void singlePlayerClick(ActionEvent e) {
    	stage.setScene(gameScreen);
    	mode = SINGLE_PLAYER;
    }
    
    // When the chip buttons are pressed, places a chip in the column assigned to the button
    public void placeChip(ActionEvent e) {
    	chipPlacedAudio.play();
    	int col = (int)((Button)e.getSource()).getUserData();
    	
    	// Only continues game if placed in open location
    	if (game.placeChip(col)) {
    		updateBoard();
    		
    		questionButton.setDisable(false);
    		
    		checkWin();
    		setButtonsDisabled();
    		
    		if (mode == SINGLE_PLAYER && !gameOver) {
    			botTurn();
    		}    		
    	}
    }
    
    public void checkWin() {
    	int winner = game.checkWinner();
    	Winner.setStroke(Color.BLACK);
    	Winner.setStrokeWidth(2);
    	AudioClip WinningAudio = new AudioClip(new File("WinningAudio.mp3").toURI().toString());
    	
    	// Yellow Wins
    	if (winner == Connect4.YELLOW) {
    		setButtonsDisabled();
    		setQuestionsDisabled();
    		questionButton.setDisable(true);
    		topControls.setVisible(false);
    		Winner.setText("Yellow Wins");
    		Winner.setFill(Color.YELLOW);
    		Winner.setVisible(true);
    		gameOver = true;
    		turnLabel.setVisible(false);
    		backgroundAudio.stop();
    		WinningAudio.setVolume(0.25);
    		WinningAudio.play();
    		
    		// Red wins
    	} else if (winner == Connect4.RED) {
    		setButtonsDisabled();
    		setQuestionsDisabled();
    		Winner.setText("Red Wins");
    		topControls.setVisible(false);
    		Winner.setFill(Color.RED);
    		Winner.setVisible(true);
    		turnLabel.setVisible(false);
    		questionButton.setDisable(true);
    		gameOver = true;
    		backgroundAudio.stop();
    		WinningAudio.setVolume(0.25);
    		WinningAudio.play();
    		
    		// Tie game
    	} else if (winner == Connect4.EMPTY) {
    		setButtonsDisabled();
    		setQuestionsDisabled();
    		topControls.setVisible(false);
    		turnLabel.setVisible(false);
    		Winner.setText("Its a tie");
    		Winner.setVisible(true);
    		
    		questionButton.setDisable(true);
    		gameOver = true;
    	}
    }
    
    public void botTurn() {
    	game.placeRandomChip();
    	updateBoard();
    	checkWin();
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
    	
    	if (game.getTurn() == 1) {
    		turnLabel.setText("Yellow's Turn");
    	} else {
    		turnLabel.setText("Red's Turn");
    	}
    }
    
    
    public void startButtonAction(ActionEvent evnet) {
    	startButton.setDisable(true);
    	setQuestionsDisabled();
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
    
    // 
    public void askQuestionClick(ActionEvent event) {
    	askQuestion();
    	questionButton.setDisable(true);
    	explanationText.setText("");
    }
    
    public void askQuestion() {
    	
    	// Gets a random quiz question
    	question = Quiz.getRandomQuestion();
    	
    	// Displays the current question
    	questionText.setText(question.getQuestion());
    	
    	// Displays the current answers
    	answerA.setText(question.getAnswers()[0]);
    	answerB.setText(question.getAnswers()[1]);
    	answerC.setText(question.getAnswers()[2]);
    	answerD.setText(question.getAnswers()[3]);
    	
    	
    	//after showing the question, the player should be able to choose an answer
    	setQuestionsEnabled();
    	
    }
    
    public void checkAnswer(ActionEvent event) {
    	int selectedAnswer = (int)((Button)event.getSource()).getUserData();
    	setQuestionsDisabled();
    	explanationText.setText(question.getExplanation());
    	colorAnswerButtons();
    	if (selectedAnswer == question.getCorrectAnswer()) {
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
    		
        	if (mode == SINGLE_PLAYER && !gameOver) {
        		botTurn();
        	}
    	}
    }
    
    public void colorAnswerButtons() {
    	answerA.setTextFill(Color.RED);
    	answerB.setTextFill(Color.RED);
    	answerC.setTextFill(Color.RED);
    	answerD.setTextFill(Color.RED);
    	
    	if (question.getCorrectAnswer() == 0) {
    		answerA.setTextFill(Color.GREEN);
    	} else if (question.getCorrectAnswer() == 1) {
    		answerB.setTextFill(Color.GREEN);
    	} else if (question.getCorrectAnswer() == 2) {
    		answerC.setTextFill(Color.GREEN);
    	} else {
    		answerD.setTextFill(Color.GREEN);
    	}
    }
    
    public void setQuestionsDisabled() {
    	answerA.setDisable(true);
    	answerB.setDisable(true);
    	answerC.setDisable(true);
    	answerD.setDisable(true);
    	
    }
    
    public void setQuestionsEnabled() {
    	answerA.setDisable(false);
    	answerA.setTextFill(Color.BLACK);
    	answerB.setDisable(false);
    	answerB.setTextFill(Color.BLACK);
    	answerC.setDisable(false);
    	answerC.setTextFill(Color.BLACK);
    	answerD.setDisable(false);
    	answerD.setTextFill(Color.BLACK);
    }

    public static void main(String[] args) {
        launch(args);
    }
}