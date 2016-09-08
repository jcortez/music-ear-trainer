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
    // Will be set to true if the user's answer is correct, false if the user is incorrect.
    private boolean userAnswerCorrect = false;
    // The correct answer for the question that was presented to the user.
    private Chord correctAnswer = null;
    // The MIDI notes of the correct answer.
    private int[] midiNotes = new int[0];

    public AnswerResponse()
    {
        // An empty AnswerResponse object will be assumed to have an incorrect
        // answer.
        userAnswerCorrect = false;
        correctAnswer = null;
        midiNotes = new int[0];
    }

    public AnswerResponse(boolean correct, Chord answer, int[] notes)
    {
        userAnswerCorrect = correct;
        correctAnswer = answer;
        midiNotes = notes;
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

    public int[] getMidiNotes()
    {
        return midiNotes;
    }
}
