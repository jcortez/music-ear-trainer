package com.jcortez.musiceartrainer.rest.chordtrainer.model;


// Interface that contains methods to obtain chord characteristics that the user
// can select to be tested in the chord ear trainer in Custom Mode. See
// UserSelectableChordCharacteristicsImpl for more details.
public interface UserSelectableChordCharacteristics
{
    public String[] getTrainerChordQualities();
    public String[] getTrainerChordInversions();
}
