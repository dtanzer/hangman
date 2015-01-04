package net.davidtanzer.examples.hangman;

import net.davidtanzer.examples.hangman.dictionary.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SecretWordProvider {
	private final Dictionary dictionary;
	private final Random random;

	@Autowired
	public SecretWordProvider(final Dictionary dictionary, final Random random) {
		this.random = random;
		this.dictionary = dictionary;
	}

	public String randomWord() {
		int wordIndex = random.nextInt(dictionary.size());
		return dictionary.wordAt(wordIndex);
	}
}
