package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jcortez.musiceartrainer.rest.chordtrainer.questions.QuestionSelector;

// A class that represents the Custom Mode of the chord ear trainer.
@Singleton
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomModeImpl implements CustomMode
{
    private static final String CUSTOM_MODE_NAME = "Custom Mode";
    private QuestionSelector questionSelector = null;
    private ChordFileStore chordFileStore = null;

    @Inject
    public CustomModeImpl(QuestionSelector questionSelector, ChordFileStore chordFileStore)
    {
        this.questionSelector = questionSelector;
        this.chordFileStore = chordFileStore;
    }

    @Override
    @XmlElement
    public String getName()
    {
        return CUSTOM_MODE_NAME;
    }

    @Override
    public AnswerResponse checkAnswer(Answer userAnswer)
    {
        if (userAnswer == null)
        {
            return new AnswerResponse();
        }
        // The user answer is checked by getting the Chord object corresponding
        // to the MIDI file and comparing it with the user's answer. If they
        // are the same, then the user answered correctly.
        Chord correctAnswer = chordFileStore.getChordForMidiFile(userAnswer.getQuestionMidiFileName());
        boolean userAnsweredCorrectly = userAnswer.getAnswer().equals(correctAnswer);
        return new AnswerResponse(userAnsweredCorrectly, correctAnswer);
    }

    @Override
    public Question getNextQuestion(ChordCharacteristicsToTest chordCharacteristics)
    {
        if (chordCharacteristics == null)
        {
            return null;
        }

        return questionSelector.selectNextQuestion(chordCharacteristics);
    }

}
