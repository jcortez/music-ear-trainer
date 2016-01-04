package com.jcortez.musiceartrainer.rest.chordtrainer.questions;

import java.util.ArrayList;
import java.util.Set;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Chord;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordInversion;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordQuality;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordRoot;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;

// See QuestionSelector interface for more details.
@Singleton
public class QuestionSelectorImpl implements QuestionSelector
{
    private ChordFileStore chordFileStore = null;
    private ChordTrainerRandomNumberGenerator randomNumberGenerator = null;

    @Inject
    public QuestionSelectorImpl(ChordFileStore fileStore, ChordTrainerRandomNumberGenerator numberGen)
    {
        chordFileStore = fileStore;
        randomNumberGenerator = numberGen;
    }

    @Override
    public Question selectNextQuestion(ChordCharacteristicsToTest chordCharacteristics)
    {
        ChordRoot root = selectRandomChordRoot();
        ChordQuality quality = selectRandomChordQuality(chordCharacteristics.getChordQualities());
        ChordInversion inversion = selectRandomChordInversion(chordCharacteristics.getChordInversions());
        Chord chordToTest = new Chord(root, quality, inversion);
        String chordToTestMidiFileName = chordFileStore.getChordMidiFileName(chordToTest);
        Question question = new Question(chordToTestMidiFileName, chordToTest);
        return question;
    }

    // Selects a random chord root to test. All possible chord roots have the
    // same probability of being chosen.
    private ChordRoot selectRandomChordRoot()
    {
        ChordRoot[] allChordRoots = ChordRoot.values();
        int randomIndex = randomNumberGenerator.nextInt(allChordRoots.length);
        return allChordRoots[randomIndex];
    }

    // Selects a random chord quality to test from the specified set of chord
    // qualities.
    private ChordQuality selectRandomChordQuality(Set<ChordQuality> chordQualities)
    {
        ArrayList<ChordQuality> qualitiesToSelectFrom = new ArrayList<ChordQuality>(chordQualities);
        int randomIndex = randomNumberGenerator.nextInt(qualitiesToSelectFrom.size());
        return qualitiesToSelectFrom.get(randomIndex);
    }

    // Selects a random chord inversion to test from the specified set of chord
    // inversions.
    private ChordInversion selectRandomChordInversion(Set<ChordInversion> chordInversions)
    {
        ArrayList<ChordInversion> inversionsToSelectFrom = new ArrayList<ChordInversion>(chordInversions);
        int randomIndex = randomNumberGenerator.nextInt(inversionsToSelectFrom.size());
        return inversionsToSelectFrom.get(randomIndex);
    }
}
