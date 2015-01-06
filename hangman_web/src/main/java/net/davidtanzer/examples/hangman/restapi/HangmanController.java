package net.davidtanzer.examples.hangman.restapi;

import net.davidtanzer.examples.hangman.Game;
import net.davidtanzer.examples.hangman.Guess;
import net.davidtanzer.examples.hangman.Hint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HangmanController {
	private final Game game;

	@Autowired
	public HangmanController(final Game game) {
		this.game = game;
	}

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hangman!";
	}

	@RequestMapping(value = "/guesses", method = RequestMethod.GET)
	public List<Guess> guesses(Model model) {
		return game.getGuesses();
	}

	@RequestMapping(value = "/guesses/{guessId}", method = RequestMethod.GET)
	public Guess guess(@PathVariable("guessId") int guessId) {
		return game.getGuesses().get(guessId);
	}

	@RequestMapping(value = "/guesses", method = RequestMethod.POST)
	public void postGuess(@RequestParam("guess") String guess, Model model) {
		game.addGuess(guess);
	}

	@RequestMapping("/hint")
	public Hint hint() {
		return game.getNextHint();
	}

	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleGuessNotFound() {
		return "Item not found...";
	}
}
