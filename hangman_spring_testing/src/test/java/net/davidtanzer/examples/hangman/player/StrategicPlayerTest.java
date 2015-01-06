package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.output.GameOutput;
import net.davidtanzer.examples.hangman.player.strategy.PlayerStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StrategicPlayerTest {
	private StrategicPlayer player;
	private GameOutput gameOutput;
	private PlayerStrategy playerStrategy;

	@Before
	public void setup() {
		gameOutput = mock(GameOutput.class);
		playerStrategy = mock(PlayerStrategy.class);

		player = new StrategicPlayer(gameOutput, playerStrategy);
	}

	@Test
	public void asksPlayerStrategyForNextGuess() {
		String hint = "____";
		player.guess(hint, 1);

		verify(playerStrategy).guess(eq(hint));
	}
}