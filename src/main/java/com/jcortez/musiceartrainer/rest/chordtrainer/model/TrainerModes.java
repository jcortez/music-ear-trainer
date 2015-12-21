package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// A singleton class that stores information on all chord trainer modes.
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TrainerModes
{
    private static volatile TrainerModes instance = null;
    @XmlElement
    private static List<TrainerMode> trainerModes = new ArrayList<TrainerMode>();

    private TrainerModes() {
        initializeTrainerModes();
    }

    public static synchronized TrainerModes getInstance()
    {
        if (instance == null)
        {
            instance = new TrainerModes();
        }

        return instance;
    }

    // Does the appropriate work needed to initialize all chord trainer modes.
    private void initializeTrainerModes()
    {
        trainerModes = new ArrayList<TrainerMode>();
        ChallengeMode challengeMode = new ChallengeMode();
        CustomMode customMode = new CustomMode();
        trainerModes.add(challengeMode);
        trainerModes.add(customMode);
    }

    // Gets a list of all of the chord trainer modes supported.
    public List<TrainerMode> getTrainerModes()
    {
        return trainerModes;
    }

}
