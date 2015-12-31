package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.inject.Singleton;

// A class that represents the Custom Mode of the chord ear trainer.
@Singleton
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomModeImpl implements CustomMode
{
    private static final String CUSTOM_MODE_NAME = "Custom Mode";

    public CustomModeImpl() 
    {
    }

    @Override
    @XmlElement
    public String getName()
    {
        return CUSTOM_MODE_NAME;
    }

    @Override
    public boolean checkAnswer(Chord userAnswer, Chord correctAnswer)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
