package com.jcortez.musiceartrainer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// REST web services for the chord ear trainer.
@Path("/chord-ear-trainer")
public class ChordEarTrainerResource
{
    @GET
    @Path("/modes")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns the chord ear training modes supported by the ear trainer.
    public String getEarTrainerModes()
    {
        //TODO: Implement getEarTrainerModes(). Use HATEOAS for links to modes?
        return "Test modes";
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
    // Returns the list of chord characteristics that can be tested in Custom Mode.
    public String getCustomModeChordCharacteristics()
    {
        //TODO: Implement getCustomModeChordCharacteristics().
        return "Test chord characteristics";
    }

    @GET
    @Path("/modes/custom/question")
    @Produces(MediaType.APPLICATION_JSON)
    // Returns the next question for Custom Mode with the chord characteristics that
    // were specified by the user.
    public String getNextCustomModeQuestion()
    {
        //TODO: Implement getNextCustomModeQuestion().
        return "Next test custom mode question";
    }

    @POST
    @Path("/modes/custom/answer")
    @Produces(MediaType.APPLICATION_JSON)
    // Submits the user's answer for Custom Mode with the chord characteristics that
    // were specified by the user.
    public String submitCustomModeAnswer()
    {
        //TODO: Implement submitCustomModeAnswer().
        return "Answer for custom mode is correct.";
    }

}
