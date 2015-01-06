package net.davidtanzer.examples.hangman.player;

public interface Player {
	String guess(String hint, int livesLeft);
	void youHaveWon(String secretWord);
	void youHaveLost(String secretWord);
}
