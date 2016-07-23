package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidChordCharacteristicsMapper implements ExceptionMapper<InvalidChordCharacteristicsException>
{
    @Override
    public Response toResponse(InvalidChordCharacteristicsException arg0)
    {
        return Response.status(Response.Status.BAD_REQUEST).entity(arg0.getMessage())
            .type(MediaType.TEXT_PLAIN).build();
    }

}
