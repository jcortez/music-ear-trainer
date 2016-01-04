package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import com.google.inject.Singleton;

// A dummy class (stub) to store the MIDI file name mappings. See ChordFileStore
// for more details.
@Singleton
public class DummyChordFileStore implements ChordFileStore
{
    @Override
    public String getChordMidiFileName(Chord chord)
    {
        return "test.midi";
    }
}
