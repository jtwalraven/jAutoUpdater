package org.jautoupdate.rss;

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
    SignatureType signatureType;

}
