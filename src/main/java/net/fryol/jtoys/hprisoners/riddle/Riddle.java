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

		HashMap<String, Integer> experimentResults = new HashMap<>();
		experimentResults.put(SUCCESS, 0);
		experimentResults.put(FAILURE, 0);

		// this also means, numPrisoners
		int numBoxes = 100;

		// this also means, allowBoxesToOpen
		int loopsAllowed = 50; 

		// how many times we're gonna run this
		int experimentSize = 100;

		List<RiddleExperiment> experimentList;	

		experimentList = runExperiment(experimentSize, numBoxes, loopsAllowed);

		experimentList.forEach(experiment -> {
			if(Boolean.TRUE.equals(experiment.getStatus())) {
				experimentResults.put(SUCCESS, experimentResults.get(SUCCESS) + 1);
			} else {
				experimentResults.put(FAILURE, experimentResults.get(FAILURE) + 1);
			}
		});

		successPercent = (double) experimentResults.get(SUCCESS) / (experimentResults.get(FAILURE) + experimentResults.get(FAILURE)) * 100;

		System.out.println("---------------");
		System.out.println("Total Success %age: " + successPercent);
		System.out.println("---------------");
		
		//System.out.println(prisonFlr.toString());
		//System.out.println(riddleLoops.toString());
	}

	private static List<RiddleExperiment> runExperiment(int experimentSize, int numBoxes, int loopsAllowed) {
		List<RiddleExperiment> experiments = new ArrayList<>();

		// running the experiment experimentSize times
		for(int i=0; i<experimentSize; i++) {
			PrisonFloor prisonFlr = new PrisonFloor(numBoxes);
			BoxLoops riddleLoops = new BoxLoops(prisonFlr);
			RiddleExperiment experiment;

			experiment = new RiddleExperiment(i+1, riddleLoops.largestLoop(), loopsAllowed);

			experiments.add(experiment);
			
			System.out.println("Run " + experiment.getRun() + " | Result: " + experiment.getResult() + " | Largest loop: " + experiment.getLargestLoop());
		}

		return experiments;
	}
}
