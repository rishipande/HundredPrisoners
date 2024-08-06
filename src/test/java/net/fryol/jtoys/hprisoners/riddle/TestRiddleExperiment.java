package net.fryol.jtoys.hprisoners.riddle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestRiddleExperiment {
	@Test
	void testRiddleExperiment() {
		RiddleExperiment rExp = new RiddleExperiment(5, 10, 20);

		if(rExp.getStatus()) {
			assertEquals("SURVIVED", rExp.getResult());
		}
		else {
			assertEquals("DEAD", rExp.getResult());
		}
	}
}
