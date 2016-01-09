package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.inject.Singleton;

// A singleton object that corresponds to all of the chord characteristics that
// the user can select to be tested by the chord ear trainer.
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Singleton
public class UserSelectableChordCharacteristicsImpl implements UserSelectableChordCharacteristics
{
    public UserSelectableChordCharacteristicsImpl()
    {
    }

    @XmlElement
    @Override
    // Returns the chord qualities that the user can select.
    public String[] getTrainerChordQualities()
    {
        return ChordQuality.getAllFullChordQualityNames();
    }

    @XmlElement
    @Override
    // Returns the chord inversions that the user can select.
    public String[] getTrainerChordInversions()
    {
        return ChordInversion.getAllFullChordInversionNames();
    }

}
