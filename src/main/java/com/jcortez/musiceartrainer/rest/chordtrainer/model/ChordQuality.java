package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Collection;
import java.util.HashMap;

// An enum corresponding to chord qualities.
// TODO: Add chord extensions here?
public enum ChordQuality
{
    MAJ, MIN, DIM, AUG, DOM_SEVENTH, MAJ_SEVENTH, MIN_SEVENTH, MIN_MAJ_SEVENTH,
    HALF_DIM_SEVENTH, DIM_SEVENTH, SUS_2, SUS_4;

    // Stores the mapping between an enum value and the full chord quality name.
    private static HashMap<ChordQuality, String> fullChordQualityNames = new HashMap<ChordQuality, String>();

    static
    {
        initializeFullNames();
    }

    // Initializes and sets the full chord quality names.
    private static void initializeFullNames()
    {
        fullChordQualityNames.put(MAJ, "Major");
        fullChordQualityNames.put(MIN, "Minor");
        fullChordQualityNames.put(DIM, "Diminished");
        fullChordQualityNames.put(AUG, "Augmented");
        fullChordQualityNames.put(DOM_SEVENTH, "Dominant Seventh");
        fullChordQualityNames.put(MAJ_SEVENTH, "Major Seventh");
        fullChordQualityNames.put(MIN_SEVENTH, "Minor Seventh");
        fullChordQualityNames.put(MIN_MAJ_SEVENTH, "Minor Major Seventh");
        fullChordQualityNames.put(HALF_DIM_SEVENTH, "Half Diminished Seventh");
        fullChordQualityNames.put(DIM_SEVENTH, "Diminished Seventh");
        fullChordQualityNames.put(SUS_2, "Sus 2");
        fullChordQualityNames.put(SUS_4, "Sus 4");
    }

    // Returns the full chord quality name for an enum value.
    public static String getFullChordQualityName(ChordQuality quality)
    {
        return fullChordQualityNames.get(quality);
    }

    // Returns an array of all of the full chord quality names. A specific order
    // for the values in the array is not guaranteed.
    public static String[] getAllFullChordQualityNames()
    {
        Collection<String> qualitiesCollection = fullChordQualityNames.values();
        return qualitiesCollection.toArray(new String[qualitiesCollection.size()]);
    }
}
