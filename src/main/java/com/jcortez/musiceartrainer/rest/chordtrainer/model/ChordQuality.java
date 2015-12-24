package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// An enum corresponding to chord qualities.
public enum ChordQuality
{
    MAJ("Major"),
    MIN("Minor"),
    DIM("Diminished"),
    AUG("Augmented"),
    DOM_SEVENTH("Dominant Seventh"),
    MAJ_SEVENTH("Major Seventh"),
    MIN_SEVENTH("Minor Seventh"),
    MIN_MAJ_SEVENTH("Minor Major Seventh"),
    HALF_DIM_SEVENTH("Half Diminished Seventh"),
    DIM_SEVENTH("Diminished Seventh"),
    SUS_2("Sus 2"),
    SUS_4("Sus 4");

    // TODO: Add chord extensions here?

    private String chordQualityName = "";

    ChordQuality(String name)
    {
        chordQualityName = name;
    }

    public String getChordQualityName()
    {
        return chordQualityName;
    }
}
