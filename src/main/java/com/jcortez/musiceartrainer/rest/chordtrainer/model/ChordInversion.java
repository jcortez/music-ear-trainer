package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// An enum corresponding to chord inversions.
public enum ChordInversion
{
    ROOT_POS("Root Position"), // Chord is not inverted if this value is used.
    FIRST_INV("First Inversion"),
    SECOND_INV("Second Inversion"),
    THIRD_INV("Third Inversion");
    
    private String inversionName = "";

    ChordInversion(String name)
    {
        inversionName = name;
    }

    public String getInversionName()
    {
        return inversionName;
    }
}
