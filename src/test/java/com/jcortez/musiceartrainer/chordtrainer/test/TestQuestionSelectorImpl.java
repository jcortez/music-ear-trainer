package com.jcortez.musiceartrainer.chordtrainer.test;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Chord;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.DummyChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelectorImpl;
import static org.junit.Assert.*;

// Tests the QuestionSelectorImpl class.
public class TestQuestionSelectorImpl
{
    private static QuestionSelectorImpl questionSelector = null;

    // Mock ChordTrainerRandomNumberGenerator object for the tests in this class.
    @Singleton
    private static class MockRandomNumberGenerator implements ChordTrainerRandomNumberGenerator
    {
        private int currentIndex = 0;
        private ArrayList<Integer> mockRandomNextInts = null;

        public MockRandomNumberGenerator()
        {
            mockRandomNextInts = new ArrayList<Integer>();
            // Random values returned for the testSelectNextQuestionOneChordCharacteristic()
            // test.
            mockRandomNextInts.add(0);
            mockRandomNextInts.add(0);
            mockRandomNextInts.add(0);
            // Random values returned for the testSelectNextQuestionAllCharacteristics()
            // test.
            mockRandomNextInts.add(5);
            mockRandomNextInts.add(11);
            mockRandomNextInts.add(1);
        }

        @Override
        public int nextInt(int end)
        {
            int randomNextInt = mockRandomNextInts.get(currentIndex);
            currentIndex++;
            return randomNextInt;
        }
        
    }

    @BeforeClass
    public static void setup()
    {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ChordFileStore.class).to(DummyChordFileStore.class);
                bind(ChordTrainerRandomNumberGenerator.class).to(MockRandomNumberGenerator.class);
            }
        });
        questionSelector = injector.getInstance(QuestionSelectorImpl.class);
    }

    // Tests selectNextQuestion() with only one chord quality and chord inversion
    // to select from.
    @Test
    public void testSelectNextQuestionOneChordCharacteristic()
    {
        ChordCharacteristicsToTest testCharacteristics = new ChordCharacteristicsToTest();
        testCharacteristics.setChordQualities(new String[] {"Major"});
        testCharacteristics.setChordInversions(new String[] {"Root Position"});
        Question question = questionSelector.selectNextQuestion(testCharacteristics);
        Chord answer = question.getAnswer();
        assertEquals(ChordRoot.C, answer.getChordRoot());
        assertEquals(ChordQuality.MAJ, answer.getChordQuality());
        assertEquals(ChordInversion.ROOT_POS, answer.getChordInversion());
    }

    // Tests selectNextQuestion() with selecting from all possible chord qualities
    // and chord inversions.
    @Test
    public void testSelectNextQuestionAllCharacteristics()
    {
        ChordCharacteristicsToTest testCharacteristics = new ChordCharacteristicsToTest();
        testCharacteristics.setChordQualities(new String[] {"all"});
        testCharacteristics.setChordInversions(new String[] {"all"});
        Question question = questionSelector.selectNextQuestion(testCharacteristics);
        Chord answer = question.getAnswer();
        assertEquals(ChordRoot.F, answer.getChordRoot());
        assertEquals(ChordQuality.SUS_4, answer.getChordQuality());
        assertEquals(ChordInversion.FIRST_INV, answer.getChordInversion());
    }
}
