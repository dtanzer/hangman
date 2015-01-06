package net.davidtanzer.examples.hangman.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LifecycleCallbacksTest.TestConfiguration.class)
public class LifecycleCallbacksTest {
	@Autowired
	public ApplicationContext applicationContext;

	@Test
	public void testAnnotationBasedCallbacks() {
		AnnotatedLifeCycleCallbacks bean = applicationContext.getBean(AnnotatedLifeCycleCallbacks.class);

		assertTrue(bean.initialized);
	}

	@Test
	public void testConfiguredCallbacks() {
		ConfiguredLifeCycleCallbacks bean = applicationContext.getBean(ConfiguredLifeCycleCallbacks.class);

		assertTrue(bean.initialized);
	}

	public static class AnnotatedLifeCycleCallbacks {
		private boolean initialized;

		@PostConstruct
		public void init() {
			System.out.println("Initializing "+this);
			initialized = true;
		}

		@PreDestroy
		public void teardown() {
			System.out.println("Destroying "+this);
		}
	}

	public static class ConfiguredLifeCycleCallbacks {
		private boolean initialized;

		public void init() {
			System.out.println("Initializing "+this);
			initialized = true;
		}

		public void teardown() {
			System.out.println("Destroying "+this);
		}
	}

	@Configuration
	public static class TestConfiguration {
		@Bean
		public AnnotatedLifeCycleCallbacks alcb() {
			return new AnnotatedLifeCycleCallbacks();
		}

		@Bean(initMethod = "init", destroyMethod = "teardown")
		public ConfiguredLifeCycleCallbacks clcb() {
			return new ConfiguredLifeCycleCallbacks();
		}
	}
}
