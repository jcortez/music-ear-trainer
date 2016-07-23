package com.jcortez.musiceartrainer.rest.chordtrainer.questions;

import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.InvalidChordCharacteristicsException;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;

// A class that manages selecting questions to present to the user.
public interface QuestionSelector
{
    // Randomly selects the next question to present to the user with the
    // specified chord characteristics that can be selected from for testing.
    // If the chord characteristics are not valid, then an
    // InvalidChordCharacteristicsException is thrown.
    public Question selectNextQuestion(ChordCharacteristicsToTest chordCharacteristics)
        throws InvalidChordCharacteristicsException;
}
