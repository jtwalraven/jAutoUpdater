package org.jautoupdater.rss.model;

/**
* Contains information from a {@link AppFeedItem} about the
* app update. This includes information about the download
* file, the version number, and, if necessary, a signature for
* signing the app file.
*
* @author Jeffrey Walraven
*/
public class AppFeedUpdate {
    
    String downloadLink;
    String version;
    String signature;

    /**
     * Construct the AppFeedUpdate.
     * @param downloadLink The link to the update files
     * @param version The version of this download
     * @param signature The DSA signature of the download
     */
    public AppFeedUpdate(String downloadLink, String version, String signature) {
        this.downloadLink = downloadLink;
        this.version = version;
        this.signature = signature;
    }

    /**
     * Get the download link of the update.
     * @return The String URL of the download
     */
    public String getDownloadLink() {
        return downloadLink;
    }

    /**
     * Get the version of the download.
     * @return The String version number of the download
     */
    public String getVersion() {
        return version;
    }

    /**
     * Get the DSA signature of the download.
     * @return The DSA signature
     */
    public String getSignature() {
        return signature;
    }
}
