// Author @ Jay Patel (Jpate260)
// Author @ Ashir Saleemi (Asalee20)
// Multi-Threaded Server/Client Game
//  University Of Illinois at Chicago CS 342 


import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

	BorderPane bp;
	Pane p1, p2, p3;
	Button Category1, Category2, Category3, Send, Reset, HTP, PlayAgain;
	Rectangle r1;
	TextArea CLWordsDisplay, CRGuess, CRLetter, CRGuessesLeft, BRGuessesNumber;
	ListView<String> listItems2;
	TextField c1;
	// 17 Objects == 17 Objects
	HashMap<String, Scene> sceneMap;  // Keeping Track of the Sceens
	Scene startScene;
	Client clientConnection;  // Connecting to Server
	
	int GuessedLetters = 0;  // keeping Track of the number guessed
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		//Connection made Sending information
		primaryStage.setTitle("Client");
		clientConnection = new Client(data->{
		Platform.runLater(()->{listItems2.getItems().add(data.toString());});});
		clientConnection.start();
		listItems2 = new ListView<String>();
        //----------------Creating the Interface----------------//
		Category1 = new Button(); // First Button on Top
		Category1.setLayoutX(14);
		Category1.setLayoutY(15);
		Category1.setMnemonicParsing(false);
		Category1.prefHeight(31);
		Category1.prefWidth(153);
		Category1.setText("Category 1");
		Category1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)");
		Category1.setTextFill(Color.BLACK);
		Category2 = new Button(); // Second Button on Top 
		Category2.setLayoutX(229);
		Category2.setLayoutY(15);
		Category2.setMnemonicParsing(false);
		Category2.prefHeight(31);
		Category2.prefWidth(153);
		Category2.setText("Category 2");
		Category2.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)");
		Category2.setTextFill(Color.BLACK);
		Category3 = new Button(); // Third Button on the Top
		Category3.setLayoutX(443);
		Category3.setLayoutY(15);
		Category3.setMnemonicParsing(false);
		Category3.prefHeight(31);
		Category3.prefWidth(153);
		Category3.setText("Category 3");
		Category3.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)");
		Category3.setTextFill(Color.BLACK);
		p1 = new Pane(); // Top Pane Containor
		p1.prefHeight(69);
		p1.prefWidth(600);
		p1.setStyle("-fx-background-color: #bb163b;");
		p1.setOpacity(0.85);
		p1.getChildren().addAll(Category1,Category2,Category3);
		p1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #771ce3, #bb163b)");
		//---------- In Pane 2 ------------//
		r1 = new Rectangle(); // Rectangle OverLay 
		r1.setArcHeight(5.0);
		r1.setArcWidth(5.0);
		r1.setStyle("-fx-background-color: #d75555;");
		r1.setHeight(228);
		r1.setWidth(583);
		r1.setLayoutX(9);
		r1.setLayoutY(6);
		
		CLWordsDisplay = new TextArea(); // Main Word Display
		CLWordsDisplay.setLayoutX(24);
		CLWordsDisplay.setLayoutY(20);
		CLWordsDisplay.setPrefHeight(200);
		CLWordsDisplay.setPrefWidth(358);
		CLWordsDisplay.setStyle("-fx-padding: 80 10 50 10; -fx-text-alignment: center ;");
		CLWordsDisplay.setFont(Font.font("Hevetica", 24));		
		CLWordsDisplay.setText("__##__#_");
		CLWordsDisplay.setEditable(false);
		
		TextArea WinOutput = new TextArea(); // Win Text Area
		WinOutput.setLayoutX(24);
		WinOutput.setLayoutY(237);
		WinOutput.setPrefHeight(15);
		WinOutput.setPrefWidth(358);
		WinOutput.setText("Status update");
		WinOutput.setEditable(false);
		
		Text t1 = new Text(); // In the rectangle Text Indicator
		t1.setLayoutX(38);
		t1.setLayoutY(190);
		t1.setFont(Font.font("Hevetica", 16));
		t1.setText("Enter you Guess here:");
		t1.setWrappingWidth(93.6708984375);
		
		CRGuess = new TextArea(); // Guessing Text Box
		CRGuess.setLayoutX(155);
		CRGuess.setLayoutY(179);
		CRGuess.setPrefHeight(30);
		CRGuess.setPrefWidth(150);
		CRGuess.setText("Please Choose a Category");
		CRGuess.setWrapText(true);
		CRGuess.setOpacity(0.80);
		CRGuess.setEditable(false);
		
		CRLetter = new TextArea(); // Letters in Word Indicator
		CRLetter.setLayoutX(397);
		CRLetter.setLayoutY(20);
		CRLetter.setPrefHeight(55);
		CRLetter.setPrefWidth(181);
		CRLetter.setText("Letters in the Word: #");
		CRLetter.setWrapText(true);
		CRLetter.setOpacity(0.80);
		
		CRGuessesLeft = new TextArea(); // Guesses Left Indicator
		CRGuessesLeft.setLayoutX(397);
		CRGuessesLeft.setLayoutY(93);
		CRGuessesLeft.setPrefHeight(55);
		CRGuessesLeft.setPrefWidth(181);
		CRGuessesLeft.setText("Guesses Left: #");
		CRGuessesLeft.setOpacity(0.80);
		
		HTP = new Button(); // How to Play button 
		HTP.setLayoutX(411);
		HTP.setLayoutY(169);
		HTP.setMnemonicParsing(false);
		HTP.setPrefHeight(38);
		HTP.setPrefWidth(153);
		HTP.setText("How to Play");
		HTP.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #bb163b, #771ce3)");
		HTP.setTextFill(Color.WHITE);
		
		PlayAgain = new Button(); // Play Again Button
		PlayAgain.setLayoutX(34);
		PlayAgain.setLayoutY(44);
		PlayAgain.setMnemonicParsing(false);
		PlayAgain.setPrefHeight(31);
		PlayAgain.setPrefWidth(337);
		PlayAgain.setText("Play Again/Reset");
		PlayAgain.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #771ce3, #bb163b)");
		PlayAgain.setTextFill(Color.WHITE);
		PlayAgain.setOnAction(e->{	// Play Again event Handler
			clientConnection.send("R"); // Send "R" to the Server
			Category1.setDisable(false); // Disabling Other Buttons from Catagories
        	Category2.setDisable(false);
        	Category3.setDisable(false);
        	CRGuess.setText("Please Choose a Category");  // Reseting the text in Every Field
        	BRGuessesNumber.setText("Letters Guessed: #");
			CRGuessesLeft.setText("Guesses Left: #");
			CRLetter.setText("Letters in the Word = #");
			CLWordsDisplay.setText("__##__#_");
			Category3.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)"); // Reseting the Colors
			Category2.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)");
			Category1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)");
			BRGuessesNumber.setText("Letters Guessed: #");
			WinOutput.setText("Status Update");
			
        });
		
		p2 = new Pane(); // Pane 2 as a Container
		p2.prefHeight(135);
		p2.prefWidth(600);
		p2.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #06e9e4, #0e0fc8)");
		p2.getChildren().addAll(r1,CLWordsDisplay,t1,CRGuess,CRLetter,CRGuessesLeft,HTP,PlayAgain,WinOutput); // Adding Childern to Container
		//------------- Start of Pane 3 ----------------//
		BRGuessesNumber = new TextArea(); // Letters you have Guesed Already
		BRGuessesNumber.setLayoutX(408);
		BRGuessesNumber.setLayoutY(30);
		BRGuessesNumber.setPrefHeight(33);
		BRGuessesNumber.setPrefWidth(178);
		BRGuessesNumber.setText("Letters Guessed: #");
		BRGuessesNumber.setEditable(false);
		
		listItems2.setLayoutX(14);
		listItems2.setLayoutY(3);
		listItems2.setPrefHeight(73);
		listItems2.setPrefWidth(189);
		
		Send = new Button(); // Sending Request Button 
		Send.setLayoutX(218);
		Send.setLayoutY(10);
		Send.setMnemonicParsing(false);
		Send.setPrefHeight(67);
		Send.setPrefWidth(72);
		Send.setText("Send");
		Send.setDisable(true);
		Send.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #03b9ba, #00d4ff)");
		Send.setTextFill(Color.BLACK);
		
		Reset = new Button(); // Checking the String Button
		Reset.setLayoutX(306);
		Reset.setLayoutY(10);
		Reset.setMnemonicParsing(false);
		Reset.setPrefHeight(67);
		Reset.setPrefWidth(72);
		Reset.setText("Check");
		Reset.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00d4ff, #03b9ba)");
		Reset.setTextFill(Color.BLACK);
		
		p3 = new Pane(); // Pane 3 Container
		p3.prefHeight(92);
		p3.prefWidth(600);
		p3.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #bb163b, #771ce3)");
		p3.getChildren().addAll(listItems2,Send,Reset,BRGuessesNumber);
		//---------- Border Pane to Organize the 3 other Panes ------------//
		bp = new BorderPane();
		BorderPane.setAlignment(p1, Pos.CENTER);
		BorderPane.setAlignment(p2, Pos.CENTER);
		BorderPane.setAlignment(p3, Pos.CENTER);
		bp.prefHeight(400);
		bp.prefWidth(600);
		bp.setTop(p1);
		bp.setCenter(p2);
		bp.setBottom(p3);
		
		
		Popup popup = new Popup(); // Popup Text
        TextArea t = new TextArea("This is Client/Server based Hangman game.\n"
                + "Begin the game by choosing from one of the Three Categories above.\n"
                + "Goal: Guess one word from each Category.\n"
                + "TextFields Indicate how many letters are in the Word/Letters guessed/Guesses Left.\n"
                + "Enter you guess in the Editable TextBox.\n"
                + "Guess Three words and Win the game. Enjoy:)"+"\n\nPress [ESC] to exit."
        		+ "**********IMPORTANT NOTE**********\n"
                + "After pressing send button press the check button to see if the letter entered is valid or not");
        t.setEditable(false);
        t.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        t.setFont(new Font("Helvetica", 15)); 
        popup.setX(300);
        popup.setY(200);
        popup.getContent().addAll(t);
        popup.setHideOnEscape(true);
        
		
		
        HTP.setOnAction(e->{  // Showing the PopUp
        	popup.show(primaryStage);
        });
        
        Category1.setOnAction(e->{  // On Action Sending Request and Updating Text Fields
			Category1.setStyle("-fx-background-color: #3d06cb");
        	clientConnection.send("A");
			clientConnection.tp = "A";
        	Send.setDisable(false);
        	CRGuess.clear();
        	CRGuess.setEditable(true);
        	BRGuessesNumber.setText("Letters Guessed: #" + clientConnection.getUsedW());
			CRGuessesLeft.setText("Guesses Left: #" + clientConnection.getChance());
			CRLetter.setText("Letters in the Word = #" + clientConnection.len);
			CLWordsDisplay.setText(clientConnection.setHid());
			WinOutput.setText(clientConnection.getMessage());
        	
        	
        });
        Category2.setOnAction(e->{ // On Action Sending Request and Updating Text Fields
			Category2.setStyle("-fx-background-color: #3d06cb");
        	clientConnection.send("B");
			clientConnection.tp = "B";
        	Send.setDisable(false);
        	CRGuess.clear();
        	CRGuess.setEditable(true);
        	BRGuessesNumber.setText("Letters Guessed: #" + clientConnection.getUsedW());
			CRGuessesLeft.setText("Guesses Left: #" + clientConnection.getChance());
			CRLetter.setText("Letters in the Word = #" + clientConnection.len);
			CLWordsDisplay.setText(clientConnection.setHid());
			WinOutput.setText(clientConnection.getMessage());
        });
        Category3.setOnAction(e->{ // On Action Sending Request and Updating Text Fields
			Category3.setStyle("-fx-background-color: #3d06cb");
        	clientConnection.send("C");
			clientConnection.tp = "C";
        	Send.setDisable(false);
        	CRGuess.clear();
        	CRGuess.setEditable(true);
        	BRGuessesNumber.setText("Letters Guessed: #" + clientConnection.getUsedW());
			CRGuessesLeft.setText("Guesses Left: #" + clientConnection.getChance());
			CRLetter.setText("Letters in the Word = #" + clientConnection.len);
			CLWordsDisplay.setText(clientConnection.setHid());
			WinOutput.setText(clientConnection.getMessage());
        });
        
        if(clientConnection.inf == "ZA"){ // Depending on Catogory Selected Disable the other 2 
			Category2.setDisable(false);
			Category3.setDisable(false);
			Category1.setStyle("-fx-background-color: #1de22d");
		}
		if( clientConnection.inf == "ZB"){ // Depending on Catogory Selected Disable the other 2
			Category1.setDisable(false);
			Category3.setDisable(false);
			Category2.setStyle("-fx-background-color: #1de22d");
		}
		if(clientConnection.inf == "ZC"){ // Depending on Catogory Selected Disable the other 2
			Category1.setDisable(false);
			Category2.setDisable(false);
			Category3.setStyle("-fx-background-color: #1de22d");
		}
		
		
		Send.setOnAction(e->{ // Send the Guess from the Text Area to the Server
			clientConnection.send(CRGuess.getText()); 
			CRGuess.clear();
		});
		Reset.setOnAction(e->{ // Check button Checking that the Word Exists or not and Recieving Input
			BRGuessesNumber.setText("Letters Guessed: #" + clientConnection.getUsedW());
			CRGuessesLeft.setText("Guesses Left: #" + clientConnection.getChance());
			CRLetter.setText("Letters in the Word = #" + clientConnection.len);
			CLWordsDisplay.setText(clientConnection.setHid());
			WinOutput.setText(clientConnection.getMessage());
		});
		
		sceneMap = new HashMap<String, Scene>(); // Intilizing the Hash Map to save Sceens
		sceneMap.put("client",  createClientGui()); // Inserting the Sceen in the Map
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		primaryStage.setScene(sceneMap.get("client")); // Setting the Sceen 
		primaryStage.show(); // Showing The Sceen
	}
	
	
	public Scene createClientGui() { // Creating the Sceen with the Border Pane
		return new Scene(bp,600,400);
		
	}

}