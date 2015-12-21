package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Corresponds to an chord ear trainer mode.
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TrainerMode
{
    @XmlElement
    private String modeName = "";

    public TrainerMode()
    {
        modeName = "";
    }

    public TrainerMode(String name)
    {
        modeName = name;
    }

    // Returns the name of this game mode.
    public String getName()
    {
        return modeName;
    }

    // Sets the name of this game mode.
    public void setName(String name)
    {
        modeName = name;
    }

    //TODO: add abstract methods for processing questions/answers
}
