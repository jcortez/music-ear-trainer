package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

// The REST response that will be returned to the user after they have submitted
// their answer. This contains information about the user's answer to the question
// that was presented.
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerResponse
{
    // Will be set to true if the user's is correct, false if the user is incorrect.
    private boolean userAnswerCorrect = false;
    // The correct answer for the question that was presented to the user.
    private Chord correctAnswer = null;

    public AnswerResponse()
    {
        // An empty AnswerResponse object will be assumed to have an incorrect
        // answer.
        userAnswerCorrect = false;
        correctAnswer = null;
    }

    public AnswerResponse(boolean correct, Chord answer)
    {
        userAnswerCorrect = correct;
        correctAnswer = answer;
    }

    // Returns true if the user's is correct, false if the user is incorrect.
    public boolean getUserAnswerCorrect()
    {
        return userAnswerCorrect;
    }

    // Returns the correct answer.
    public Chord getCorrectAnswer()
    {
        return correctAnswer;
    }
}
