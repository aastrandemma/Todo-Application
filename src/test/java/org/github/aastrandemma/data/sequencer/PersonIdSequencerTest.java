package org.github.aastrandemma.data.sequencer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonIdSequencerTest {
    @Test
    public void testSingleTonInstance() {
        assertNotNull(PersonIdSequencer.getInstance());
    }
}
