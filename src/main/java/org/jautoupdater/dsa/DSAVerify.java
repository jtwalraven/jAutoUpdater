package org.jautoupdater.dsa;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* Takes a File and verifies it with its DSA public key.
* This class uses the java.security libraries for 
* working with DSA signatures.
*
* @author Jeffrey Walraven
*/
public class DSAVerify {

    /**
     * Verify a file was signed with the specified public key and signature.
     * @param pubKey The public DSA key
     * @param signature The DSA signature
     * @param file The signed file to verify
     * @return Whether or not the file is verified
     */
   public boolean verifyFile(String pubKey, String signature, String file) {
       FileInputStream keyFIS = null;
       try {
           keyFIS = new FileInputStream(pubKey);

           byte[] encKey = new byte[keyFIS.available()];  // Create byte array
           keyFIS.read(encKey); // Read the public key to the byte array
           keyFIS.close(); // Close the stream

           X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
           KeyFactory keyFactory = KeyFactory.getInstance("DSA");
           PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

           byte[] sigVerify;
           try (FileInputStream sigFIS = new FileInputStream(signature)) {
               sigVerify = new byte[sigFIS.available()];
               sigFIS.read(sigVerify);
           }

           // Create Signature object and initialize it with the public key
           Signature sig = Signature.getInstance("SHA1withDSA");
           sig.initVerify(publicKey);
           FileInputStream fileFIS = new FileInputStream(file);

           try (BufferedInputStream bufferedIn = new BufferedInputStream(fileFIS)) {
               byte[] buffer = new byte[1024]; // Create a new buffer
               int dat;
               while (bufferedIn.available() != 0) {
                   dat = bufferedIn.read(buffer); // Read the next set of data in
                   sig.update(buffer, 0, dat); // Update the signature
               }
           }

        return sig.verify(sigVerify); // Return if the signature verifies

       } catch (FileNotFoundException ex) {
           Logger.getLogger(DSAVerify.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | 
               InvalidKeyException | SignatureException ex) {
           Logger.getLogger(DSAVerify.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               if (keyFIS != null) {
                   keyFIS.close();
               }
           } catch (IOException ex) {
               Logger.getLogger(DSAVerify.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

       return false; // If nothing is run, return false
   }
}
