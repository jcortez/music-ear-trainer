package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MIDINotes
{
    private int[] midiNotes;

    public MIDINotes()
    {
        midiNotes = new int[0];
    }

    public MIDINotes(int[] notes)
    {
        midiNotes = notes;
    }

    public void setMidiNotes(int[] notes)
    {
        midiNotes = notes;
    }

    public int[] getMidiNotes()
    {
        return midiNotes;
    }

}
