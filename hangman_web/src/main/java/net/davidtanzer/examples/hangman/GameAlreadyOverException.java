package net.davidtanzer.examples.hangman;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Cannot add another guess, the game is already over!")
public class GameAlreadyOverException extends RuntimeException {
}
