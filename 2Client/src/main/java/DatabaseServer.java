// Author @ Jay Patel (Jpate260)
// Author @ Ashir Saleemi (Asalee20)
// Multi-Threaded Server/Client Game
//  University Of Illinois at Chicago CS 342 

import java.io.Serializable;
import java.util.ArrayList;

public class DatabaseServer implements java.io.Serializable{
	private static final long serialVersionUID = -4953957455523193258L; 
	private int guessNum;  // used to keep track of attempts
	private int c1; // used to track which word in category one is used
	private int c2;  // used to track which word in category two is used
	private int c3;  // used to track which word in category three is used
	private int count; // used to keep track of how many letters left to guess
	private int indicator;
	private String wordToGuess; // word which is  being guessed
	private String hiddenWord; // word convered to "-"
	private String Used; // keep track of letters used already
	private String message; // outputs messages
	private String info; // keep track of which button is being used
	private int track; // keep track of how many categorys left for win
	String[] cat1 = {"volleyball", "cricket", "soccer", "swimming", "basketball"};  // sports
	String[] cat2 = {"summer", "winter", "spring", "fall"};  // seasons
	String[] cat3 = {"lamborghini", "ferrari", "tesla","audi"}; // cars
	
	public void incTrack() {
		this.track = track+1;
	}
	public void setTrack(int a) {
		this.track = a;
	}
	
	public int getTrack() {
		return track;
	}
	
	public void setCount(int a) {
		this.count = a;
	}
	
	public int getCount() {
		return count;
	}
	public void dCount(){
		this.count = count-1;
	}
	public void setUsed(String a) {
		this.Used = a;
	}
	public String getUsed() {
		return Used;
	}
	public void setWordToGuess(String a) {
		this.wordToGuess = a;
	}
	
	public String getWordToGuess() {
		return wordToGuess;
	}
	
	public void setHiddenWord(String a) {
		this.hiddenWord = a;
	}
	public String getHiddenWord() {
		return hiddenWord;
	}
	
	public int getC1() {
		return c1;
	}
	public int getC2() {
		return c2;
	}

	public int getC3() {
		return c3;
	}
	
	public void setC1(int a) {
		this.c1 = a;
	}
	public void setC2(int b) {
		this.c2 = b;
	}

	public void setC3(int c) {
		this.c3 = c;
	}
	public void incC1() {
		this.c1 = c1+1;
	}
	public void incC2() {
		this.c2 = c2+1;
	}
	public void incC3() {
		this.c3 = c3+1;
	}

    public int chance() {
        return guessNum;
    }

    public void setGuess(int guessNum) {
        this.guessNum = guessNum;
    }
    public void removeGuess() {
        this.guessNum = guessNum-1;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setInd(int a) {
    	this.indicator = a;
    }
    
    public int getInd() {
    	return indicator;
    }
}
