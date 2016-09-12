package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.jcortez.musiceartrainer.chordtrainer.test.mocks.MockRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.ChordEarTrainerResource;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Answer;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.AnswerResponse;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeModeImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Chord;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomModeImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.InvalidChordCharacteristicsException;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.MIDINotes;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.TrainerMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristics;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristicsImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.XMLChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.XMLChordFileStore.QuestionXMLFile;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelector;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelectorImpl;

// Tests the general methods called by the REST web services. Tests on more
// specific web services are in the other test Java classes.
public class TestChordEarTrainerResource
{
    private ChordEarTrainerResource restResource;

    public TestChordEarTrainerResource()
    {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(CustomMode.class).to(CustomModeImpl.class);
                bind(ChallengeMode.class).to(ChallengeModeImpl.class);
                bind(ChordFileStore.class).to(XMLChordFileStore.class);
                bind(ChordTrainerRandomNumberGenerator.class).to(MockRandomNumberGenerator.class);
                bind(UserSelectableChordCharacteristics.class).to(UserSelectableChordCharacteristicsImpl.class);
                bind(QuestionSelector.class).to(QuestionSelectorImpl.class);
                bindConstant().annotatedWith(QuestionXMLFile.class).to("src/test/resources/question_data_test.xml");
            }
            @Provides
            @Named("randomValues")
            private ArrayList<Integer> getRandomValues()
            {
                ArrayList<Integer> mockRandomNextInts = new ArrayList<Integer>();
                // Random values returned for the testGetQuestionAndSubmitCorrectAnswer()
                // test.
                mockRandomNextInts.add(9);
                mockRandomNextInts.add(3);
                mockRandomNextInts.add(0);
                // Random values returned for the testGetQuestionAndSubmitIncorrectAnswer()
                // test.
                mockRandomNextInts.add(11);
                mockRandomNextInts.add(10);
                mockRandomNextInts.add(0);
                return mockRandomNextInts;
            }
        });
        restResource = injector.getInstance(ChordEarTrainerResource.class);
    }

    @Test
    // Tests the getEarTrainerModes() method in ChordEarTrainerResource to ensure
    // that the correct modes are returned.
    public void testGetEarTrainerModes()
    {
        List<TrainerMode> trainerModes = restResource.getEarTrainerModes();
        assertEquals(2, trainerModes.size());
        assertEquals("Challenge Mode", trainerModes.get(0).getName());
        assertEquals("Custom Mode", trainerModes.get(1).getName());
    }

    @Test
    // Tests going through the full process of obtaining a question from the chord
    // ear trainer and submitting a correct answer.
    public void testGetQuestionAndSubmitCorrectAnswer()
        throws InvalidChordCharacteristicsException
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        characteristics.setChordInversions(new String[] {"all"});
        characteristics.setChordQualities(new String[] {"all"});
        Question question = restResource.getNextCustomModeQuestion(characteristics);
        String midiFile = question.getQuestionMidiFileName();

        Chord chordAnswer = new Chord(ChordRoot.A, ChordQuality.AUG, ChordInversion.ROOT_POS);
        Answer answer = new Answer(midiFile, chordAnswer);
        AnswerResponse response = restResource.submitCustomModeAnswer(answer);
        assertEquals(ChordRoot.A, response.getCorrectAnswer().getChordRoot());
        assertEquals(ChordQuality.AUG, response.getCorrectAnswer().getChordQuality());
        assertEquals(ChordInversion.ROOT_POS, response.getCorrectAnswer().getChordInversion());
        assertTrue(response.getUserAnswerCorrect());
        assertEquals(69, response.getMidiNotes()[0]);
        assertEquals(73, response.getMidiNotes()[1]);
        assertEquals(77, response.getMidiNotes()[2]);
    }

    @Test
    // Tests going through the full process of obtaining a question from the chord
    // ear trainer and submitting an incorrect answer.
    public void testGetQuestionAndSubmitIncorrectAnswer()
        throws InvalidChordCharacteristicsException
    {
        ChordCharacteristicsToTest characteristics = new ChordCharacteristicsToTest();
        characteristics.setChordInversions(new String[] {"all"});
        characteristics.setChordQualities(new String[] {"all"});
        Question question = restResource.getNextCustomModeQuestion(characteristics);
        String midiFile = question.getQuestionMidiFileName();

        Chord chordAnswer = new Chord(ChordRoot.B_C_FLAT, ChordQuality.SUS_2, ChordInversion.ROOT_POS);
        Answer answer = new Answer(midiFile, chordAnswer);
        AnswerResponse response = restResource.submitCustomModeAnswer(answer);
        assertFalse(response.getUserAnswerCorrect());
    }

    @Test
    // Tests the getMidiNotesForChord() method.
    public void testGetMidiNotesForChord()
    {
        Chord chord = new Chord(ChordRoot.D_SHARP_E_FLAT, ChordQuality.MIN_SEVENTH, ChordInversion.FIRST_INV);
        int[] midiNotes = restResource.getMidiNotesForChord(chord).getMidiNotes();
        int[] correctNotes = new int[] { 66, 70, 73, 75 };
        assertTrue(Arrays.equals(midiNotes, correctNotes));
    }
}
