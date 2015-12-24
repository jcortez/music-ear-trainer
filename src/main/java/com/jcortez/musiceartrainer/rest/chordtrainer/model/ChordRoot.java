package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// An enum corresponding to the root note of chords. Sharp roots are denoted with
// "s" and flat roots are denoted with "b" in the enumerated names. A root note
// can correspond to 2 enharmonic equivalent notes.
public enum ChordRoot
{
    C("C"),
    CsDb("C#/Db"),
    D("D"),
    DsEb("D#/Eb"),
    E("E"),
    F("F"),
    FsGb("F#/Gb"),
    G("G"),
    GsAb("G#/Ab"),
    A("A"),
    AsBb("A#/Bb"),
    BCb("B/Cb");
    
    private String chordRootName = "";

    ChordRoot(String name)
    {
        chordRootName = name;
    }

    public String getChordRootName()
    {
        return chordRootName;
    }
}
