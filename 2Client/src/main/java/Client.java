// Author @ Jay Patel (Jpate260)
// Author @ Ashir Saleemi (Asalee20)
// Multi-Threaded Server/Client Game
//  University Of Illinois at Chicago CS 342 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;


public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	Object val;
	DatabaseServer dataG;
	int chance = 6;
	int len = 0;
	String TEMP2 = "";
	int count2 = 0;
	String inf = "";
	String mes = "";
	public boolean open = true;
	private Consumer<Serializable> callback;
	String a = "";
	String tp = "";
	Client(Consumer<Serializable> call){
	
		callback = call;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket("127.0.0.1",5555);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);	    
		}
		catch(Exception e) {}
		
		while(true) {
			 
			try {
			serverCom();
			String message = in.readObject().toString();
			callback.accept(message);
			}
			catch(Exception e) {}
		}
    }


	public String getIN(){
		return inf;
	}
	
	
	// length of word
	public int enableW() {
		return count2;
	}
	
	// used to the guessed letters
	public String getUsedW() {
		return a;
	}
	
	// used to get number of guesses left
	public int getChance() {
		return chance;
	}
	// hidden word setter
	public String setHid() {
		return TEMP2;
	}
	
	// message getter
	public String getMessage() {
		return mes;
	}
	
	// used to send message to server
	public void send(String data) {
			try {
			if(data.length() == 0) {
				data = "Blank";
			}
			out.writeObject(data);
			out.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// used to read in data from server
	public void serverCom() {
		
		
		try {
			val = in.readObject();
			if(val instanceof DatabaseServer) {
				dataG = (DatabaseServer) val;
				System.out.println(dataG.getMessage());
				System.out.println(dataG.getC1());
				System.out.println(dataG.getC2());
				System.out.println(dataG.getC3());
				System.out.println(dataG.chance());
				System.out.println(dataG.getWordToGuess());
				System.out.println(dataG.getHiddenWord());
				System.out.println(dataG.getInfo());
				System.out.println(dataG.getCount());

			}
			
			mes = dataG.getMessage();
			TEMP2 = dataG.getHiddenWord();
			chance = dataG.chance();
			len = dataG.getHiddenWord().length();
			count2 = dataG.getCount();
			inf  = dataG.getInfo();	
			a = a+dataG.getUsed();
			//dataG.getUsed();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	}
