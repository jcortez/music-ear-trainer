package com.jcortez.musiceartrainer.rest.chordtrainer.questions;

import java.util.concurrent.ThreadLocalRandom;
import com.google.inject.Singleton;

// See ChordTrainerRandomNumberGenerator for more details.
@Singleton
public class ChordTrainerRandomNumberGeneratorImpl implements ChordTrainerRandomNumberGenerator
{

    @Override
    public int nextInt(int end)
    {
        return ThreadLocalRandom.current().nextInt(end);
    }

}
