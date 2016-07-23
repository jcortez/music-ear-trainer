package com.jcortez.musiceartrainer.chordtrainer.test;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.jcortez.musiceartrainer.chordtrainer.test.mocks.DummyChordFileStore;
import com.jcortez.musiceartrainer.chordtrainer.test.mocks.MockRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Chord;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.InvalidChordCharacteristicsException;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelectorImpl;
import static org.junit.Assert.*;
import java.util.ArrayList;

// Tests the QuestionSelectorImpl class.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestQuestionSelectorImpl
{
    private static QuestionSelectorImpl questionSelector = null;

    @BeforeClass
    public static void setup()
    {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ChordFileStore.class).to(DummyChordFileStore.class);
                bind(ChordTrainerRandomNumberGenerator.class).to(MockRandomNumberGenerator.class);
            }
            @Provides
            @Named("randomValues")
            private ArrayList<Integer> getRandomValues()
            {
                ArrayList<Integer> mockRandomNextInts = new ArrayList<Integer>();
                // Random values returned for the testSelectNextQuestionAllCharacteristics()
                // test.
                mockRandomNextInts.add(5);
                mockRandomNextInts.add(11);
                mockRandomNextInts.add(1);
                // Random values returned for the testSelectNextQuestionInvalidCharacteristics()
                // test.
                mockRandomNextInts.add(0);
                mockRandomNextInts.add(0);
                mockRandomNextInts.add(0);
                for (int i = 1; i <= 24; i++)
                {
                    // Adding the values to cycle through the 6 qualities.
                    mockRandomNextInts.add(i%6);
                }
                // Random values returned for the testSelectNextQuestionOneChordCharacteristic()
                // test.
                mockRandomNextInts.add(0);
                mockRandomNextInts.add(0);
                mockRandomNextInts.add(0);
                // Random values returned for the
                // testSelectNextQuestionPotentiallyInvalidCharacteristics() test.
                mockRandomNextInts.add(7);
                mockRandomNextInts.add(0);
                mockRandomNextInts.add(1);
                mockRandomNextInts.add(1);
                mockRandomNextInts.add(2);

                return mockRandomNextInts;
            }
        });
        questionSelector = injector.getInstance(QuestionSelectorImpl.class);
    }

    // Tests selectNextQuestion() with only one chord quality and chord inversion
    // to select from.
    @Test
    public void testSelectNextQuestionOneChordCharacteristic()
        throws InvalidChordCharacteristicsException
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
        throws InvalidChordCharacteristicsException
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

    // Tests selectNextQuestion() with selecting from invalid chord characteristics
    // (third inversion for chords that do not have this inversion).
    @Test(expected=InvalidChordCharacteristicsException.class)
    public void testSelectNextQuestionInvalidCharacteristics()
        throws InvalidChordCharacteristicsException
    {
        ChordCharacteristicsToTest testCharacteristics = new ChordCharacteristicsToTest();
        testCharacteristics.setChordQualities(new String[] { "Major", "Minor",
            "Diminished", "Augmented", "Sus 2", "Sus 4" });
        testCharacteristics.setChordInversions(new String[] {"Third Inversion"});
        questionSelector.selectNextQuestion(testCharacteristics);
    }

    // Tests selectNextQuestion() with selecting from chord characteristics
    // that could potentially be invalid.
    @Test
    public void testSelectNextQuestionPotentiallyInvalidCharacteristics()
        throws InvalidChordCharacteristicsException
    {
        ChordCharacteristicsToTest testCharacteristics = new ChordCharacteristicsToTest();
        testCharacteristics.setChordQualities(new String[] { "Major", "Sus 4",
            "Minor Seventh" });
        testCharacteristics.setChordInversions(new String[] { "Root Position",
            "Third Inversion" });
        Question question = questionSelector.selectNextQuestion(testCharacteristics);
        Chord answer = question.getAnswer();
        assertEquals(ChordRoot.G, answer.getChordRoot());
        assertEquals(ChordQuality.MIN_SEVENTH, answer.getChordQuality());
        assertEquals(ChordInversion.THIRD_INV, answer.getChordInversion());
    }
}
