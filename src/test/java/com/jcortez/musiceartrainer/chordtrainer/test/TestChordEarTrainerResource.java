package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.jcortez.musiceartrainer.rest.ChordEarTrainerResource;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.TrainerMode;

// Tests the general methods called by the REST web services. Tests on more
// specific web services are in the other test Java classes.
public class TestChordEarTrainerResource
{
    private ChordEarTrainerResource restResource = new ChordEarTrainerResource();

    @Test
    // Tests the getEarTrainerModes() method in ChordEarTrainerResource to ensure
    // that the correct modes are returned.
    public void testGetEarTrainerModes()
    {
        List<TrainerMode> trainerModes = restResource.getEarTrainerModes();
        assertEquals(2, trainerModes.size());
        assertEquals("Challenge Mode", trainerModes.get(0).getName());
        assertEquals("Custom Mode", trainerModes.get(1).getName());
    }
}