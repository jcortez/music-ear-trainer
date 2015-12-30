package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;

// Tests the static methods for the ChordInversion enum.
public class TestChordInversion
{
    @Test
    // Tests the getChordInversionEnum() method with a valid String that has an
    // enum value.
    public void testGetChordInversionEnumValid()
    {
        ChordInversion inversion = ChordInversion.getChordInversionEnum("Second Inversion");
        assertEquals(ChordInversion.SECOND_INV, inversion);
    }

    @Test
    // Tests the getChordInversionEnum() method with a String that does not have
    // an enum value.
    public void testGetChordInversionEnumInvalid()
    {
        ChordInversion inversion = ChordInversion.getChordInversionEnum("Test");
        assertNull(inversion);
    }
}
