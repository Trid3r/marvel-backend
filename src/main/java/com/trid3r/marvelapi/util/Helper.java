package com.trid3r.marvelapi.util;

import org.springframework.beans.factory.annotation.Value;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {

    @Value("${privateKey}")
    private static String privateKey;

    @Value("${publicKey}")
    private static String publicKey;

    public static String generateMarvelAPIHash(String ts) {
        try {
            String value = ts + privateKey + publicKey;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(value.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 hashing algorithm not found", e);
        }
    }
}
