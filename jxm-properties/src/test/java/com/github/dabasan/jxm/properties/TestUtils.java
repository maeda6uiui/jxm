package com.github.dabasan.jxm.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility methods for test
 *
 * @author maeda6uiui
 */
public class TestUtils {
    public static String getFileHash(String filepath) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] hash = null;
        try (var dis = new DigestInputStream(new BufferedInputStream(new FileInputStream(filepath)), md)) {
            while (dis.read() != -1) {

            }

            hash = md.digest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var sb = new StringBuilder();
        for (byte b : hash) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }

        return sb.toString();
    }
}
