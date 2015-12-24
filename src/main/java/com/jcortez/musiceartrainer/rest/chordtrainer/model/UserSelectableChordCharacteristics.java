package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// A singleton object that contains all of the chord characteristics that the
// user can select to be tested by the chord ear trainer.
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserSelectableChordCharacteristics
{
    private static volatile UserSelectableChordCharacteristics instance = null;
    private ChordQuality[] trainerChordQualities = null;
    private ChordInversion[] trainerChordInversions = null;

    private UserSelectableChordCharacteristics()
    {
        trainerChordQualities = ChordQuality.values();
        trainerChordInversions = ChordInversion.values();
    }

    public static synchronized UserSelectableChordCharacteristics getInstance()
    {
        if (instance == null)
        {
            instance = new UserSelectableChordCharacteristics();
        }

        return instance;
    }

    @XmlElement
    public ArrayList<String> getTrainerChordQualities()
    {
        // The ChordQuality enum values are converted to their full names to be
        // presented to the user.
        ArrayList<String> chordQualityNames = new ArrayList<String>();

        for (ChordQuality q : trainerChordQualities)
        {
            chordQualityNames.add(q.getChordQualityName());
        }

        return chordQualityNames;
    }

    @XmlElement
    public ArrayList<String> getTrainerChordInversions()
    {
        // The ChordInversion enum values are converted to their full names to be
        // presented to the user.
        ArrayList<String> chordInversionNames = new ArrayList<String>();

        for (ChordInversion i : trainerChordInversions)
        {
            chordInversionNames.add(i.getInversionName());
        }

        return chordInversionNames;
    }

}
