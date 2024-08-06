package net.fryol.jtoys.hprisoners.riddle;

import net.fryol.jtoys.hprisoners.prison.PrisonFloor;
import net.fryol.jtoys.hprisoners.prison.BoxLoops;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Riddle {	
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";

	public static void main(String[] args) {
		double successPercent;

		/**
		 * experimentResults: this is basically a counter for two "key" strings: Success
		 * and Failure, and the value associated for each of them gets appended for every
		 * encounter of Success or Failure from a RiddleExperiment.getResult()
		 */
		HashMap<String, Integer> experimentResults;

		// this also means, numPrisoners
		int numBoxes = 100;

		// this also means, allowBoxesToOpen
		int loopsAllowed = 50; 

		// how many times we're gonna run this
		int experimentSize = 100;

		List<RiddleExperiment> experimentList;	

		experimentList = runExperiment(experimentSize, numBoxes, loopsAllowed);

		experimentResults = calcExperimentResults(experimentList);

		successPercent = calcSuccessPercent(experimentResults);

		System.out.println("---------------");
		System.out.println("Total Success %age: " + successPercent);
		System.out.println("---------------");
	}

	protected static double calcSuccessPercent(HashMap<String, Integer> experimentResults) {
		return (double) experimentResults.get(SUCCESS) / (experimentResults.get(FAILURE) + experimentResults.get(FAILURE)) * 100;
	}

	protected static HashMap<String, Integer> calcExperimentResults(List<RiddleExperiment> experimentList) {
		HashMap<String, Integer> experimentResults = new HashMap<>();
		experimentResults.put(SUCCESS, 0);
		experimentResults.put(FAILURE, 0);

		for(RiddleExperiment experiment : experimentList) {
			if(Boolean.TRUE.equals(experiment.getStatus())) {
				experimentResults.put(SUCCESS, experimentResults.get(SUCCESS) + 1);
			} else {
				experimentResults.put(FAILURE, experimentResults.get(FAILURE) + 1);
			}
		}
		return experimentResults;
	}

	protected static List<RiddleExperiment> runExperiment(int experimentSize, int numBoxes, int loopsAllowed) {
		List<RiddleExperiment> experiments = new ArrayList<>();

		// running the experiment experimentSize times
		for(int i=0; i<experimentSize; i++) {
			PrisonFloor prisonFlr = new PrisonFloor(numBoxes);
			BoxLoops riddleLoops = new BoxLoops(prisonFlr);
			RiddleExperiment experiment;

			experiment = new RiddleExperiment(i+1, riddleLoops.getLargestLoop(), loopsAllowed);

			experiments.add(experiment);
			
			System.out.println("Run " + experiment.getExperimentSize() + " | Result: " + experiment.getResult() + " | Largest loop: " + experiment.getLargestLoop());
		}

		return experiments;
	}
}
