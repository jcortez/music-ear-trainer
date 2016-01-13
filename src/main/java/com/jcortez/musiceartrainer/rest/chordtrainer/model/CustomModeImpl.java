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

    @Inject
    public CustomModeImpl(QuestionSelector questionSelector)
    {
        this.questionSelector = questionSelector;
    }

    @Override
    @XmlElement
    public String getName()
    {
        return CUSTOM_MODE_NAME;
    }

    @Override
    public boolean checkAnswer(Chord userAnswer, Chord correctAnswer)
    {
        // TODO Auto-generated method stub
        return false;
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
