package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Collection;
import com.google.common.collect.ImmutableBiMap;

// An enum corresponding to chord inversions.
public enum ChordInversion
{
    ROOT_POS, // Chord is not inverted if this value is used.
    FIRST_INV, SECOND_INV, THIRD_INV;

    // A bidirectional map that stores the mapping between an enum value and the
    // full chord inversion name. Elements are stored in insertion order.
    private static final ImmutableBiMap<ChordInversion, String> fullChordInversionNames =
            ImmutableBiMap.<ChordInversion, String>builder()
            .put(ROOT_POS, "Root Position")
            .put(FIRST_INV, "First Inversion")
            .put(SECOND_INV, "Second Inversion")
            .put(THIRD_INV, "Third Inversion")
            .build();

    // Returns the full chord inversion name for an enum value.
    public static String getFullChordInversionName(ChordInversion inversion)
    {
        return fullChordInversionNames.get(inversion);
    }

    // Returns an array of all of the full chord inversion names in the order
    // that they were inserted.
    public static String[] getAllFullChordInversionNames()
    {
        Collection<String> namesCollection = fullChordInversionNames.values();
        return namesCollection.toArray(new String[namesCollection.size()]);
    }
}
