package org.jautoupdater.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.jautoupdater.rss.model.AppFeed;
import org.jautoupdater.rss.model.AppFeedItem;
import org.jautoupdater.rss.model.AppFeedUpdate;

/**
* Reads the app RSS feed and puts the data into model class.
* This puts the information into {@link AppFeed}, {@link AppFeedItem}, 
* and {@link AppFeedUpdate}.
*
* @author Jeffrey Walraven
*/
public class AppFeedReader {
    
    // Declare xml tags
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    static final String NOTES_LINK = "releaseNotesLink";
    static final String UPDATE_ITEM = "update";

    final URL url;

    /**
     * Constructs a new AppFeedReader.
     * @param feedURL The URL of the app feed
     */
    public AppFeedReader(String feedURL) {
        try {
            this.url = new URL(feedURL);
        } catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read the AppFeed from the URL.
     */
    public AppFeed readAppFeed() {
        AppFeed feed = null;
        try {
           boolean isFirstRead = true;
            
           String title = "";
           String description = "";
           String language = "";
           String copyright = "";
           String link = "";
           String author = "";
           String pubdate = "";
           String guid = "";
           String notes_link = "";
           AppFeedUpdate update = null;

           // Create a new XMLInputFactory
           XMLInputFactory inputFactory = XMLInputFactory.newInstance();

           // Setup a new input stream
           InputStream in;
           try {
               in = url.openStream();
           } catch(IOException e) {
               throw new RuntimeException(e);
           }

           // Setup a new event reader
           XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

           // Read the XML RSS feed through a while loop
           while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                // If this event is the start element
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case ITEM:
                            if (isFirstRead) {
                                isFirstRead = false;
                                feed = new AppFeed(title, link, description, language, copyright, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case NOTES_LINK:
                            notes_link = getCharacterData(event, eventReader);
                            break;
                        case UPDATE_ITEM:
                            String downloadLink = "";
                            String version = "";
                            String signature = "";
                            String tagPart = "";

                            while (tagPart != UPDATE_ITEM) {
                                event = eventReader.nextEvent();
                                if (event.isEndElement()) {
                                    tagPart = event.asEndElement().getName().getLocalPart();
                                }

                                if (event.isStartElement()) {
                                    String updatePart = event.asStartElement().getName().getLocalPart();
                                    switch (updatePart) {
                                        case "downloadLink":
                                            downloadLink = getCharacterData(event, eventReader);
                                            break;
                                        case "version":
                                            version = getCharacterData(event, eventReader);
                                            break;
                                        case "signature":
                                            signature = getCharacterData(event, eventReader);
                                            break;
                                    }
                                }
                            }
                            // Create the AppFeedUpdate
                            update = new AppFeedUpdate(downloadLink, version, signature);
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        AppFeedItem feedItem = new AppFeedItem(title, description, pubdate, notes_link, author, guid, update);
                        feed.items.add(feedItem);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
           }
        } catch(XMLStreamException  e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) 
        throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}
