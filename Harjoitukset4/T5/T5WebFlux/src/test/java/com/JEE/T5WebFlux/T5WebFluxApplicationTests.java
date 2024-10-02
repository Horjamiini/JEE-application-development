package com.JEE.T5WebFlux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class T5WebFluxApplicationTests {

	@Test
	void contextLoads() {
	}

}
