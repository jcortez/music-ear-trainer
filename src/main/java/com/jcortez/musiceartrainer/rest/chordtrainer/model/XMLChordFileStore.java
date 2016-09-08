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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;
import com.google.inject.Singleton;

// Reads chord and MIDI information from an XML file. See ChordFileStore for more
// details.
@Singleton
public class XMLChordFileStore implements ChordFileStore
{
    // Annotation for the XML file.
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @BindingAnnotation
    public @interface QuestionXMLFile {}

    private static final Logger logger = LogManager.getLogger(XMLChordFileStore.class);
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
            logger.error(e, e);
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
            logger.error(e, e);
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
            logger.error(e, e);
        }

        return null;
    }

    @Override
    public int[] getMIDINotes(Chord chord)
    {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "/questions/question[answer/chordRoot/text()='" +
                chord.getChordRootString() + "' and answer/chordQuality/text()='" +
                chord.getChordQualityString() + "' and answer/chordInversion/text()='" +
                chord.getChordInversionString() + "']/midiNotes/*";
        try
        {
            NodeList nodeList = (NodeList) xpath.evaluate(expression, questionsData, XPathConstants.NODESET);
            int[] midiNotes = new int[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                midiNotes[i] = Integer.parseInt(nodeList.item(i).getTextContent());
            }

            return midiNotes;
        }
        catch (Exception e)
        {
            logger.error(e, e);
        }

        return new int[0];
    }   
}
