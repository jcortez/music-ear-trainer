package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Collection;
import com.google.common.collect.ImmutableBiMap;

// An enum corresponding to the root note of chords. A root note can correspond
// to 2 enharmonic equivalent notes.
public enum ChordRoot
{
    C, C_SHARP_D_FLAT, D, D_SHARP_E_FLAT, E, F, F_SHARP_G_FLAT, G, G_SHARP_A_FLAT,
    A, A_SHARP_B_FLAT, B_C_FLAT;

    // A bidirectional map that stores the mapping between an enum value and the
    // full chord root name. Elements are stored in insertion order.
    private static final ImmutableBiMap<ChordRoot, String> fullChordRootNames =
            ImmutableBiMap.<ChordRoot, String>builder()
            .put(C, "C")
            .put(C_SHARP_D_FLAT, "C#/Db")
            .put(D, "D")
            .put(D_SHARP_E_FLAT, "D#/Eb")
            .put(E, "E")
            .put(F, "F")
            .put(F_SHARP_G_FLAT, "F#/Gb")
            .put(G, "G")
            .put(G_SHARP_A_FLAT, "G#/Ab")
            .put(A, "A")
            .put(A_SHARP_B_FLAT, "A#/Bb")
            .put(B_C_FLAT, "B/Cb")
            .build();

    // Returns the full chord root name for an enum value.
    public static String getFullChordRootName(ChordRoot root)
    {
        return fullChordRootNames.get(root);
    }

    // Returns an array of all of the full chord root names in the order that
    // they were inserted.
    public static String[] getAllFullChordRootNames()
    {
        Collection<String> rootsCollection = fullChordRootNames.values();
        return rootsCollection.toArray(new String[rootsCollection.size()]);
    }
}
