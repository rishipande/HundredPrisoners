package net.fryol.jtoys.hprisoners.prison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestBoxLoops {

    @Test
    void testBoxLoopsLargestLoop() {
        BoxLoops bl = new BoxLoops(new PrisonFloor(100));
        boolean condition = bl.getLargestLoop() > 0;
        assertEquals(true, condition);

        bl = new BoxLoops(new PrisonFloor(100), 99);
        condition = bl.getLargestLoop() > 0;
        assertEquals(true, condition);

        // this should result in largest loops = 0
        bl = new BoxLoops(new PrisonFloor(100), 1000);
        condition = bl.getLargestLoop() > 0;
        assertEquals(false, condition);
    }

}
