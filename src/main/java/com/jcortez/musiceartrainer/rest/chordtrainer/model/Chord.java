package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// A data structure corresponding to a chord in the chord ear trainer. A Chord
// object will contain all chord characteristics.
public class Chord
{
    private ChordRoot chordRoot = null;
    private ChordQuality chordQuality = null;
    private ChordInversion chordInversion = null;

    public Chord(ChordRoot root, ChordQuality quality, ChordInversion inversion)
    {
        chordRoot = root;
        chordQuality = quality;
        chordInversion = inversion;
    }

    // Returns the ChordRoot enum value that corresponds to the root of this Chord
    // object.
    public ChordRoot getChordRoot()
    {
        return chordRoot;
    }

    // Returns the ChordQuality enum value that corresponds to the chord quality of
    // this chord object.
    public ChordQuality getChordQuality()
    {
        return chordQuality;
    }

    // Returns the ChordInversion enum value that corresponds to the chord inversion
    // of this chord. If the value is ROOT_POS, then this chord is not inverted.
    public ChordInversion getChordInversion()
    {
        return chordInversion;
    }
}
