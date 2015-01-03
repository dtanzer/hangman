package net.davidtanzer.examples.hangman;

import net.davidtanzer.examples.hangman.dictionary.Dictionary;
import net.davidtanzer.examples.hangman.dictionary.OneWordDictionary;

import java.util.Random;

public class SecretWordProvider {
	private final Dictionary dictionary;
	private final Random random;

	public SecretWordProvider() {
		random = new Random();
		dictionary = new OneWordDictionary();
	}

	public String randomWord() {
		int wordIndex = random.nextInt(dictionary.size());
		return dictionary.wordAt(wordIndex);
	}
}
