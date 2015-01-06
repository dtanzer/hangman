package net.davidtanzer.examples.hangman;

import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.output.GameOutput;
import net.davidtanzer.examples.hangman.player.Player;
import net.davidtanzer.examples.hangman.player.StrategicPlayer;
import net.davidtanzer.examples.hangman.player.strategy.LetterFrequencyBasedStrategy;
import net.davidtanzer.examples.hangman.player.strategy.PlayerStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@ComponentScan(basePackages = "net.davidtanzer.examples.hangman")
public class HangmanConfiguration {
	@Bean
	public Player player() {
		return new StrategicPlayer(gameOutput(), playerStrategy());
	}

	@Bean
	public PlayerStrategy playerStrategy() {
		return new LetterFrequencyBasedStrategy(LetterFrequencyBasedStrategy.FREQUENT_LETTERS_IN_ENGLISH);
	}

	@Bean
	public GameOutput gameOutput() {
		return new CommandLineOutput();
	}

	@Bean
	public Random random() {
		return new Random();
	}
}
