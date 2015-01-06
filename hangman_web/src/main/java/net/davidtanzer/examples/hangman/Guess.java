package net.davidtanzer.examples.hangman;

public class Guess {
	private final Hint hint;
	private final String guesses;
	private final int livesLeft;

	Guess(Hint hint, String guesses, int livesLeft) {
		this.hint = hint;
		this.guesses = guesses;
		this.livesLeft = livesLeft;
	}

	public String getGuesses() {
		return guesses;
	}

	public Hint getHint() {
		return hint;
	}

	public int getLivesLeft() {
		return livesLeft;
	}
}
