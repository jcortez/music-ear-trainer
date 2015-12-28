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
        assertTrue(TestUtilities.stringArrayContains(qualities, "Major"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Minor"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Diminished"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Augmented"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Dominant Seventh"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Major Seventh"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Minor Seventh"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Minor Major Seventh"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Half Diminished Seventh"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Diminished Seventh"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Sus 2"));
        assertTrue(TestUtilities.stringArrayContains(qualities, "Sus 4"));
        assertTrue(TestUtilities.stringArrayContains(inversions, "Root Position"));
        assertTrue(TestUtilities.stringArrayContains(inversions, "First Inversion"));
        assertTrue(TestUtilities.stringArrayContains(inversions, "Second Inversion"));
        assertTrue(TestUtilities.stringArrayContains(inversions, "Third Inversion"));
    }

}
