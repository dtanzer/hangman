package net.davidtanzer.examples.hangman.output;

public interface GameOutput {
	void update(String hint, int livesLeft, String nextGuess);
	void won(String secretWord);
	void lost(String secretWord);
}
