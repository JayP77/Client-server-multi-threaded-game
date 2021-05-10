// Author @ Jay Patel (Jpate260)
// Author @ Ashir Saleemi (Asalee20)
// Multi-Threaded Server/Client Game
//  University Of Illinois at Chicago CS 342 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

	DatabaseServer dataSend = new DatabaseServer();
	
	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	
	Server(Consumer<Serializable> call){
	
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
			
		
			try(ServerSocket mysocket = new ServerSocket(5555);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			
				ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
				
			}
			
			
			// initializes game
			public void initializeGame() {
				dataSend.setGuess(6);
				dataSend.setMessage("");
				dataSend.setInfo("");
				dataSend.setC1(0);
				dataSend.setC2(0);
				dataSend.setC3(0);
				dataSend.setCount(0);
				dataSend.setHiddenWord("");
				dataSend.setWordToGuess("");
				dataSend.setUsed("");
				dataSend.setTrack(0);
				
				
			}
			
			//check for win
			public char checkW(char a) {
				char temp = 't';
				for(int i = 0; i < dataSend.getWordToGuess().length(); i++) {
				  if(a == dataSend.getHiddenWord().charAt(i));{
					temp = 'f';
				  }
				}
				return temp;
			}
			
			// search for specific 
			public char searchC(char a) {
				for(int i = 0; i < dataSend.getWordToGuess().length(); i++) {
					if(a == dataSend.getWordToGuess().charAt(i)) {
						return 't';
					} 
				}
				return 'f';
			}
		
			//search if letter is in use
			public char searchUse(char a) {
				for(int i = 0; i < dataSend.getUsed().length(); i++) {
					if(a == dataSend.getUsed().charAt(i)) {
						return 't';
					} 
				}
				return 'f';
			}
			
			// converts letter into hidden word for user to guess
			public void setV() {
				int a = dataSend.getWordToGuess().length();
				String temp = "";
				for(int i=0; i < a; i++) {
					temp = temp+"-";
				}
				dataSend.setCount(dataSend.getWordToGuess().length());
				dataSend.setHiddenWord(temp);
			}
			
			// main algorithm for game
			public void mainAlgo(String Data) {
				
				if(dataSend.getInfo() == "A") {
					dataSend.setWordToGuess(dataSend.cat1[dataSend.getC1()]);
					setV();
					dataSend.setInfo("ZA");
					
				} else if(dataSend.getInfo() == "B") {
					dataSend.setWordToGuess(dataSend.cat2[dataSend.getC2()]);
					setV();
					dataSend.setInfo("ZB");
					
					
				} else if(dataSend.getInfo() == "C") {
					dataSend.setWordToGuess(dataSend.cat3[dataSend.getC3()]);
					setV();
					dataSend.setInfo("ZC");
				}

				dataSend.setMessage("");
				
				String temp2 = dataSend.getHiddenWord();
				if(dataSend.chance() == 0) {
					dataSend.setMessage("You are out of guesses! try another category or word");
					if(dataSend.getInfo() == "A") {
						dataSend.incC1();
					}
					if(dataSend.getInfo() == "B") {
						dataSend.incC2();
					}
					if(dataSend.getInfo() == "C") {
						dataSend.incC3();
					}
					
				} else if(searchUse(Data.charAt(0)) == 't') {
					dataSend.setMessage("Letter already guessed try another letter!");
					dataSend.removeGuess();
					
				} else if(Data.length() != 1) {
					dataSend.setMessage("Enter only one letter at a time!");
					dataSend.removeGuess();
				} else if(searchC(Data.charAt(0)) == 'f'){
					dataSend.setMessage("Letter not in word!");
					dataSend.removeGuess();
					dataSend.setUsed(Data+" ");
		
				} else if(searchC(Data.charAt(0)) == 't'){
					
					for(int i = 0; i < dataSend.getWordToGuess().length(); i++) {
						if(Data.charAt(0) == dataSend.getWordToGuess().charAt(i)) {
							temp2 = temp2.substring(0, i) + Data.charAt(0) + temp2.substring(i + 1);
							dataSend.setMessage("Letter in word");
							dataSend.setHiddenWord(temp2);
							dataSend.dCount();
							dataSend.setUsed(Data+" ");
						}
					}
				}  
				if (dataSend.getCount() == 0) {
					dataSend.setMessage("You correctly guessed the word!");
					dataSend.incTrack();
					if(dataSend.getInfo() == "ZA") {
						dataSend.incC1();
						dataSend.setInfo("A");
						dataSend.setGuess(6);
					}
					if(dataSend.getInfo() == "ZB") {
						dataSend.incC2();
						dataSend.setInfo("B");
						dataSend.setGuess(6);
					}
					if(dataSend.getInfo() == "ZC") {
						dataSend.incC3();
						dataSend.setInfo("C");
						dataSend.setGuess(6);
					}
				}
				if(dataSend.getTrack() == 3) {
					dataSend.setMessage("You have won the game");
				}
			}
			
			// to keep track of which category is in use
			public String sendChar(String a) {
				for(int i = 0; i < a.length(); i++) {
					if(a.charAt(i) == 'A') {
						return "A1";
					}
					if(a.charAt(i) == 'B') {
						return "B1";
					}
					if(a.charAt(i) == 'C') {
						return "C1";
					}
					if(a.charAt(i) == 'R') {
						return "R1";
					}
				}
				return "z";
			}
	
			
			public void run(){
				
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				updateClients("new client on server: client #"+count);
				
				initializeGame();
				 while(true) {				 	
					    try {
					    	String data = in.readObject().toString();					    	
					    	callback.accept("client: " + count + " sent: " + data);
					    	updateClients("client #"+count+" said: "+ data);
					    	if(sendChar(data) == "A1") { // cat 1
					    		dataSend.setInfo("A");
					    	}
					    	if(sendChar(data) == "B1") { // cat 2
					    		dataSend.setInfo("B");
					    	}
					    	if(sendChar(data) == "C1") { // cat 3
					    		dataSend.setInfo("C");
					    	}
					    	if(sendChar(data) == "R1") { // resets
					    		dataSend.setGuess(6);
								dataSend.setMessage("");
								dataSend.setInfo("");
								dataSend.setC1(0);
								dataSend.setC2(0);
								dataSend.setC3(0);
								dataSend.setCount(1);
								dataSend.setHiddenWord("");
								dataSend.setWordToGuess("");
								dataSend.setUsed("");
								dataSend.setTrack(0);
					    	}
					    	if(sendChar(data) != "R1" && sendChar(data) != "A1" && sendChar(data) != "B1" && sendChar(data) != "C1") {
					    		mainAlgo(data);
					    	}
					    	out.writeObject(dataSend);
					    	out.reset(); 
					    	
					    }
					    
					    
					    	
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					   
					    
					}
				 
				}//end of run
			
			
			
		}//end of client thread
		
}


	
	

	
