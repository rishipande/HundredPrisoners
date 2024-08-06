package net.fryol.jtoys.hprisoners.riddle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class TestRiddle {

	@Test
	void testRiddleCalcExperimentResultsAndSuccess() {
		List<RiddleExperiment> expList = Riddle.runExperiment(5, 100, 50);
		HashMap<String, Integer> experimentResults = Riddle.calcExperimentResults(expList);

		boolean successes = experimentResults.get("Success") >= 0;
		boolean failures = experimentResults.get("Failure") >= 0;
		int totalSuccessesFailures = experimentResults.get("Success") + experimentResults.get("Failure");
		boolean successPercLtHundred = Riddle.calcSuccessPercent(experimentResults) <= 100;

		assertEquals(5, expList.size());
		assertEquals(true, successes);
		assertEquals(true, failures);
		assertEquals(5, totalSuccessesFailures);
		assertEquals(true, successPercLtHundred);
	}

	@Test
	void testRiddleRunExperiment() {
		List<RiddleExperiment> expList = Riddle.runExperiment(5, 100, 50);
		int expNumber = 0;

		assertEquals(5, expList.size());

		for (RiddleExperiment experiment : expList) {
			boolean validResult = experiment.getResult().equals("DEAD") || experiment.getResult().equals("SURVIVED");
			boolean loopGtZero = experiment.getLargestLoop() > 0;
			expNumber++;

			assertEquals(expNumber, experiment.getExperimentSize());
			assertEquals(true, validResult);
			assertEquals(true, loopGtZero);
		}
	}
}
