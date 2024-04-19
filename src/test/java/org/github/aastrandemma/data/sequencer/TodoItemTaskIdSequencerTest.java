package org.github.aastrandemma.data.sequencer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoItemTaskIdSequencerTest {
    @Test
    public void testSingleTonInstance() {
        assertNotNull(TodoItemTaskIdSequencer.getInstance());
    }
}