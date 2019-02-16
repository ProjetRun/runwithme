package miage.parisnanterre.fr.runwithme.settings;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

    private static final String transformation = "AES";
    private static final String algorithme = "SHA-256";



    public static void encrypt(Serializable object, OutputStream ostream, String mdp) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        try {
            byte[] key = calculerHash(algorithme, mdp);

            SecretKeySpec sks = new SecretKeySpec(key, transformation);

            // Create cipher
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            SealedObject sealedObject = new SealedObject(object, cipher);

            // Wrap the output stream
            CipherOutputStream cos = new CipherOutputStream(ostream, cipher);
            ObjectOutputStream outputStream = new ObjectOutputStream(cos);
            outputStream.writeObject(object);
            outputStream.close();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static Object decrypt(InputStream istream, String mdp) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        byte[] key = calculerHash(algorithme, mdp);

        SecretKeySpec sks = new SecretKeySpec(key, transformation);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, sks);

        CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
        ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);

        try {
            Object o = (Object) inputStream.readObject();
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
        /*SealedObject sealedObject;
        try {
            sealedObject = (SealedObject) inputStream.readObject();
            return sealedObject.getObject(cipher);
        } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }*/
    }

    public static byte[] calculerHash(String algorithme, String mdp) {
        byte[] digest = null;
        try {
            MessageDigest mDig = MessageDigest.getInstance(algorithme);
            mDig.update(mdp.getBytes());
            digest = mDig.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }

}
