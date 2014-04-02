package org.jautoupdater.extraction;

import junitx.framework.FileAssert;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

/**
* Tests for {@link ZipExtractor}.
*
* @author JeffreyWalraven
*/
@RunWith(JUnit4.class)
public class ZipExtractorTest {

    @Test
    public void extractZipTest() {
        // Get the working directory
        String workingDir = System.getProperty("user.dir");
        String testFilesDir = workingDir+"/build/resources/test/org/jautoupdater/extraction/";

        // Run the extractor on the test zip file
        ZipExtractor zipExtractor = new ZipExtractor();
        zipExtractor.extractZip(testFilesDir+"ZipTest.zip", testFilesDir+"ZipTest/");

        // Define the test files
        File testFile = new File(testFilesDir, "Extractor_Test_Dir/Test_File.txt");
        File resultFile = new File(testFilesDir, "ZipTest/Extractor_Test_Dir/Test_File.txt");

        // Verify the results
        FileAssert.assertEquals("Failure - Extracted file did not match original", 
                testFile, resultFile);
    }
}
