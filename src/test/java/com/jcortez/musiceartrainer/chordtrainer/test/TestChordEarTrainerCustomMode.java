package com.jcortez.musiceartrainer.chordtrainer.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.jcortez.musiceartrainer.chordtrainer.test.mocks.DummyChordFileStore;
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
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristics;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristicsImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelector;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelectorImpl;

// Tests the methods called by the REST web services pertaining to the custom
// mode for the chord ear trainer.
public class TestChordEarTrainerCustomMode
{
    private ChordEarTrainerResource restResource;

    public TestChordEarTrainerCustomMode()
    {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(CustomMode.class).to(CustomModeImpl.class);
                bind(ChallengeMode.class).to(ChallengeModeImpl.class);
                bind(ChordFileStore.class).to(DummyChordFileStore.class);
                bind(ChordTrainerRandomNumberGenerator.class).to(MockRandomNumberGenerator.class);
                bind(UserSelectableChordCharacteristics.class).to(UserSelectableChordCharacteristicsImpl.class);
                bind(QuestionSelector.class).to(QuestionSelectorImpl.class);
            }
            @Provides
            @Named("randomValues")
            private ArrayList<Integer> getRandomValues()
            {
                ArrayList<Integer> mockRandomNextInts = new ArrayList<Integer>();
                // Random values returned for the testGetNextCustomModeQuestion()
                // test.
                mockRandomNextInts.add(2);
                mockRandomNextInts.add(7);
                mockRandomNextInts.add(0);
                return mockRandomNextInts;
            }
        });
        restResource = injector.getInstance(ChordEarTrainerResource.class);
    }

    @Test
    // Tests the getCustomModeChordCharacteristics() method in
    // ChordEarTrainerResource to check that the correct chord characteristics
    // that the user can select are returned.
    public void testGetCustomModeChordCharacteristics()
    {
        UserSelectableChordCharacteristics chordCharacteristics = restResource.getCustomModeChordCharacteristics();
        String[] qualities = chordCharacteristics.getTrainerChordQualities();
        String[] inversions = chordCharacteristics.getTrainerChordInversions();

        assertEquals(12, qualities.length);
        assertEquals(4, inversions.length);
        assertEquals("Major", qualities[0]);
        assertEquals("Minor", qualities[1]);
        assertEquals("Diminished", qualities[2]);
        assertEquals("Augmented", qualities[3]);
        assertEquals("Dominant Seventh", qualities[4]);
        assertEquals("Major Seventh", qualities[5]);
        assertEquals("Minor Seventh", qualities[6]);
        assertEquals("Minor Major Seventh", qualities[7]);
        assertEquals("Half Diminished Seventh", qualities[8]);
        assertEquals("Diminished Seventh", qualities[9]);
        assertEquals("Sus 2", qualities[10]);
        assertEquals("Sus 4", qualities[11]);
        assertEquals("Root Position", inversions[0]);
        assertEquals("First Inversion", inversions[1]);
        assertEquals("Second Inversion", inversions[2]);
        assertEquals("Third Inversion", inversions[3]);
    }

    @Test
    // Tests calling the getNextCustomModeQuestion() method when the chard
    // characteristics are null.
    public void testGetNextCustomModeQuestionNullCharacteristics()
        throws InvalidChordCharacteristicsException
    {
        Question question = restResource.getNextCustomModeQuestion(null);
        assertNull(question);
    }

    @Test
    // Tests calling the getNextCustomModeQuestion() method to return a standard
    // question to the user.
    public void testGetNextCustomModeQuestion()
        throws InvalidChordCharacteristicsException
    {
        ChordCharacteristicsToTest testCharacteristics = new ChordCharacteristicsToTest();
        testCharacteristics.setChordQualities(new String[] {"all"});
        testCharacteristics.setChordInversions(new String[] {"all"});
        Question question = restResource.getNextCustomModeQuestion(testCharacteristics);
        assertEquals("test.midi", question.getQuestionMidiFileName());
        // The answer will not be returned in the REST response but will be used
        // here to verify the correct chord for the question was chosen.
        Chord answer = question.getAnswer();
        assertEquals(ChordRoot.D, answer.getChordRoot());
        assertEquals(ChordQuality.MIN_MAJ_SEVENTH, answer.getChordQuality());
        assertEquals(ChordInversion.ROOT_POS, answer.getChordInversion());
    }

    @Test
    // Tests calling the submitCustomModeAnswer() method when the user's answer
    // is null.
    public void testSubmitCustomModeAnswerNullAnswer()
    {
        AnswerResponse response = restResource.submitCustomModeAnswer(null);
        assertNull(response);
    }

    @Test
    // Tests calling the submitCustomModeAnswer() method to submit a correct
    // answer.
    public void testSubmitCustomModeAnswerCorrectAnswer()
    {
        Answer answer = new Answer("test.midi",
                new Chord(ChordRoot.C, ChordQuality.MAJ, ChordInversion.ROOT_POS));
        AnswerResponse response = restResource.submitCustomModeAnswer(answer);
        assertTrue(response.getUserAnswerCorrect());
        assertEquals(ChordRoot.C, response.getCorrectAnswer().getChordRoot());
        assertEquals(ChordQuality.MAJ, response.getCorrectAnswer().getChordQuality());
        assertEquals(ChordInversion.ROOT_POS, response.getCorrectAnswer().getChordInversion());
    }

    @Test
    // Tests calling the submitCustomModeAnswer() method to submit an incorrect
    // answer.
    public void testSubmitCustomModeAnswerIncorrectAnswer()
    {
        Answer answer = new Answer("test.midi",
                new Chord(ChordRoot.D, ChordQuality.MIN_SEVENTH, ChordInversion.THIRD_INV));
        AnswerResponse response = restResource.submitCustomModeAnswer(answer);
        assertFalse(response.getUserAnswerCorrect());
        assertEquals(ChordRoot.C, response.getCorrectAnswer().getChordRoot());
        assertEquals(ChordQuality.MAJ, response.getCorrectAnswer().getChordQuality());
        assertEquals(ChordInversion.ROOT_POS, response.getCorrectAnswer().getChordInversion());
    }
}
