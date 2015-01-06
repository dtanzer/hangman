package net.davidtanzer.examples.hangman;

public class IllegalGuessLengthException extends RuntimeException {
	public IllegalGuessLengthException(final String guess) {
		super("Illegal guess: \""+guess+"\" - length must be 1.");
	}
}
