package net.davidtanzer.examples.hangman;

import net.davidtanzer.examples.hangman.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Game {
	private static final int MAX_TRIES = 8;
	private final SecretWordProvider secretWordProvider;
	private Player player;

	@Autowired
	public Game(final SecretWordProvider secretWordProvider, final Player player) {
		this.player = player;
		this.secretWordProvider = secretWordProvider;
	}

	public void start() {
		String secretWord = secretWordProvider.randomWord();
		String guesses = "";

		for(int i=1; i <= MAX_TRIES; i++) {
			guesses = getNextGuessFromPlayer(secretWord, guesses, i);

			if(checkPlayerHasWon(secretWord, guesses)) {
				player.youHaveWon(secretWord);
				return;
			}
		}

		player.youHaveLost(secretWord);
	}

	private String getNextGuessFromPlayer(final String secretWord, String guesses, final int i) {
		String hint = createHint(secretWord, guesses);
		int livesLeft = MAX_TRIES - i;
		guesses += player.guess(hint, livesLeft);
		return guesses;
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
