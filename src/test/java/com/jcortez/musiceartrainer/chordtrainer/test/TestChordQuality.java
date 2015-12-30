package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;

// Tests the static methods for the ChordQuality enum.
public class TestChordQuality
{
    @Test
    // Tests the getChordQualityEnum() method with a valid String that has an
    // enum value.
    public void testGetChordQualityEnumValid()
    {
        ChordQuality quality = ChordQuality.getChordQualityEnum("Minor Seventh");
        assertEquals(ChordQuality.MIN_SEVENTH, quality);
    }

    @Test
    // Tests the getChordQualityEnum() method with a String that does not have
    // an enum value.
    public void testGetChordQualityEnumInvalid()
    {
        ChordQuality quality = ChordQuality.getChordQualityEnum("Test");
        assertNull(quality);
    }
    
}
