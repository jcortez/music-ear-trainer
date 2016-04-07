package com.jcortez.musiceartrainer.rest.chordtrainer.model;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;
import com.google.inject.Singleton;

// Reads the chord/file mappings from an XML file. See ChordFileStore for more
// details.
@Singleton
public class XMLChordFileStore implements ChordFileStore
{
    // Annotation for the XML file.
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @BindingAnnotation
    public @interface QuestionXMLFile {}

    // Questions XML document.
    private Document questionsData = null;

    @Inject
    public XMLChordFileStore(@QuestionXMLFile String fileName)
    {
        try
        {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            questionsData = docBuilder.parse(new File(fileName));
        }
        catch (Exception e)
        {
            // TODO: Log this
            System.out.println(e.toString());
        }
    }

    @Override
    public String getChordMidiFileName(Chord chord)
    {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "/questions/question[answer/chordRoot/text()='" +
                chord.getChordRootString() + "' and answer/chordQuality/text()='" +
                chord.getChordQualityString() + "' and answer/chordInversion/text()='" +
                chord.getChordInversionString() + "']/questionMidiFileName";
        try
        {
            String fileName = (String) xpath.evaluate(expression, questionsData, XPathConstants.STRING);
            return fileName;
        }
        catch (XPathExpressionException e)
        {
            // TODO: Log this
            System.out.println(e.toString());
        }

        return "";
    }

    @Override
    public Chord getChordForMidiFile(String midiFileName)
    {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String fileNameExpression = "/questions/question[questionMidiFileName/text()='" +
                midiFileName +"']";
        try
        {
            String chordRoot = (String) xpath.evaluate(fileNameExpression +
                    "/answer/chordRoot", questionsData, XPathConstants.STRING);
            String chordQuality = (String) xpath.evaluate(fileNameExpression +
                    "/answer/chordQuality", questionsData, XPathConstants.STRING);
            String chordInversion = (String) xpath.evaluate(fileNameExpression +
                    "/answer/chordInversion", questionsData, XPathConstants.STRING);
            return new Chord(ChordRoot.getChordRootEnum(chordRoot),
                    ChordQuality.getChordQualityEnum(chordQuality),
                    ChordInversion.getChordInversionEnum(chordInversion));
        }
        catch (XPathExpressionException e)
        {
            // TODO: Log this
            System.out.println(e.toString());
        }

        return null;
    }   
}
