/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Proyectos
 */
public class SecurityHelper {

    public static final String KEY_ALGORITHM = "DES";
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";
    public static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";

    /**
     * Key generation
     *
     * @return
     * @throws Exception
     */
    private static byte[] generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(56); //Des must be 56, the initial method does not have to call
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * Reduction of keys
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec des = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(des);
        return secretKey;
    }

    /**
     * Encryption
     *
     * @param data
     * @Param data
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data) throws Exception {
        byte[] key = generateKey();
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * Encryption
     *
     * @param data
     * @param key
     * @Param data
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

}
