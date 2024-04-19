package org.github.aastrandemma.data.sequencer;

public class BaseIdSequencer implements IIdSequencer {
    // Implements IIdSequencer interface
    // Provides the basic functionality for generating and managing IDs
    private int currentId = 0;

    @Override
    public int nextId() {
        return ++currentId;
    }

    @Override
    public int getCurrentId() {
        return currentId;
    }

    @Override
    public void setCurrentId(int id) {
        currentId = id;
    }
}