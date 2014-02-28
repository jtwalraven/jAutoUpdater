package org.jautoupdater.update;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

/**
* Downloads files from an HTTP address. This class uses
* HTTP protocal to download a file.
*
* @author Jeffrey Walraven
*/
public class HTTPDownloader {

    /**
     * Download a file from an HTTP address. 
     * Take the downloaded file and output it to a specified File.
     * @param fileURL The file to download from a server
     * @param toFile The {@link File} to output to
     * @return Whether the {@link File} was succesfully
     * downloaded and sent into a new {@link File}
     */
    public boolean downloadFile(String fileURL, String toFile) {
        URL url;
        
        // Convert String url to URL
        try {
            url = new URL(fileURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            // Create new connection
            URLConnection urlCon = url.openConnection();
            File outFile = new File(toFile);

            // Create InputStream and OutputStream
            BufferedInputStream bis = new BufferedInputStream(urlCon.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(outFile.getName()));
            for (int i = 0; i != -1; i = bis.read()) {
                bos.write(i);
            }

            // IMPORTANT: Make sure to flush and close streams
            bos.flush();
            bis.close();

            // Return successful
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
