package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.output.GameOutput;
import net.davidtanzer.examples.hangman.player.strategy.LetterFrequencyBasedStrategy;
import net.davidtanzer.examples.hangman.player.strategy.PlayerStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanPostProcessorTest.TestConfiguration.class)
public class BeanPostProcessorTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void changeBeanClassWithPostProcessor() {
		Object bean = applicationContext.getBean("player");

		assertEquals(StupidPlayer.class, bean.getClass());
	}

	@Test(expected = ClassCastException.class)
	public void changeBeanToIncompatibleClass() {
		PlayerStrategy bean = (PlayerStrategy) applicationContext.getBean("playerStrategy");
	}

	public static class TestPostProcessor implements BeanPostProcessor {
		@Autowired
		private GameOutput gameOutput;

		@Override
		public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
			return new StupidPlayer(gameOutput);
		}

		@Override
		public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
			return bean;
		}
	}

	@ContextConfiguration
	public static class TestConfiguration {
		@Bean
		public TestPostProcessor tpp() {
			return new TestPostProcessor();
		}

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
