package net.fryol.jtoys.hprisoners.riddle;

public class RiddleExperiment {
	private int run;
	private int largestLoop;
	private String resultString;
	private Boolean result;

	public RiddleExperiment(int run, int totalLoops, int loopsAllowed) {
		this.setRun(run);
		this.setLargestLoop(totalLoops);

		if(this.getLargestLoop() > loopsAllowed) {
			this.setResult("DEAD");
		} else {
			this.setResult("SURVIVED");
		}
	}
	
	public void setRun(int rn) {
		this.run = rn;
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

	public int getRun() {
		return run;
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
