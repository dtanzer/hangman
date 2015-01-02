package net.davidtanzer.examples.hangman;

public class CommandLineOutput implements GameOutput {
	@Override
	public void update(final String hint, final int livesLeft, final String nextGuess) {
		System.out.println(hint+" (Lives left: "+livesLeft+") Guessing: "+nextGuess);
	}

	@Override
	public void won(final String secretWord) {
		System.out.println("YOU WON! The secret word was: "+secretWord);
	}

	@Override
	public void lost(final String secretWord) {
		System.out.println("You lost :( The secret word was: "+secretWord);
	}
}
