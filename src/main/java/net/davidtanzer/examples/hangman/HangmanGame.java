package net.davidtanzer.examples.hangman;

public class HangmanGame {
	private static final int MAX_TRIES = 8;
	private Player player;

	public static void main(String[] args) {
		new HangmanGame().start();
	}

	private void start() {
		Dictionary dictionary = new SimpleDictionary();
		player = new StupidPlayer();

		String secretWord = dictionary.randomWord();
		String guesses = "";
		for(int i=1; i <= MAX_TRIES; i++) {
			String hint = createHint(secretWord, guesses);
			int livesLeft = MAX_TRIES - i;
			guesses += player.guess(hint, livesLeft);
			if(checkPlayerHasWon(secretWord, guesses)) {
				player.youHaveWon(secretWord);
				return;
			}
		}

		player.youHaveLost(secretWord);
	}

	private boolean checkPlayerHasWon(final String secretWord, final String guesses) {
		if(secretWord.replaceAll("["+guesses+"]", "").isEmpty()) {
			return true;
		}
		return false;
	}

	private String createHint(final String secretWord, final String guesses) {
		return secretWord.replaceAll("[^ "+guesses+"]", "_");
	}
}
