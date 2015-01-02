package net.davidtanzer.examples.hangman;

public interface Player {
	String guess(String hint, int livesLeft);
	void youHaveWon(String secretWord);
	void youHaveLost(String secretWord);
}
