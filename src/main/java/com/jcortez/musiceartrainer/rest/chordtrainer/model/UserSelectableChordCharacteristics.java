package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// A singleton object that corresponds to all of the chord characteristics that
// the user can select to be tested by the chord ear trainer.
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserSelectableChordCharacteristics
{
    private static volatile UserSelectableChordCharacteristics instance = null;

    private UserSelectableChordCharacteristics()
    {
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
    public String[] getTrainerChordQualities()
    {
        return ChordQuality.getAllFullChordQualityNames();
    }

    @XmlElement
    public String[] getTrainerChordInversions()
    {
        return ChordInversion.getAllFullChordInversionNames();
    }

}
