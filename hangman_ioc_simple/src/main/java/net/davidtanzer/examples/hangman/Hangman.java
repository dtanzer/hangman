package net.davidtanzer.examples.hangman;

import net.davidtanzer.examples.hangman.dictionary.OneWordDictionary;
import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.player.StrategicPlayer;
import net.davidtanzer.examples.hangman.player.strategy.LetterFrequencyBasedStrategy;

import java.util.Random;

public class Hangman {
	public static void main(String[] args) {
		Game game = setupNewGame();
		game.start();
	}

	private static Game setupNewGame() {
		Random random = new Random();
		OneWordDictionary dictionary = new OneWordDictionary();
		SecretWordProvider secretWordProvider = new SecretWordProvider(dictionary, random);

		CommandLineOutput gameOutput = new CommandLineOutput();
		LetterFrequencyBasedStrategy playerStrategy = new LetterFrequencyBasedStrategy(LetterFrequencyBasedStrategy.FREQUENT_LETTERS_IN_ENGLISH);
		StrategicPlayer player = new StrategicPlayer(gameOutput, playerStrategy);

		return new Game(secretWordProvider, player);
	}
}
