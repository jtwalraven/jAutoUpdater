import java.security.Signature;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
* Generates a DSA key pair. This can be run from
* the command line to generate a DSA key pair. 
* "USAGE: DSAGenerate publicKey 
* privateKey"
*
* @author Jeffrey Walraven
*/
public class DSAGenerate {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: DSAGenerate privateKey publicKey");
        } else try {
            File privateKey = new File(args[0]);
            if (!privateKey.exists()) {
                DSAGenerate generate = new DSAGenerate();
                KeyPair keys = generate.generateKeyPair();
                
                // Save the private key
                byte[] privKey = keys.getPrivate().getEncoded();
                FileOutputStream keyFOS = new FileOutputStream(args[0]);
                keyFOS.write(privKey);
                keyFOS.close();

                // Save the public key
                byte[] pubKey = keys.getPublic().getEncoded();
                FileOutputStream pubKeyFOS = new FileOutputStream(args[1]);
                pubKeyFOS.write(pubKey);
                pubKeyFOS.close();
            } else {
                System.out.println("There is already a DSA private key here. "+
                        "Move it aside to continue.\nWARNING: If you have "+
                        "signed something with the old private key, make sure "+
                        "to keep it safe and backed up.");
            }
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }

    /**
     * Generate a DSA key pair.
     * @return Return a {@link KeyPair}
     */
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Initialize generator
        keyGen.initialize(1024, random);

        // Create the DSA key pair
        KeyPair keyPair = keyGen.generateKeyPair();

        return keyPair;
    }
}
