package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
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
        ArrayList<String> qualities = chordCharacteristics.getTrainerChordQualities();
        ArrayList<String> inversions = chordCharacteristics.getTrainerChordInversions();

        assertEquals(12, qualities.size());
        assertEquals(4, inversions.size());
        assertEquals("Major", qualities.get(0));
        assertEquals("Minor", qualities.get(1));
        assertEquals("Diminished", qualities.get(2));
        assertEquals("Augmented", qualities.get(3));
        assertEquals("Dominant Seventh", qualities.get(4));
        assertEquals("Major Seventh", qualities.get(5));
        assertEquals("Minor Seventh", qualities.get(6));
        assertEquals("Minor Major Seventh", qualities.get(7));
        assertEquals("Half Diminished Seventh", qualities.get(8));
        assertEquals("Diminished Seventh", qualities.get(9));
        assertEquals("Sus 2", qualities.get(10));
        assertEquals("Sus 4", qualities.get(11));
        assertEquals("Root Position", inversions.get(0));
        assertEquals("First Inversion", inversions.get(1));
        assertEquals("Second Inversion", inversions.get(2));
        assertEquals("Third Inversion", inversions.get(3));
    }
}
