package net.davidtanzer.examples.hangman.player;

import net.davidtanzer.examples.hangman.dictionary.Dictionary;
import net.davidtanzer.examples.hangman.dictionary.OneWordDictionary;
import net.davidtanzer.examples.hangman.output.CommandLineOutput;
import net.davidtanzer.examples.hangman.output.GameOutput;
import net.davidtanzer.examples.hangman.player.strategy.LetterFrequencyBasedStrategy;
import net.davidtanzer.examples.hangman.player.strategy.PlayerStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScopesTest.TestConfiguration.class)
public class ScopesTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void contextReturnsTheSameInstanceOfSingletonScopedBeanEveryTime() {
		assertSame(applicationContext.getBean(GameOutput.class), applicationContext.getBean(GameOutput.class));
	}

	@Test
	public void contextReturnsDifferentInstanceOfPrototypeScopedBeanEveryTime() {
		assertNotSame(applicationContext.getBean(PlayerStrategy.class), applicationContext.getBean(PlayerStrategy.class));
	}


	@Test
	public void beanWithNoScopeAnnotationIsInSingletonScope() {
		assertSame(applicationContext.getBean(Dictionary.class), applicationContext.getBean(Dictionary.class));
	}

	@Test
	public void singletonWithLookupMethodGetsNewPlayerStrategyEachTime() {
		assumeTrue(applicationContext.getBean(SingletonThatDependsOnPrototypeBean.class) == applicationContext.getBean(SingletonThatDependsOnPrototypeBean.class));

		SingletonThatDependsOnPrototypeBean sb = applicationContext.getBean(SingletonThatDependsOnPrototypeBean.class);
		assertNotSame(sb.getStrategy(), sb.getStrategy());
	}

	public static abstract class SingletonThatDependsOnPrototypeBean {
		public PlayerStrategy getStrategy() {
			return createStrategy();
		}

		protected abstract PlayerStrategy createStrategy();
	}

	public static class TestConfiguration {
		@Bean
		@Scope("prototype")
		public PlayerStrategy playerStrategy() {
			return new LetterFrequencyBasedStrategy("xyz");
		}

		@Bean
		@Scope("singleton")
		public GameOutput gameOutput() {
			return new CommandLineOutput();
		}

		@Bean
		public Dictionary dictionary() { return new OneWordDictionary(); }

		@Bean
		public SingletonThatDependsOnPrototypeBean sb() {
			return new SingletonThatDependsOnPrototypeBean() {
				@Override
				protected PlayerStrategy createStrategy() {
					return playerStrategy();
				}
			};
		}
	}
}
