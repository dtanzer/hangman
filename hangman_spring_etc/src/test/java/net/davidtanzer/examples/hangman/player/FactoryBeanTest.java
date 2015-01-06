package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.output.GameOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FactoryBeanTest.TestConfiguration.class)
public class FactoryBeanTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testGettingPlayer() {
		Object player = applicationContext.getBean("player");

		assertTrue(player instanceof Player);
	}

	@Test
	public void testGettingPlayerFactory() {
		Object player = applicationContext.getBean("&player");

		assertTrue(player instanceof PlayerFactory);
	}

	public static class PlayerFactory implements FactoryBean<Player> {
		@Autowired
		private GameOutput gameOutput;

		@Override
		public Player getObject() throws Exception {
			return new StupidPlayer(gameOutput);
		}

		@Override
		public Class<?> getObjectType() {
			return StupidPlayer.class;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}
	}

	@ContextConfiguration
	public static class TestConfiguration {
		@Bean
		public PlayerFactory player() {
			return new PlayerFactory();
		}

		@Bean
		public GameOutput gameOutput() {
			return new CommandLineOutput();
		}
	}
}
