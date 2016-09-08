package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Chord;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.XMLChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.XMLChordFileStore.QuestionXMLFile;

// Tests the XMLChordFileStore class.
public class TestXMLChordFileStore
{
    private static XMLChordFileStore fileStore = null;

    @BeforeClass
    public static void setup()
    {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bindConstant().annotatedWith(QuestionXMLFile.class).to("src/test/resources/question_data_test.xml");
            }
        });
        fileStore = injector.getInstance(XMLChordFileStore.class);
    }

    @Test
    // Tests the getChordMidiFileName() method with a valid Chord object.
    public void testGetChordMidiFileNameValidChord()
    {
        Chord chord = new Chord(ChordRoot.E, ChordQuality.HALF_DIM_SEVENTH, ChordInversion.ROOT_POS);
        String fileName = fileStore.getChordMidiFileName(chord);
        assertEquals("196.mid", fileName);
    }

    @Test
    // Tests the getChordMidiFileName() method with an invalid Chord object.
    public void testGetChordMidiFileNameInvalidChord()
    {
        Chord chord = new Chord(ChordRoot.D, ChordQuality.MAJ, ChordInversion.THIRD_INV);
        String fileName = fileStore.getChordMidiFileName(chord);
        assertEquals("", fileName);
    }

    @Test
    // Tests the getChordForMidiFile() method with a valid MIDI file name.
    public void testGetChordForMidiFileValidFile()
    {
        Chord generatedChord = fileStore.getChordForMidiFile("361.mid");
        Chord correctChord = new Chord(ChordRoot.G_SHARP_A_FLAT, ChordQuality.MIN_MAJ_SEVENTH, ChordInversion.FIRST_INV);
        assertEquals(correctChord, generatedChord);
    }

    @Test
    // Tests the getChordForMidiFile() method with an invalid MIDI file name.
    public void testGetChordForMidiFileInvalidFile()
    {
        Chord generatedChord = fileStore.getChordForMidiFile("test.mid");
        Chord correctChord = new Chord(null, null, null);
        assertEquals(correctChord, generatedChord);
    }

    @Test
    // Tests the getMIDINotes() method with a valid Chord object.
    public void testGetMIDINotesValidChord()
    {
        Chord chord = new Chord(ChordRoot.E, ChordQuality.HALF_DIM_SEVENTH, ChordInversion.ROOT_POS);
        int[] midiNotes = fileStore.getMIDINotes(chord);
        assertEquals(64, midiNotes[0]);
        assertEquals(67, midiNotes[1]);
        assertEquals(70, midiNotes[2]);
        assertEquals(74, midiNotes[3]);
    }

    @Test
    // Tests the getMIDINotes() method with an invalid Chord object.
    public void testGetMIDINotesInvalidChord()
    {
        Chord chord = new Chord(ChordRoot.A_SHARP_B_FLAT, ChordQuality.AUG, ChordInversion.THIRD_INV);
        int[] midiNotes = fileStore.getMIDINotes(chord);
        assertEquals(0, midiNotes.length);
    }
}
