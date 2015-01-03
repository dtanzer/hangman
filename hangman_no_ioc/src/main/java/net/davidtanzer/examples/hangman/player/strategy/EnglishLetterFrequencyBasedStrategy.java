package net.davidtanzer.examples.hangman.player.strategy;

public class EnglishLetterFrequencyBasedStrategy implements PlayerStrategy {
	String letters = "etaoinshrdlcumwfgypbvkjxqz";
	int index = 0;

	@Override
	public String guess(final String hint) {
		return String.valueOf(letters.charAt(index++));
	}
}
