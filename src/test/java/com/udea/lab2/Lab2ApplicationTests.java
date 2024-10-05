package com.udea.lab2;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Lab2ApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	void health() {
		assertEquals("HEALTH CHECK OK!", dataController.healthCheck());
	}

	@Test
	void version() {
		assertEquals("The actual version is 1.0.0", dataController.version());
	}

	@Test
	void nationLength() {
		 Integer nationsLength = dataController.getRandomnNations().size();
		 assertEquals(10, nationsLength);
	}

	@Test
	void currenciesLength() {
		Integer currenciesLength = dataController.getRandomnCurrencies().size();
		assertEquals(20, currenciesLength);
	}

	@Test
	public void testRandomCurrenciesCodeFormat() {
		DataController controller = new DataController();
		JsonNode response = controller.getRandomnCurrencies();
		for (int i = 0; i < response.size(); i++) {
			JsonNode currency = response.get(i);
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}")); // Check for 3 uppercase letters format
		}
	}

	@Test
	public void testRandomNationsPerformance() {
		DataController controller = new DataController();
		long startTime = System.currentTimeMillis();
		controller.getRandomnNations();
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println(executionTime);
// Assert that execution time is within acceptable limits
		assertTrue(executionTime < 2000); // 2 second threshold
	}
	@Test
	void aviationsLength() {
		Integer aviationsLength = dataController.getRandomnAviation().size();
		assertEquals(20, aviationsLength);
	}
}
