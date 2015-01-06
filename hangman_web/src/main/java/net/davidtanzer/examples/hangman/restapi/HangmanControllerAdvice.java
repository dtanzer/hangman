package net.davidtanzer.examples.hangman.restapi;

import net.davidtanzer.examples.hangman.IllegalGuessLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HangmanControllerAdvice {
	@ExceptionHandler(IllegalGuessLengthException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void illegalGuessLength() {
	}
}
