package com.jcortez.musiceartrainer.chordtrainer.test.mocks;

import java.util.ArrayList;
import java.util.Iterator;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.ChordTrainerRandomNumberGenerator;

// Mock ChordTrainerRandomNumberGenerator object that returns preset values for
// nextInt() from the specified ArrayList that is injected.
@Singleton
public class MockRandomNumberGenerator implements ChordTrainerRandomNumberGenerator
{
    private Iterator<Integer> nextIntsIterator = null;

    @Inject
    public MockRandomNumberGenerator(@Named("randomValues") ArrayList<Integer> randomValues)
    {
        nextIntsIterator = randomValues.iterator();
    }

    @Override
    // Returns the next integer from the injected ArrayList. If there are no more
    // values to return, -1 is returned.
    public int nextInt(int end)
    {
        if (nextIntsIterator.hasNext())
        {
            return nextIntsIterator.next();
        }
        else
        {
            return -1;
        }
    }
    
}