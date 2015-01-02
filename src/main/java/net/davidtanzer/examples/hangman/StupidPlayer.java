package net.davidtanzer.examples.hangman;

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
