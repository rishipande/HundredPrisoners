package net.fryol.jtoys.hprisoners.prison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestBox {
    @Test
    void testBoxConstructor() {
        Box b = new Box(1);

        assertEquals(1, b.getBoxNumber());
    }

    @Test
    void testBoxSetGetSlipNumberIndex() {
        Box b = new Box(1);
        Slip s = new Slip(2);
        
        b.setSlip(s);
        assertEquals(2, b.getSlipNumber());
        assertEquals(1, b.getSlipIndex());
    }

    @Test
    void testBoxSetGetNumberIndex() {
        Box b = new Box(1);

        b.setBoxNumber(3);
        assertEquals(3, b.getBoxNumber());
        assertEquals(2, b.getBoxIndex());
    }
}

