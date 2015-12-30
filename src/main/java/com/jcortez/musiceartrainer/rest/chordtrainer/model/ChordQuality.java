package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Collection;
import com.google.common.collect.ImmutableBiMap;

// An enum corresponding to chord qualities.
// TODO: Add chord extensions here?
public enum ChordQuality
{
    MAJ, MIN, DIM, AUG, DOM_SEVENTH, MAJ_SEVENTH, MIN_SEVENTH, MIN_MAJ_SEVENTH,
    HALF_DIM_SEVENTH, DIM_SEVENTH, SUS_2, SUS_4;

    // A bidirectional map that stores the mapping between an enum value and the
    // full chord quality name. Elements are stored in insertion order.
    private static final ImmutableBiMap<ChordQuality, String> fullChordQualityNames =
            ImmutableBiMap.<ChordQuality, String>builder()
            .put(MAJ, "Major")
            .put(MIN, "Minor")
            .put(DIM, "Diminished")
            .put(AUG, "Augmented")
            .put(DOM_SEVENTH, "Dominant Seventh")
            .put(MAJ_SEVENTH, "Major Seventh")
            .put(MIN_SEVENTH, "Minor Seventh")
            .put(MIN_MAJ_SEVENTH, "Minor Major Seventh")
            .put(HALF_DIM_SEVENTH, "Half Diminished Seventh")
            .put(DIM_SEVENTH, "Diminished Seventh")
            .put(SUS_2, "Sus 2")
            .put(SUS_4, "Sus 4")
            .build();

    // Returns the full chord quality name for an enum value.
    public static String getFullChordQualityName(ChordQuality quality)
    {
        return fullChordQualityNames.get(quality);
    }

    // Returns the enum value for the specified chord quality name. If there
    // is no enum value for the String, null is returned.
    public static ChordQuality getChordQualityEnum(String chordQualityName)
    {
        ImmutableBiMap<String, ChordQuality> inverseMap = fullChordQualityNames.inverse();
        return inverseMap.get(chordQualityName);
    }

    // Returns an array of all of the full chord quality names in the order
    // that they were inserted.
    public static String[] getAllFullChordQualityNames()
    {
        Collection<String> qualitiesCollection = fullChordQualityNames.values();
        return qualitiesCollection.toArray(new String[qualitiesCollection.size()]);
    }
}
