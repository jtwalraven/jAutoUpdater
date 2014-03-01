package org.jautoupdater.update;

import static org.junit.Assert.assertTrue;
import junitx.framework.FileAssert;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.net.URL;

/**
* Tests for {@link HTTPSDownloader}.
*
* @author Jeffrey Walraven
*/
@RunWith(JUnit4.class)
public class HTTPSDownloaderTest {

    @Test
    public void downloadFileTest() {
        // Get the working directory
        String workingDir = System.getProperty("user.dir");

        // The test file URL
        String testFileURL = "https://jtwalraven.github.io/jAutoUpdater/files/tests/DownloadFile.txt";

        // Create a new HTTPDownloader
        HTTPSDownloader downloader = new HTTPSDownloader();

        // Download the test file
        boolean result = downloader.downloadFile(testFileURL, workingDir+"HTTPDownloader-TestFile.txt");
        assertTrue("Failure - HTTPDownloader return result false", result);

        // Get the test file from the URL
        File testFile = new File(workingDir+"/jAutoUpdaterHTTPDownloader-TestFile.txt");

        // Fetch test file from resources
        String testLocalFileURL;
        testLocalFileURL = workingDir+"/build/resources/test/org/jautoupdater/update/"
            +"jAutoUpdaterHTTPDownloader-TestFile.txt";
        File testLocalFile = new File(testLocalFileURL);

        // Compare files
        FileAssert.assertEquals("Failure - Local test file did not match with remote test file",
                testLocalFile, testFile);

        // Delete remotely downloaded test file
        testFile.delete();
    }
}
