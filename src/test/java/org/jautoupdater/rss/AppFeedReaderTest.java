package org.jautoupdater.rss;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.jautoupdater.rss.model.AppFeed;
import org.jautoupdater.rss.model.AppFeedUpdate;

/**
* Tests for {@link org.jautoupdater.rss.AppFeedReader}.
*
* @author JeffreyWalraven
*/
@RunWith(JUnit4.class)
public class AppFeedReaderTest {

    @Test
    public void readAppFeedTest() {
        AppFeedReader feedReader;
        feedReader = new AppFeedReader("http://jtwalraven.github.io/jAutoUpdater/files/example-feed.xml");
        AppFeed feed = feedReader.readAppFeed();

        // Check if the AppFeed output matches
        String expectedFeed;
        expectedFeed = "AppFeed (FeedTitle, FeedLink, FeedDescription, en, None, Tue, 25 Feb 2014 19:20:11 +0000)";
        assertEquals("Failure - feed data does not match", expectedFeed, feed.toString());
        System.out.println(feed.toString());

        // Check if the AppFeedItem output matches
        String expectedFeedItem;
        expectedFeedItem = "AppFeedItem (UpdateTitle, UpdateDescription, Tue, 25 Feb 2014 19:20:11"
            + " +0000, UpdateReleaseNotesLink, Jeffrey Walraven, update1)";
        assertEquals("Failure - feed item data does not match", expectedFeedItem, feed.items.get(0).toString());
        System.out.println(feed.items.get(0).toString());

        // Check if the AppFeedUpdate output matches
        AppFeedUpdate feedUpdate = feed.items.get(0).getAppFeedUpdate();
        String expectedFeedUpdate;
        expectedFeedUpdate = "AppFeedUpdate (UpdateLink, UpdateVersion, UpdateSignature)";
        assertEquals("Failure - feed update data does not match", expectedFeedUpdate, feedUpdate.toString());
        System.out.println(feedUpdate.toString());
    }
}
