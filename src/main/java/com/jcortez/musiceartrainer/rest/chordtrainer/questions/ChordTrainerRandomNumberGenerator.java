package com.jcortez.musiceartrainer.rest.chordtrainer.questions;

// Manages generating random numbers for the chord ear trainer.
public interface ChordTrainerRandomNumberGenerator
{
    // Generates the next random int between 0 and up to the specified number
    // (exclusive).
    public int nextInt(int end);
}
