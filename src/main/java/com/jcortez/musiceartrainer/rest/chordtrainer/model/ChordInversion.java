package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Collection;
import java.util.HashMap;

// An enum corresponding to chord inversions.
public enum ChordInversion
{
    ROOT_POS, // Chord is not inverted if this value is used.
    FIRST_INV, SECOND_INV, THIRD_INV;

    // Stores the mapping between an enum value and the full chord inversion name.
    private static HashMap<ChordInversion, String> fullChordInversionNames = new HashMap<ChordInversion, String>();

    static
    {
        initializeFullNames();
    }

    // Initializes and sets the full chord inversion names.
    private static void initializeFullNames()
    {
        fullChordInversionNames.put(ROOT_POS, "Root Position");
        fullChordInversionNames.put(FIRST_INV, "First Inversion");
        fullChordInversionNames.put(SECOND_INV, "Second Inversion");
        fullChordInversionNames.put(THIRD_INV, "Third Inversion");
    }

    // Returns the full chord inversion name for an enum value.
    public static String getFullChordInversionName(ChordInversion inversion)
    {
        return fullChordInversionNames.get(inversion);
    }

    // Returns an array of all of the full chord inversion names. A specific order
    // for the values in the array is not guaranteed.
    public static String[] getAllFullChordInversionNames()
    {
        Collection<String> namesCollection = fullChordInversionNames.values();
        return namesCollection.toArray(new String[namesCollection.size()]);
    }
}
