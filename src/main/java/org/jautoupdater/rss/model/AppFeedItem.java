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
    AppFeedUpdate update;

    /**
     * Constructs an AppFeedItem.
     * @param title The title of the app feed item
     * @param description The description of the app feed item
     * @param publishDate The publish date of the app feed item
     * @param notesLink A link to the update notes
     * @param update The update file information held in an {@link
     * AppFeedUpdate}
     */
    public AppFeedItem(String title, String description, String publishDate, String notesLink, AppFeedUpdate update) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        this.releaseNotesLink = notesLink;
        this.update = update;
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
     * Get the {@link AppFeedUpdate} item.
     * @return The {@link AppFeedUpdate} item that contains information
     * about the update file
     */
    public AppFeedUpdate getAppFeedUpdate() {
        return update;
    }
}
