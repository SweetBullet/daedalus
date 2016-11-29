package com.bullet.sweet.daedalus.kern;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * Created by Zhanlan on 16/11/17.
 */

public class SignatureFactory {

    //排序、拼接、生成签名
    public static String createSignature(String token, String timestamp, String nonce, String encrypt) throws Exception {
        try {
            String[] params = new String[]{token, timestamp, nonce, encrypt};
            Arrays.sort(params);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < params.length; ++i) {
                sb.append(params[i]);
            }
            String combination = sb.toString();
            return DigestUtils.sha1Hex(combination);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Signature:SHA-1");
        }
    }


    public static String createSignature(String token, String timestamp, String nonce) throws Exception {
        return createSignature(token, timestamp, nonce, "");
    }


}
