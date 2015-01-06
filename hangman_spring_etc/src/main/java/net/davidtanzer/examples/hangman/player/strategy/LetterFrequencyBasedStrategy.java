package net.davidtanzer.examples.hangman.player.strategy;

public class LetterFrequencyBasedStrategy implements PlayerStrategy {
	public static final String FREQUENT_LETTERS_IN_ENGLISH = "etaoinshrdlcumwfgypbvkjxqz";

	private final String letters;
	private int index = 0;

	public LetterFrequencyBasedStrategy(final String letters) {
		this.letters = letters;
	}

	@Override
	public String guess(final String hint) {
		return String.valueOf(letters.charAt(index++));
	}
}
