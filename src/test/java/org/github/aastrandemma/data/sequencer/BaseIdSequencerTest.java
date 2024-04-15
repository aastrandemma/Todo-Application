package org.github.aastrandemma.data.sequencer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseIdSequencerTest {
    BaseIdSequencer testObject;

    @BeforeEach
    public void setup() {
        testObject = new BaseIdSequencer();
    }

    @AfterEach
    public void tearDown() {
        testObject = null;
    }

    @Test
    public void testNextId() {
        assertEquals(1, testObject.nextId());
    }

    @Test
    public void testGetCurrentId() {
        assertEquals(0, testObject.getCurrentId());
    }

    @Test
    public void testSetCurrentId() {
        testObject.setCurrentId(2000);
        assertEquals(2000, testObject.getCurrentId());
    }
}