import java.security.Signature;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
* Signs data with a signature generated from a DSA private key.
* This private key must be specified. This class is meant to
* be run from the command line. "USAGE: DSASign privateKey fileToSign"
*
* @author Jeffrey Walraven
*/
public class DSASign {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: DSASign privateKey fileToSign");
        } else try {
            File privateKey = new File(args[0]);
            File unsignedFile = new File(args[1]);
            if (privateKey.exists() && unsignedFile.exists()) {
                DSASign signer = new DSASign();

                FileInputStream keyFIS = new FileInputStream(args[0]);

                byte[] encKey = new byte[keyFIS.available()];   // Create byte array
                keyFIS.read(encKey);    // Read the private key to the byte array
                keyFIS.close(); // Close the stream

                PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("DSA");
                PrivateKey privKey = keyFactory.generatePrivate(privKeySpec);

                // Create signature
                Signature sig = signer.createSignature(privKey);

                // Sign the data
                signer.signData(args[1], sig);

                System.out.println("File signed successfully!");
            } else {
                if (!privateKey.exists()) {
                    System.out.println("Error: The private key file you specified does not exist.");
                } else if (!unsignedFile.exists()) {
                    System.out.println("Error: The file to sign specified does not exist.");
                }
            }
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Sign the data with the specified {@link Signature}.
     * @param file The data file to write to
     * @param sig The {@link Signature}
     */
    public void signData(String file, Signature sig) throws IOException, SignatureException {
        FileInputStream fIS = new FileInputStream(file);
        BufferedInputStream bIS = new BufferedInputStream(fIS);
        byte[] buffer = new byte[1024];
        int len;
        while (bIS.available() != 0) {
            len = bIS.read(buffer);
            sig.update(buffer, 0, len);
        }
        bIS.close();
    }

    /**
     * Create a DSA signature from the private key.
     * @param privateKey The private key to generate the signature from
     * @return Returns a DSA signature
     */
    public Signature createSignature(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException{
        Signature sig = Signature.getInstance("SHA1withDSA");
        sig.initSign(privateKey);
        return sig;
    }
}
