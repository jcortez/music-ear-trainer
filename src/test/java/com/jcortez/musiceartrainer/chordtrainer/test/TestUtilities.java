package com.jcortez.musiceartrainer.chordtrainer.test;

// Utility methods used for unit testing.
public class TestUtilities
{
    // Returns true if the String is stored in the String array. Used for testing
    // the mapping of enum values to full names because there is no guaranteed
    // order for getting arrays of the full names.
    public static boolean stringArrayContains(String[] array, String stringToLookFor)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].equals(stringToLookFor))
            {
                return true;
            }
        }

        return false;
    }
}
