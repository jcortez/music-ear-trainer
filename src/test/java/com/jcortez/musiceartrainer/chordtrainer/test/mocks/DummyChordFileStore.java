package com.jcortez.musiceartrainer.chordtrainer.test.mocks;

import com.google.inject.Singleton;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Chord;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;

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

    @Override
    public Chord getChordForMidiFile(String midiFileName)
    {
        return new Chord(ChordRoot.C, ChordQuality.MAJ, ChordInversion.ROOT_POS);
    }
}
