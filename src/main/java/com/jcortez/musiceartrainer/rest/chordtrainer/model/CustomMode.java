package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// Interface to use with Guice. See CustomModeImpl for more info.
public interface CustomMode extends TrainerMode
{
    // Returns the next question to present to the user using the chord
    // characteristics that were selected by the user.
    public Question getNextQuestion(ChordCharacteristicsToTest chordCharacteristics)
        throws InvalidChordCharacteristicsException;
}
