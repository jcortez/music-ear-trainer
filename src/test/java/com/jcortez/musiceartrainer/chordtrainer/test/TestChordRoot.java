package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;

// Tests the static methods for the ChordRoot enum.
public class TestChordRoot
{
    @Test
    // Tests the getChordRootEnum() method with a valid String that has an enum
    // value.
    public void testGetChordRootEnumValid()
    {
        ChordRoot chordRoot = ChordRoot.getChordRootEnum("G#/Ab");
        assertEquals(ChordRoot.G_SHARP_A_FLAT, chordRoot);
    }

    @Test
    // Tests the getChordRootEnum() method with a String that does not have an
    // enum value.
    public void testGetChordRootEnumInvalid()
    {
        ChordRoot chordRoot = ChordRoot.getChordRootEnum("Test");
        assertNull(chordRoot);
    }
}
