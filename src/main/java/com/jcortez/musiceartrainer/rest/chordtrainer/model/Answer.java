package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

// An answer that the user has submitted for a question.
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Answer
{
    // The file name of the MIDI file that was played to the user for the
    // question that this answer corresponds to.
    private String questionMidiFileName = null;
    // The user's answer represented by a Chord object.
    private Chord answer = null;

    public Answer()
    {
    }

    public Answer(String midiFileName, Chord userAnswer)
    {
        questionMidiFileName = midiFileName;
        answer = userAnswer;
    }

    // Returns the MIDI file name for the file that was played to the user.
    public String getQuestionMidiFileName()
    {
        return questionMidiFileName;
    }

    // Returns the user's answer.
    public Chord getAnswer()
    {
        return answer;
    }

    // Sets the MIDI file name for the file that was played to the user.
    public void setQuestionMidiFileName(String fileName)
    {
        questionMidiFileName = fileName;
    }

    // Sets the user's answer.
    public void setAnswer(Chord userAnswer)
    {
        answer = userAnswer;
    }
}
