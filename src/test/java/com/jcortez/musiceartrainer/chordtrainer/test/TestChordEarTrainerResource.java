package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jcortez.musiceartrainer.rest.ChordEarTrainerResource;
import com.jcortez.musiceartrainer.rest.ChordEarTrainerServletModule;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.TrainerMode;

// Tests the general methods called by the REST web services. Tests on more
// specific web services are in the other test Java classes.
public class TestChordEarTrainerResource
{
    private ChordEarTrainerResource restResource;

    public TestChordEarTrainerResource()
    {
        Injector injector = Guice.createInjector(new ChordEarTrainerServletModule());
        restResource = injector.getInstance(ChordEarTrainerResource.class);
    }

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
