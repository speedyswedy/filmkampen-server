package com.filmkampen.filmkampen_server.crypt;

import org.jasypt.util.text.BasicTextEncryptor;

public class Crypto {
    
    private static String KEY = "sdfkjQww23333;df???dfdf";

    public static String encrypt(String value) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(KEY);
        return textEncryptor.encrypt(value);
    }
    
    public static String decrypt(String value) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(KEY);
        return textEncryptor.decrypt(value);
    }
}
