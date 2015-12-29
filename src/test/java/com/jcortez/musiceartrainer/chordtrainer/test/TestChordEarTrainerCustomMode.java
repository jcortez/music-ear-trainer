package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.jcortez.musiceartrainer.rest.ChordEarTrainerResource;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristics;

// Tests the methods called by the REST web services pertaining to the custom
// mode for the chord ear trainer.
public class TestChordEarTrainerCustomMode
{
    private ChordEarTrainerResource restResource = new ChordEarTrainerResource();

    @Test
    // Tests the getCustomModeChordCharacteristics() method in
    // ChordEarTrainerResource to check that the correct chord characteristics
    // that the user can select are returned.
    public void testGetCustomModeChordCharacteristics()
    {
        UserSelectableChordCharacteristics chordCharacteristics = restResource.getCustomModeChordCharacteristics();
        String[] qualities = chordCharacteristics.getTrainerChordQualities();
        String[] inversions = chordCharacteristics.getTrainerChordInversions();

        assertEquals(12, qualities.length);
        assertEquals(4, inversions.length);
        assertEquals("Major", qualities[0]);
        assertEquals("Minor", qualities[1]);
        assertEquals("Diminished", qualities[2]);
        assertEquals("Augmented", qualities[3]);
        assertEquals("Dominant Seventh", qualities[4]);
        assertEquals("Major Seventh", qualities[5]);
        assertEquals("Minor Seventh", qualities[6]);
        assertEquals("Minor Major Seventh", qualities[7]);
        assertEquals("Half Diminished Seventh", qualities[8]);
        assertEquals("Diminished Seventh", qualities[9]);
        assertEquals("Sus 2", qualities[10]);
        assertEquals("Sus 4", qualities[11]);
        assertEquals("Root Position", inversions[0]);
        assertEquals("First Inversion", inversions[1]);
        assertEquals("Second Inversion", inversions[2]);
        assertEquals("Third Inversion", inversions[3]);
    }

}
