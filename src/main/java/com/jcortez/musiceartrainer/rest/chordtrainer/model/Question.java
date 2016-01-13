package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// A class corresponding to a question that the chord ear trainer presents to
// the user.
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Question
{
    // The file name of the MIDI file to play to the user for this question.
    private String questionMidiFileName = null;
    // The Chord object corresponding to the correct answer. This will not be
    // presented to the user.
    private Chord answer = null;

    public Question(String fileName, Chord answer)
    {
        questionMidiFileName = fileName;
        this.answer = answer;
    }

    // Sets the MIDI file name.
    public void setQuestionMidiFileName(String file)
    {
        questionMidiFileName = file;
    }

    // Gets the MIDI file name.
    @XmlElement
    public String getQuestionMidiFileName()
    {
        return questionMidiFileName;
    }
 
    // Returns the correct answer for this question.
    @XmlTransient
    public Chord getAnswer()
    {
        return answer;
    }

}
