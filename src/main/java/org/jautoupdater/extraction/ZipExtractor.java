package org.jautoupdater.extraction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* A file extractor for *.zip files. 
* This class takes in plain *.zip files and outputs
* to the specified directory.
*
* @author JeffreyWalraven
*/
public class ZipExtractor {

    /**
     * Extract the specified *.zip file to the specified output directory.
     *
     * @param inFile The input *.zip file to extract
     * @param outDir The directory to extract to
     * @throws IOException
     */
    public void extractZip(String inFile, String outDir) {
        try {
            // First create output directory if it doesn't exist
            if (!new File(outDir).exists()) {
                new File(outDir).mkdir();
            }

            InputStream inStream = null;
            OutputStream outStream = null;
            ZipFile zipFile = new ZipFile(inFile);  // The ZIP file to extract

            Enumeration zipEntries = zipFile.entries();
            while (zipEntries.hasMoreElements()) {  // While there are entries to extract
                ZipEntry entry = (ZipEntry) zipEntries.nextElement();
                if (entry.isDirectory()) {
                    new File(outDir, entry.getName()).mkdir();
                    continue;
                }
                inStream = new BufferedInputStream(zipFile.getInputStream(entry));
                File outFile = new File(outDir, entry.getName());
                FileOutputStream fOS = new FileOutputStream(outFile);
                outStream = new BufferedOutputStream(fOS);
                
                int count;  // The number of bytes to write
                byte data[] = new byte[1024]; // The data bytes
                while ((count = inStream.read(data, 0, 1024)) != -1) {
                    outStream.write(data, 0, count); // Write out to the specified Directory
                }

                // IMPORTANT: Make sure to flush and close streams
                inStream.close();
                outStream.flush();
                outStream.close();
            }
        } catch (IOException ex) {
           Logger.getLogger(ZipExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
