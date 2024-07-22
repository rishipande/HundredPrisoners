package net.fryol.jtoys.hprisoners.prison;

class Slip {

	private int slipNumber;

	Slip(int slipN) {
		slipNumber = slipN;
	}

	public void setSlipNumber(int slipN) {
		this.slipNumber = slipN;
	}

	public int getSlipNumber() {
		return this.slipNumber;
	}	

	public int getSlipIndex() {
		return this.slipNumber - 1;
	}
}
