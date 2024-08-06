package net.fryol.jtoys.hprisoners.prison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class TestPrisonFloor {
    @Test
    void testPrisonFloorConstructor() {
        PrisonFloor pf = new PrisonFloor(3);
        List<Box> bl = pf.getBoxesList();

        assertEquals(3, bl.size());

        assertEquals(1, bl.get(0).getBoxNumber());
        assertEquals(2, bl.get(1).getBoxNumber());
        assertEquals(3, bl.get(2).getBoxNumber());

        assertEquals(0, bl.get(0).getBoxIndex());
        assertEquals(1, bl.get(1).getBoxIndex());
        assertEquals(2, bl.get(2).getBoxIndex());    
    }

    /* this test will always fail since slips get shuffled in boxes
    @Test
    void testPrisonFloorToString() {
        PrisonFloor pf = new PrisonFloor(3);
        StringBuilder pfs = new StringBuilder();

        pfs = pfs.append("Box: 01 ");
        pfs = pfs.append("Slip: 01 " + System.lineSeparator());
        pfs = pfs.append("Box: 02 ");
        pfs = pfs.append("Slip: 02 " + System.lineSeparator());
        pfs = pfs.append("Box: 03 ");
        pfs = pfs.append("Slip: 03 " + System.lineSeparator());

        assertEquals(pfs.toString(), pf.toString());
    }
    */
}

