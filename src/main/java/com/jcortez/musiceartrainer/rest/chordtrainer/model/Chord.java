package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// A data structure corresponding to a chord in the chord ear trainer. A Chord
// object will contain all chord characteristics.
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Chord
{
    private ChordRoot chordRoot = null;
    private ChordQuality chordQuality = null;
    private ChordInversion chordInversion = null;

    public Chord() { }

    public Chord(ChordRoot root, ChordQuality quality, ChordInversion inversion)
    {
        chordRoot = root;
        chordQuality = quality;
        chordInversion = inversion;
    }

    // Returns the ChordRoot enum value that corresponds to the root of this Chord
    // object.
    @XmlTransient
    public ChordRoot getChordRoot()
    {
        return chordRoot;
    }

    // Returns the ChordQuality enum value that corresponds to the chord quality of
    // this chord object.
    @XmlTransient
    public ChordQuality getChordQuality()
    {
        return chordQuality;
    }

    // Returns the ChordInversion enum value that corresponds to the chord inversion
    // of this chord. If the value is ROOT_POS, then this chord is not inverted.
    @XmlTransient
    public ChordInversion getChordInversion()
    {
        return chordInversion;
    }

    // Returns the chord root as its full name.
    @XmlElement(name="chordRoot")
    public String getChordRootString()
    {
        if (chordRoot == null)
        {
            return "";
        }

        return ChordRoot.getFullChordRootName(chordRoot);
    }

    // Returns the chord quality as its full name.
    @XmlElement(name="chordQuality")
    public String getChordQualityString()
    {
        if (chordQuality == null)
        {
            return "";
        }

        return ChordQuality.getFullChordQualityName(chordQuality);
    }

    // Returns the chord inversion as its full name.
    @XmlElement(name="chordInversion")
    public String getChordInversionString()
    {
        if (chordInversion == null)
        {
            return "";
        }

        return ChordInversion.getFullChordInversionName(chordInversion);
    }

    // Sets the chord root using the full chord root name.
    @XmlElement
    @QueryParam("chordRoot")
    public void setChordRoot(String fullChordRootName)
    {
        if (fullChordRootName != null)
        {
            chordRoot = ChordRoot.getChordRootEnum(fullChordRootName);
        }
    }

    // Sets the chord quality using the full chord quality name.
    @XmlElement
    @QueryParam("chordQuality")
    public void setChordQuality(String fullChordQualityName)
    {
        if (fullChordQualityName != null)
        {
            chordQuality = ChordQuality.getChordQualityEnum(fullChordQualityName);
        }
    }

    // Sets the chord inversion using the full chord inversion name.
    @XmlElement
    @QueryParam("chordInversion")
    public void setChordInversion(String fullChordInversionName)
    {
        if (fullChordInversionName != null)
        {
            chordInversion = ChordInversion.getChordInversionEnum(fullChordInversionName);
        }
    }

    @Override
    // Generated by Eclipse.
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chordInversion == null) ? 0 : chordInversion.hashCode());
        result = prime * result + ((chordQuality == null) ? 0 : chordQuality.hashCode());
        result = prime * result + ((chordRoot == null) ? 0 : chordRoot.hashCode());
        return result;
    }

    @Override
    // Generated by Eclipse.
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chord other = (Chord) obj;
        if (chordInversion != other.chordInversion)
            return false;
        if (chordQuality != other.chordQuality)
            return false;
        if (chordRoot != other.chordRoot)
            return false;
        return true;
    }

}
