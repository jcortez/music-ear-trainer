package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

// A class that stores the chord characteristics that will be tested in questions
// that the chord trainer will present to the user. This will be a subset (or the
// complete set) of all possible chord characteristics. It is assumed that all
// chord roots can be selected for testing, so they are not stored here.
public class ChordCharacteristicsToTest
{
    private HashSet<ChordQuality> chordQualitiesEnums = null;
    private HashSet<ChordInversion> chordInversionsEnums = null;

    public ChordCharacteristicsToTest()
    {
        chordQualitiesEnums = new HashSet<ChordQuality>();
        chordInversionsEnums = new HashSet<ChordInversion>();
    }

    @DefaultValue("all")
    @QueryParam("chordQualities")
    // Sets the chord qualities to test from the specified array. This method
    // will only have an effect the first time that it is called. If a chord
    // quality name does not correspond to a ChordQuality enum value, it will
    // be ignored. If a chord quality is added more than once, it will be ignored
    // after the first time it is inserted. If the array contains only the "all"
    // String, then all chord qualities are selected.
    public void setChordQualities(String[] chordQualities)
    {
        // Checking to see if this method has already been called.
        if (!chordQualitiesEnums.isEmpty())
        {
            return;
        }

        if (chordQualities.length == 1 && chordQualities[0].equals("all"))
        {
            List<ChordQuality> allEnums = Arrays.asList(ChordQuality.class.getEnumConstants());
            chordQualitiesEnums = new HashSet<ChordQuality>(allEnums);
            return;
        }

        for (int i = 0; i < chordQualities.length; i++)
        {
            ChordQuality chordQuality = ChordQuality.getChordQualityEnum(chordQualities[i]);

            // Checking if the enum value is valid.
            if (chordQuality != null)
            {
                chordQualitiesEnums.add(chordQuality);
            }
        }
    }

    @DefaultValue("all")
    @QueryParam("chordInversions")
    // Sets the chord inversions to test from the specified array. This method
    // will only have an effect the first time that it is called. If a chord
    // inversion name does not correspond to a ChordInversion enum value, it
    // will be ignored. If a chord inversion is added more than once, it will
    // be ignored after the first time it is inserted. If the array contains
    // only the "all" String, then all chord inversions are selected.
    public void setChordInversions(String[] chordInversions)
    {
        // Checking to see if this method has already been called.
        if (!chordInversionsEnums.isEmpty())
        {
            return;
        }

        if (chordInversions.length == 1 && chordInversions[0].equals("all"))
        {
            List<ChordInversion> allEnums = Arrays.asList(ChordInversion.class.getEnumConstants());
            chordInversionsEnums = new HashSet<ChordInversion>(allEnums);
            return;
        }

        for (int i = 0; i < chordInversions.length; i++)
        {
            ChordInversion chordInversion = ChordInversion.getChordInversionEnum(chordInversions[i]);

            // Checking if the enum value is valid.
            if (chordInversion != null)
            {
                chordInversionsEnums.add(chordInversion);
            }
        }
    }
    
    // Gets all of the chord qualities that the chord trainer can test.
    public HashSet<ChordQuality> getChordQualities()
    {
        return chordQualitiesEnums;
    }

    // Gets all of the chord inversions that the chord trainer can test.
    public HashSet<ChordInversion> getChordInversions()
    {
        return chordInversionsEnums;
    }
}
