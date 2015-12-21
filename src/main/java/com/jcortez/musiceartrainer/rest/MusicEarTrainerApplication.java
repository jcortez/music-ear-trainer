package com.jcortez.musiceartrainer.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class MusicEarTrainerApplication extends Application
{
    // REST web service objects that need to be initialized per-request.
    public Set<Class<?>> getClasses()
    {
        return new HashSet<Class<?>>();
    }

    // REST web service objects that are initialized once.
    public Set<Object> getSingletons()
    {
        HashSet<Object> webServices = new HashSet<Object>();
        webServices.add(new ChordEarTrainerResource());
        return webServices;
    }
}
