package org.jautoupdater.rss.model;

/**
* Holds information about an app RSS feed item. This
* contains all the needed variables that the RSS
* feed may contain.
*
* @author Jeffrey Walraven
*/
public class AppFeedItem {
    
    String title;
    String description;
    String publishDate;
    String releaseNotesLink;
    String author;
    String guid;
    AppFeedUpdate update;

    /**
     * Constructs an AppFeedItem.
     * @param title The title of the app feed item
     * @param description The description of the app feed item
     * @param publishDate The publish date of the app feed item
     * @param notesLink A link to the update notes
     * @param author The author of the app feed item
     * @param guid The GUID of the app feed item
     * @param update The update file information held in an {@link
     * AppFeedUpdate}
     */
    public AppFeedItem(String title, String description, String publishDate, String notesLink, String author, String guid, AppFeedUpdate update) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        this.releaseNotesLink = notesLink;
        this.update = update;
        this.author = author;
        this.guid = guid;
    }

    /**
     * Get the title of the AppFeedItem.
     * @return The String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the description of the AppFeedItem.
     * @return The String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the publish date of the AppFeedItem.
     * @return The String value of the publish date
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * Get the link to the release notes.
     * @return The String value of the URL link
     */
    public String getReleaseNotesLink() {
        return releaseNotesLink;
    }

    /**
     * Get the author of the RSS app feed.
     * @return The String value of the author's name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the GUID of the RSS app feed.
     * @return The String value of the GUID
     */
    public String getGUID() {
        return guid;
    }

     /**
     * Get the {@link AppFeedUpdate} item.
     * @return The {@link AppFeedUpdate} item that contains information
     * about the update file
     */
    public AppFeedUpdate getAppFeedUpdate() {
        return update;
    }

    @Override
    public String toString() {
        String returnString = "AppFeedItem (";
        returnString += title + ", ";
        returnString += description + ", ";
        returnString += publishDate + ", ";
        returnString += releaseNotesLink + ", ";
        returnString += author + ", ";
        returnString += guid + ")";
        return returnString;
    }
}
