package com.titan.arm.md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/17
 * \* Time: 15:10
 * \* Description: 密码加密
 * \
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * 加密方法
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
    //编写主类进行测试
    public static void main(String args[]) {
        String s = "Kodak";
        System.out.println("原始：" + s);

        String jm=MD5Util.inputPassToFormPass(s);
        System.out.println("加密后："+jm);

        System.out.println("解密："+MD5Util.formPassToDBPass(jm,"1a2b3c"));

    }
}
