package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.inject.Singleton;

// A class that represents the Challenge Mode of the chord ear trainer.
@Singleton
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ChallengeModeImpl implements ChallengeMode
{
    private static final String CHALLENGE_MODE_NAME = "Challenge Mode";

    public ChallengeModeImpl()
    {
    }

    @Override
    @XmlElement
    public String getName()
    {
        return CHALLENGE_MODE_NAME;
    }

    @Override
    public boolean checkAnswer(Chord userAnswer, Chord correctAnswer)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Question getNextQuestion(int challengeId)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
