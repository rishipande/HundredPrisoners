package net.fryol.jtoys.hprisoners.prison;

class Box {

	private int boxNumber;
	private Slip slip;

	Box(int boxN) {
		this.boxNumber = boxN;
	}

	public void setSlip(Slip mySlip) {
		this.slip = mySlip;
	}

	public void setBoxNumber(int boxN) {
		this.boxNumber = boxN;
	}

	public int getBoxNumber() {
		return this.boxNumber;
	}

	public int getSlipNumber() {
		return this.slip.getSlipNumber();
	}

	public Slip getSlip() {
		return this.slip;
	}

	public int getBoxIndex() {
		return this.boxNumber - 1;
	}

	public int getSlipIndex() {
		return this.slip.getSlipIndex();
	}
}

