package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.output.GameOutput;

public class StupidPlayer implements Player {
	private final GameOutput gameOutput;
	private char lastGuess = 'a';

	public StupidPlayer() {
		gameOutput = new CommandLineOutput();
	}

	@Override
	public String guess(final String hint, final int livesLeft) {
		String nextGuess = String.valueOf(lastGuess++);
		gameOutput.update(hint, livesLeft, nextGuess);
		return nextGuess;
	}

	@Override
	public void youHaveWon(final String secretWord) {
		gameOutput.won(secretWord);
	}

	@Override
	public void youHaveLost(final String secretWord) {
		gameOutput.lost(secretWord);
	}
}
