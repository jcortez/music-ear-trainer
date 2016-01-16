package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// Stores the mapping between Chord objects and the MIDI file names that they
// correspond to.
public interface ChordFileStore
{
    // Gets the MIDI file name for the specified chord.
    public String getChordMidiFileName(Chord chord);
    // Gets the Chord object for the specified MIDI file name.
    public Chord getChordForMidiFile(String midiFileName);
}
