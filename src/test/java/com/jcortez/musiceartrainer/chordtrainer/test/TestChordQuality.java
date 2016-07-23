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

    @Test
    // Tests the hasThirdInversion() method with qualities that have third inversions.
    public void testHasThirdInversionWithValidQualities()
    {
        ChordQuality[] qualitiesWithThirdInv = new ChordQuality[] {
            ChordQuality.DOM_SEVENTH, ChordQuality.MAJ_SEVENTH,
            ChordQuality.MIN_SEVENTH, ChordQuality.MIN_MAJ_SEVENTH,
            ChordQuality.HALF_DIM_SEVENTH, ChordQuality.DIM_SEVENTH };

        for (ChordQuality q : qualitiesWithThirdInv)
        {
            assertTrue(ChordQuality.hasThirdInversion(q));
        }
    }

    @Test
    // Tests the hasThirdInversion() method with qualities that do not have third
    // inversions.
    public void testHasThirdInversionWithInvalidQualities()
    {
        ChordQuality[] qualitiesWithNoThirdInv = new ChordQuality[] {
            ChordQuality.MAJ, ChordQuality.MIN, ChordQuality.DIM,
            ChordQuality.AUG, ChordQuality.SUS_2, ChordQuality.SUS_4 };

        for (ChordQuality q : qualitiesWithNoThirdInv)
        {
            assertFalse(ChordQuality.hasThirdInversion(q));
        }
    }
}
