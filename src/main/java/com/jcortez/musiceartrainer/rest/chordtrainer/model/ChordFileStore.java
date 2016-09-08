package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// Stores the mapping between Chord objects and MIDI information.
public interface ChordFileStore
{
    // Gets the MIDI file name for the specified chord.
    public String getChordMidiFileName(Chord chord);
    // Gets the Chord object for the specified MIDI file name.
    public Chord getChordForMidiFile(String midiFileName);
    // Gets the MIDI notes for the specified chord.
    public int[] getMIDINotes(Chord chord);
}
