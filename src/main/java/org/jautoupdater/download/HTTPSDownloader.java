package org.jautoupdater.update;

import org.jautoupdater.update.HTTPDownloader;
import java.security.Security;

/**
* Downloads files from an HTTPS address. This class uses
* HTTPS protocal to download a file.
*
* @author Jeffrey Walraven
*/
public class HTTPSDownloader {

    /**
     * Download a file from an HTTPS address. 
     * Take the downloaded file and output it to a specified File.
     * @param fileURL The file to download from a server
     * @param toFile The {@link File} to output to
     * @return Whether the {@link File} was succesfully
     * downloaded and sent into a new {@link File}
     */
    public boolean downloadFile(String fileURL, String toFile) {
        // Since Java 7, SNIExtension must be set to false to avoid error.
        // This is because SSL will cause an error which only Java picks up on.
        //
        // See:
        // http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0
        System.setProperty("jsse.enableSNIExtension", "false");


        // Set property to use HTTPS protocal
        System.setProperty("java.protocol.handler.pkgs",
                       "com.sun.net.ssl.internal.www.protocol");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        // Use HTTPDownloader with the new properties set
        HTTPDownloader httpDownloader = new HTTPDownloader();
        return httpDownloader.downloadFile(fileURL, toFile);
    }
}
