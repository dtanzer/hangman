package net.davidtanzer.examples.hangman;

public interface GameOutput {
	void update(String hint, int livesLeft, String nextGuess);
	void won(String secretWord);
	void lost(String secretWord);
}
