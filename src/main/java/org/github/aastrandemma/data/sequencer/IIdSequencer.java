package org.github.aastrandemma.data.sequencer;

public interface IIdSequencer {
    int nextId();
    int getCurrentId();
    void setCurrentId(int id);
}