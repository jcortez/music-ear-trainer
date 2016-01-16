package com.jcortez.musiceartrainer.rest;

import java.util.ArrayList;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.inject.Inject;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Answer;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.AnswerResponse;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChallengeMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.ChordCharacteristicsToTest;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.CustomMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.Question;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.TrainerMode;
import com.jcortez.musiceartrainer.rest.chordtrainer.model.UserSelectableChordCharacteristics;

// REST web services for the chord ear trainer.
@Path("/chord-ear-trainer")
public class ChordEarTrainerResource
{
    private final ChallengeMode challengeMode;
    private final CustomMode customMode;
    private final UserSelectableChordCharacteristics userSelectableChordCharacteristics;

    @Inject
    public ChordEarTrainerResource(final ChallengeMode challengeMode, final CustomMode customMode,
            final UserSelectableChordCharacteristics userSelectableChordCharacteristics)
    {
        this.challengeMode = challengeMode;
        this.customMode = customMode;
        this.userSelectableChordCharacteristics = userSelectableChordCharacteristics;
    }

    @GET
    @Path("/modes")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns the chord ear training modes supported by the ear trainer.
    public ArrayList<TrainerMode> getEarTrainerModes()
    {
        ArrayList<TrainerMode> trainerModes = new ArrayList<TrainerMode>();
        trainerModes.add(challengeMode);
        trainerModes.add(customMode);
        return trainerModes;
    }

    @GET
    @Path("/modes/challenges")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns the list of challenges available in Challenge Mode.
    public String getChallenges()
    {
        //TODO: Implement getChallenges().
        return "Test challenges";
    }

    @GET
    @Path("/modes/challenges/{challengeId}/question")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns the next question for the challenge specified by challengeId.
    public String getNextChallengeModeQuestion(@PathParam("challengeId") int challengeId)
    {
        //TODO: Implement getNextChallengeModeQuestion().
        return "Next test challenge question";
    }

    @POST
    @Path("/modes/challenges/{challengeId}/answer")
    @Produces(MediaType.APPLICATION_JSON)
    // Submits the user's answer for the specified challengeId.
    public String submitChallengeModeAnswer(@PathParam("challengeId") int challengeId)
    {
        //TODO: Implement submitChallengeModeAnswer().
        return "Answer for test challenge " + challengeId + " is correct.";
    }

    @GET
    @Path("/modes/custom")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns lists of chord characteristics that the user can select to be
    // tested in Custom Mode.
    public UserSelectableChordCharacteristics getCustomModeChordCharacteristics()
    {
        return userSelectableChordCharacteristics;
    }

    @GET
    @Path("/modes/custom/question")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns the next question for Custom Mode with the chord characteristics that
    // were specified by the user. The chord characteristics are sent through the
    // appropriate query parameters. If no query parameters are specified, all
    // possible chord characteristics can be tested.
    public Question getNextCustomModeQuestion(@BeanParam ChordCharacteristicsToTest chordCharacteristicsToTest)
    {
        if (chordCharacteristicsToTest == null)
        {
            return null;
        }
        else
        {
            return customMode.getNextQuestion(chordCharacteristicsToTest);
        }
    }

    @POST
    @Path("/modes/custom/answer")
    @Produces(MediaType.APPLICATION_JSON)
    // Submits the user's answer for Custom Mode with the chord characteristics that
    // were specified by the user.
    public AnswerResponse submitCustomModeAnswer(Answer userAnswer)
    {
        if (userAnswer == null)
        {
            return null;
        }
        else
        {
            return customMode.checkAnswer(userAnswer);
        }
    }

}
