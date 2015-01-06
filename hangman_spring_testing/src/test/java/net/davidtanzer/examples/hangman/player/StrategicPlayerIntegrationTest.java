package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.output.GameOutput;
import net.davidtanzer.examples.hangman.player.strategy.LetterFrequencyBasedStrategy;
import net.davidtanzer.examples.hangman.player.strategy.PlayerStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StrategicPlayerIntegrationTest.TestConfiguration.class)
public class StrategicPlayerIntegrationTest {
	@Autowired
	private Player player;

	@Test
	@DirtiesContext
	public void firstGuessIsX() {
		String guess = player.guess("____", 1);

		assertThat(guess, is("x"));
	}

	@Test
	@DirtiesContext
	public void firstGuessIsY() {
		player.guess("____", 1);
		String guess = player.guess("____", 1);

		assertThat(guess, is("y"));
	}

	@Configuration
	public static class TestConfiguration {
		@Bean
		public Player player() {
			return new StrategicPlayer(gameOutput(), playerStrategy());
		}

		@Bean
		public PlayerStrategy playerStrategy() {
			return new LetterFrequencyBasedStrategy("xyz");
		}

		@Bean
		public GameOutput gameOutput() {
			return new CommandLineOutput();
		}
	}
}