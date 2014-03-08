package org.jautoupdater.dsa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
* Tests for {@link DSAVerify}.
*
* @author Jeffrey Walraven
*/
@RunWith(JUnit4.class)
public class DSAVerifyTest {

    @Test
    public void verifyFileTest() {
        // Get the working directory                                                                
        String workingDir = System.getProperty("user.dir");
        String testFilesDir;
        testFilesDir = workingDir+"/build/resources/test/org/jautoupdater/dsa/";

        // Test file names
        String pubKey = testFilesDir+"suepk";
        String signature = testFilesDir+"sig";
        String file = testFilesDir+"signed_file.txt";

        // Initialize DSAVerify
        DSAVerify veri = new DSAVerify();

        // Verify test file
        boolean verified = veri.verifyFile(pubKey, signature, file);
        assertEquals("Failure - File should be valid through signature", true, verified);
    }

    @Test
    public void rejectFileTest() {
        // Get the working directory                                                                
        String workingDir = System.getProperty("user.dir");
        String testFilesDir;
        testFilesDir = workingDir+"/build/resources/test/org/jautoupdater/dsa/";

        // Test file names
        String pubKey = testFilesDir+"suepk";
        String signature = testFilesDir+"sig";
        String file = testFilesDir+"unsigned_file.txt";

        // Initialize DSAVerify
        DSAVerify veri = new DSAVerify();

        // Verify test file
        boolean verified = veri.verifyFile(pubKey, signature, file); 
        assertEquals("Failure - File should be invalid through signature", false, verified);
    }
}
