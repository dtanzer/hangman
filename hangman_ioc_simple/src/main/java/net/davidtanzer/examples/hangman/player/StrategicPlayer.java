package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.output.GameOutput;
import net.davidtanzer.examples.hangman.player.strategy.PlayerStrategy;

public class StrategicPlayer implements Player {
	private final GameOutput gameOutput;
	private final PlayerStrategy playerStrategy;

	public StrategicPlayer(final GameOutput gameOutput, final PlayerStrategy playerStrategy) {
		this.gameOutput = gameOutput;
		this.playerStrategy = playerStrategy;
	}

	@Override
	public String guess(final String hint, final int livesLeft) {
		String nextGuess = playerStrategy.guess(hint);
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
