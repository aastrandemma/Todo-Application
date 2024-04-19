package org.github.aastrandemma.data.sequencer;

public class TodoItemTaskIdSequencer {
    // Have its own singleton instance and reusing functionality from BaseIdSequencer
    private static final BaseIdSequencer instance = new BaseIdSequencer();

    public static BaseIdSequencer getInstance() {
        return instance;
    }
}