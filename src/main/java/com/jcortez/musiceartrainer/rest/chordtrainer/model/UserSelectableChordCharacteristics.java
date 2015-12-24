package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// A singleton object that contains all of the chord characteristics that the
// user can select to be tested by the chord ear trainer.
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSelectableChordCharacteristics
{
    private static volatile UserSelectableChordCharacteristics instance = null;
    // Chord qualities and chord inversions are stored here and presented to the
    // user as Strings of their full names instead of their enum values.
    @XmlElement
    private ArrayList<String> trainerChordQualities = new ArrayList<String>();
    @XmlElement
    private ArrayList<String> trainerChordInversions = new ArrayList<String>();

    private UserSelectableChordCharacteristics()
    {
        // Creating the lists of chord qualities and chord inversions from all
        // possible enum values.
        for (ChordQuality q : ChordQuality.values())
        {
            trainerChordQualities.add(q.getChordQualityName());
        }

        for (ChordInversion i : ChordInversion.values())
        {
            trainerChordInversions.add(i.getInversionName());
        }
    }

    public static synchronized UserSelectableChordCharacteristics getInstance()
    {
        if (instance == null)
        {
            instance = new UserSelectableChordCharacteristics();
        }

        return instance;
    }

    public ArrayList<String> getTrainerChordQualities()
    {
        return trainerChordQualities;
    }

    public ArrayList<String> getTrainerChordInversions()
    {
        return trainerChordInversions;
    }

}
