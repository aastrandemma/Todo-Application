package org.github.aastrandemma.data.sequencer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoItemIdSequencerTest {
    @Test
    public void testSingleTonInstance() {
        assertNotNull(TodoItemIdSequencer.getInstance());
    }
}
