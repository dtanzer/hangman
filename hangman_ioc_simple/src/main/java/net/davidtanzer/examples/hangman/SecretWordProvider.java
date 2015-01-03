package net.davidtanzer.examples.hangman;

import net.davidtanzer.examples.hangman.dictionary.Dictionary;

import java.util.Random;

public class SecretWordProvider {
	private final Dictionary dictionary;
	private final Random random;

	public SecretWordProvider(final Dictionary dictionary, final Random random) {
		this.random = random;
		this.dictionary = dictionary;
	}

	public String randomWord() {
		int wordIndex = random.nextInt(dictionary.size());
		return dictionary.wordAt(wordIndex);
	}
}
