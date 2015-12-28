package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Collection;
import java.util.HashMap;

// An enum corresponding to the root note of chords. A root note can correspond
// to 2 enharmonic equivalent notes.
public enum ChordRoot
{
    C, C_SHARP_D_FLAT, D, D_SHARP_E_FLAT, E, F, F_SHARP_G_FLAT, G, G_SHARP_A_FLAT,
    A, A_SHARP_B_FLAT, B_C_FLAT;

    // Stores the mapping between an enum value and the full chord root name.
    private static HashMap<ChordRoot, String> fullChordRootNames = new HashMap<ChordRoot, String>();

    static
    {
        initializeFullNames();
    }

    // Initializes and sets the full chord root names.
    private static void initializeFullNames()
    {
        fullChordRootNames.put(C, "C");
        fullChordRootNames.put(C_SHARP_D_FLAT, "C#/Db");
        fullChordRootNames.put(D, "D");
        fullChordRootNames.put(D_SHARP_E_FLAT, "D#/Eb");
        fullChordRootNames.put(E, "E");
        fullChordRootNames.put(F, "F");
        fullChordRootNames.put(F_SHARP_G_FLAT, "F#/Gb");
        fullChordRootNames.put(G, "G");
        fullChordRootNames.put(G_SHARP_A_FLAT, "G#/Ab");
        fullChordRootNames.put(A, "A");
        fullChordRootNames.put(A_SHARP_B_FLAT, "A#/Bb");
        fullChordRootNames.put(B_C_FLAT, "B/Cb");
    }

    // Returns the full chord root name for an enum value.
    public static String getFullChordRootName(ChordRoot root)
    {
        return fullChordRootNames.get(root);
    }

    // Returns an array of all of the full chord root names. A specific order
    // for the values in the array is not guaranteed.
    public static String[] getAllFullChordRootNames()
    {
        Collection<String> rootsCollection = fullChordRootNames.values();
        return rootsCollection.toArray(new String[rootsCollection.size()]);
    }
}
