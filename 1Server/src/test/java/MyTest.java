import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void basic() {
		DatabaseServer data = new DatabaseServer();
		data.setWordToGuess("TEST");
		assertEquals("TEST",data.getWordToGuess(), "Wrong");
		
		
	}
	
	@Test
	void basic2() {
		DatabaseServer data = new DatabaseServer();
		data.setHiddenWord("---");
		assertEquals("---",data.getHiddenWord(), "Wrong");
	}
	
	@Test
	void basic3() {
		DatabaseServer data = new DatabaseServer();
		data.setGuess(6);
		assertEquals(6,data.chance(), "Wrong");
	}
	
	@Test
	void wordCat1() {
		DatabaseServer data = new DatabaseServer();
		data.setWordToGuess("volleyball");
		data.setC1(0);
		assertEquals(data.getWordToGuess(),data.cat1[data.getC1()], "Wrong");
	}
	
	@Test
	void wordCat2() {
		DatabaseServer data = new DatabaseServer();
		data.setWordToGuess("summer");
		data.setC2(0);
		assertEquals(data.getWordToGuess(),data.cat2[data.getC2()], "Wrong");
	}
	
	@Test
	void wordCat3() {
		DatabaseServer data = new DatabaseServer();
		data.setWordToGuess("lamborghini");
		data.setC3(0);
		assertEquals(data.getWordToGuess(),data.cat3[data.getC3()], "Wrong");
	}
	
	@Test
	void used() {
		DatabaseServer data = new DatabaseServer();
		data.setUsed("abcd");
		assertEquals("abcd",data.getUsed(), "Wrong");
	}
	
	@Test
	void count() {
		DatabaseServer data = new DatabaseServer();
		data.setCount(1);
		assertEquals(1,data.getCount(), "Wrong");
	}
	
	@Test
	void info() {
		DatabaseServer data = new DatabaseServer();
		data.setInfo("A");
		assertEquals("A",data.getInfo(), "Wrong");
	}
	
	@Test
	void track() {
		DatabaseServer data = new DatabaseServer();
		data.setTrack(2);
		assertEquals(2,data.getTrack(), "Wrong");
		data.incTrack();
		assertEquals(3,data.getTrack(), "Wrong");
		data.incTrack();
		data.incTrack();
		assertEquals(5,data.getTrack(), "Wrong");
		
	}
	
	@Test
	void message() {
		DatabaseServer data = new DatabaseServer();
		data.setMessage("Hello");
		assertEquals("Hello",data.getMessage(), "Wrong");
	}
	
	@Test
	void indicator() {
		DatabaseServer data = new DatabaseServer();
		data.setInd(3);
		assertEquals(3,data.getInd(), "Wrong");
	}

}
