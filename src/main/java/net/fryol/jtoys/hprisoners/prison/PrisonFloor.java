package net.fryol.jtoys.hprisoners.prison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrisonFloor {

	List<Box> boxes;
	List<Slip> slips;

	public PrisonFloor(int size) {
				
		this.boxes = new ArrayList<>();
		this.slips = new ArrayList<>();

		for(int i=0; i<size; i++) {
			Slip slip = new Slip(i+1);
			this.slips.add(slip);
		}

		Collections.shuffle(slips);

		for(int i=0; i<size; i++) {
			Box box = new Box(i+1);
			box.setSlip(this.slips.get(i));

			this.boxes.add(box);
		}
	}

	@Override
	public String toString() {
		StringBuilder prisonFloorString = new StringBuilder();
		String lineSeparator = System.lineSeparator();

		boxes.forEach(box -> {
			prisonFloorString.append(String.format("Box: %02d ", box.getBoxNumber()));
			prisonFloorString.append(String.format("Slip: %02d ", box.getSlipNumber()));
			prisonFloorString.append(lineSeparator);
		});

		return prisonFloorString.toString();
	}

	public List<Box> getBoxesList() {
		return this.boxes;
	}
}

