package com.jcortez.musiceartrainer.rest.chordtrainer.model;

// This exception will be thrown when chord characteristics are selected by the
// user that cannot be tested on.
public class InvalidChordCharacteristicsException extends Exception
{
    private static final long serialVersionUID = 9182696525782398037L;

    public InvalidChordCharacteristicsException(String message)
    {
        super(message);
    }
}
