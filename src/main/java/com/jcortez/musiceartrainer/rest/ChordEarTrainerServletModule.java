package com.jcortez.musiceartrainer.rest;

import com.google.inject.AbstractModule;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeModeImpl;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomModeImpl;

// A class that configures the Guice bindings that are used by the chord ear
// trainer.
public class ChordEarTrainerServletModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ChordEarTrainerResource.class);
        bind(CustomMode.class).to(CustomModeImpl.class);
        bind(ChallengeMode.class).to(ChallengeModeImpl.class);
    }
}
