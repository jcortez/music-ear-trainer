package com.jcortez.musiceartrainer.rest.chordtrainer.model;

//Interface to use with Guice. See ChallengeModeImpl for more info.
public interface ChallengeMode extends TrainerMode
{
    // Returns the next question to present to the user using the chord
    // characteristics corresponding to the challenge.
    public Question getNextQuestion(int challengeId);
}
