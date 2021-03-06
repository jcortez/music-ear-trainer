package com.jcortez.musiceartrainer.rest;

import com.google.inject.AbstractModule;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeModeImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomModeImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.InvalidChordCharacteristicsMapper;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristics;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristicsImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.XMLChordFileStore;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.XMLChordFileStore.QuestionXMLFile;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGenerator;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGeneratorImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelector;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelectorImpl;

// A class that configures the Guice bindings that are used by the chord ear
// trainer.
public class ChordEarTrainerServletModule extends AbstractModule
{
    private static final String questionsXMLFile = "question_data.xml";

    @Override
    protected void configure()
    {
        bind(ChordEarTrainerResource.class);
        bind(InvalidChordCharacteristicsMapper.class);
        bind(CustomMode.class).to(CustomModeImpl.class);
        bind(ChallengeMode.class).to(ChallengeModeImpl.class);
        bind(ChordFileStore.class).to(XMLChordFileStore.class);
        bind(ChordTrainerRandomNumberGenerator.class).to(ChordTrainerRandomNumberGeneratorImpl.class);
        bind(UserSelectableChordCharacteristics.class).to(UserSelectableChordCharacteristicsImpl.class);
        bind(QuestionSelector.class).to(QuestionSelectorImpl.class);
        bindConstant().annotatedWith(QuestionXMLFile.class).to(questionsXMLFile);
    }
}
