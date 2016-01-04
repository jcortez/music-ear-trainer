package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;

// Tests for the ChordCharacteristicsToTest class.
public class TestChordCharacteristicsToTest
{
 
    @Test
    // Tests the setChordQualities() method with normal input.
    public void testSetChordQualities()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordQualitiesNames = new String[2];
        testChordQualitiesNames[0] = "Diminished";
        testChordQualitiesNames[1] = "Minor Major Seventh";
        characteristics.setChordQualities(testChordQualitiesNames);

        HashSet<ChordQuality> qualities = characteristics.getChordQualities();
        assertEquals(2, qualities.size());
        assertTrue(qualities.contains(ChordQuality.DIM));
        assertTrue(qualities.contains(ChordQuality.MIN_MAJ_SEVENTH));
    }

    @Test
    // Tests calling the setChordQualities() method multiple times and checking
    // that the original results do not change.
    public void testSetChordQualitiesMultipleCalls()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordQualitiesNames = new String[2];
        testChordQualitiesNames[0] = "Sus 2";
        testChordQualitiesNames[1] = "Sus 4";
        characteristics.setChordQualities(testChordQualitiesNames);

        testChordQualitiesNames[0] = "Diminished";
        testChordQualitiesNames[1] = "Diminished Seventh";
        characteristics.setChordQualities(testChordQualitiesNames);

        testChordQualitiesNames[0] = "Augmented";
        testChordQualitiesNames[1] = "Diminished Seventh";
        characteristics.setChordQualities(testChordQualitiesNames);

        HashSet<ChordQuality> qualities = characteristics.getChordQualities();
        assertEquals(2, qualities.size());
        assertTrue(qualities.contains(ChordQuality.SUS_2));
        assertTrue(qualities.contains(ChordQuality.SUS_4));
    }

    @Test
    // Test calling the setChordQualities() method with an invalid chord quality.
    public void testSetChordQualitiesInvalidQuality()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordQualitiesNames = new String[2];
        testChordQualitiesNames[0] = "TEST";
        testChordQualitiesNames[1] = "Major";
        characteristics.setChordQualities(testChordQualitiesNames);

        HashSet<ChordQuality> qualities = characteristics.getChordQualities();
        assertEquals(1, qualities.size());
        assertTrue(qualities.contains(ChordQuality.MAJ));
    }

    @Test
    // Test calling the setChordQualities() method with inserting the same chord
    // quality multiple times.
    public void testSetChordQualitiesMultipleQuality()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordQualitiesNames = new String[3];
        testChordQualitiesNames[0] = "Major";
        testChordQualitiesNames[1] = "Minor";
        testChordQualitiesNames[2] = "Major";
        characteristics.setChordQualities(testChordQualitiesNames);

        HashSet<ChordQuality> qualities = characteristics.getChordQualities();
        assertEquals(2, qualities.size());
        assertTrue(qualities.contains(ChordQuality.MAJ));
        assertTrue(qualities.contains(ChordQuality.MIN));
    }

    @Test
    // Test that all chord qualities are added when the setChordQualities()
    // method is called with an array containing "all".
    public void testSetChordQualitiesAll()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordQualitiesNames = new String[1];
        testChordQualitiesNames[0] = "all";
        characteristics.setChordQualities(testChordQualitiesNames);

        HashSet<ChordQuality> qualities = characteristics.getChordQualities();
        ChordQuality[] allQualities = ChordQuality.values();
        assertEquals(allQualities.length, qualities.size());
        for (ChordQuality q : allQualities)
        {
            assertTrue(qualities.contains(q));
        }
    }

    @Test
    // Tests the setChordInversions() method with normal input.
    public void testSetChordInversions()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordInversionsNames = new String[2];
        testChordInversionsNames[0] = "Root Position";
        testChordInversionsNames[1] = "Third Inversion";
        characteristics.setChordInversions(testChordInversionsNames);

        HashSet<ChordInversion> inversions = characteristics.getChordInversions();
        assertEquals(2, inversions.size());
        assertTrue(inversions.contains(ChordInversion.ROOT_POS));
        assertTrue(inversions.contains(ChordInversion.THIRD_INV));
    }

    @Test
    // Tests calling the setChordInversions() method multiple times and checking
    // that the original results do not change.
    public void testSetChordInversionsMultipleCalls()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordInversionsNames = new String[2];
        testChordInversionsNames[0] = "Root Position";
        testChordInversionsNames[1] = "Third Inversion";
        characteristics.setChordInversions(testChordInversionsNames);

        testChordInversionsNames[0] = "First Inversion";
        testChordInversionsNames[1] = "Second Inversion";
        characteristics.setChordInversions(testChordInversionsNames);

        HashSet<ChordInversion> inversions = characteristics.getChordInversions();
        assertEquals(2, inversions.size());
        assertTrue(inversions.contains(ChordInversion.ROOT_POS));
        assertTrue(inversions.contains(ChordInversion.THIRD_INV));
    }

    @Test
    // Test calling the setChordInversions() method with an invalid chord quality.
    public void testSetChordInversionsInvalidQuality()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordInversionsNames = new String[2];

        testChordInversionsNames[0] = "First Inversion";
        testChordInversionsNames[1] = "TEST";
        characteristics.setChordInversions(testChordInversionsNames);

        HashSet<ChordInversion> inversions = characteristics.getChordInversions();
        assertEquals(1, inversions.size());
        assertTrue(inversions.contains(ChordInversion.FIRST_INV));
    }

    @Test
    // Test calling the setChordInversions() method with inserting the same chord
    // quality multiple times.
    public void testSetChordInversionsMultipleQuality()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordInversionsNames = new String[3];

        testChordInversionsNames[0] = "First Inversion";
        testChordInversionsNames[1] = "Second Inversion";
        testChordInversionsNames[2] = "First Inversion";
        characteristics.setChordInversions(testChordInversionsNames);

        HashSet<ChordInversion> inversions = characteristics.getChordInversions();
        assertEquals(2, inversions.size());
        assertTrue(inversions.contains(ChordInversion.FIRST_INV));
        assertTrue(inversions.contains(ChordInversion.SECOND_INV));
    }

    @Test
    // Test that all chord inversions are added when the setChordInversions()
    // method is called with an array containing "all".
    public void testSetChordInversionsAll()
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        String[] testChordInversionsNames = new String[1];
        testChordInversionsNames[0] = "all";
        characteristics.setChordInversions(testChordInversionsNames);

        HashSet<ChordInversion> inversions = characteristics.getChordInversions();
        ChordInversion[] allInversions = ChordInversion.values();
        assertEquals(allInversions.length, inversions.size());
        for (ChordInversion i : allInversions)
        {
            assertTrue(inversions.contains(i));
        }
    }

}
