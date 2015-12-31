package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// Corresponds to an chord ear trainer mode.
public interface TrainerMode
{
    // Returns the name of this game mode.
    public String getName();

    // Determines if the user's answer is correct. This method executes the
    // default logic but subclasses can override if necessary. True is returned
    // if the user's answer is correct, false is returned if it is not correct.
    public boolean checkAnswer(Chord userAnswer, Chord correctAnswer);
}
