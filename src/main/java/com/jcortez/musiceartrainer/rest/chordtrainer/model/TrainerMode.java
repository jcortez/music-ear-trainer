package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// Corresponds to an chord ear trainer mode.
public interface TrainerMode
{
    // Returns the name of this game mode.
    public String getName();

    // Determines if the user's answer is correct. Returns an AnswerResponse
    // object that contains all of the necessary information to return to the
    // user.
    public AnswerResponse checkAnswer(Answer userAnswer);
}
