package net.fryol.jtoys.hprisoners.riddle;

public class RiddleExperiment {
	private int experimentSize;
	private int largestLoop;
	private String resultString;
	private Boolean result;

	public RiddleExperiment(int experimentSize, int numBoxes, int loopsAllowed) {
		this.setExperimentSize(experimentSize);
		this.setLargestLoop(numBoxes);

		if(this.getLargestLoop() > loopsAllowed) {
			this.setResult("DEAD");
		} else {
			this.setResult("SURVIVED");
		}
	}
	
	public void setExperimentSize(int es) {
		this.experimentSize = es;
	}

	public void setLargestLoop(int ll) {
		this.largestLoop = ll;
	}

	public void setResult(String rs) {
		this.resultString = rs;
		if(rs.contains("DEAD")) {
			this.result = Boolean.FALSE;
		} else {
			this.result = Boolean.TRUE;
		}
	}

	public int getExperimentSize() {
		return experimentSize;
	}

	public int getLargestLoop() {
		return largestLoop;
	}

	public String getResult() {
		return resultString;
	}

	public Boolean getStatus() {
		return result;
	}
}
