package net.davidtanzer.examples.hangman;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Game {
	private static final int MAX_TRIES = 8;
	private final String secretWord;
	private List<Guess> guesses;

	public Game() {
		secretWord = "broccoli";
		guesses = new ArrayList<Guess>();
		guesses.add(new Guess(createHint(""), "", MAX_TRIES));
	}

	public Hint getNextHint() {
		return createHint(guesses.get(guesses.size()-1).getGuesses());
	}

	public void addGuess(final String guess) {
		if(guess.length() != 1) {
			throw new IllegalGuessLengthException(guess);
		}
		Guess lastGuess = guesses.get(guesses.size() - 1);
		if(lastGuess.getLivesLeft() <= 0) {
			throw new GameAlreadyOverException();
		}
		String newGuesses = lastGuess.getGuesses() + guess;
		guesses.add(new Guess(createHint(newGuesses), newGuesses, lastGuess.getLivesLeft()-1));
	}

	public List<Guess> getGuesses() {
		return guesses;
	}

	private Hint createHint(final String guesses) {
		return new Hint(secretWord.replaceAll("[^ "+guesses+"]", "_"));
	}
}
