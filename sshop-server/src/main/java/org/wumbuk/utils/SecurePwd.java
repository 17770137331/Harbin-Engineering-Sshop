package org.wumbuk.utils;

//import sun.net.idn.Punycode;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;

/**
 * @auther PeihaoYang
 * @date 2020/9/23 - 12:01
 */

public class SecurePwd {
    // 字符串默认键值，静态变量，存储器中全局变量

    //加密工具，记住Cipher是一个加密类
    private static Cipher encryptCipher = null;

    // 解密工具
    private static  Cipher decryptCipher = null;

    /**
     * 默认构造方法，使用默认密钥
     */
    public SecurePwd() throws Exception {


        this(getPsdFromPropeties());


    }

    public static String getPsdFromPropeties() throws IOException {

//在此处请设置您自己的密钥
        String psd="";
        System.out.println("得到配置文件的值为"+psd);
        return  psd;
    }

    
    /**
     * 指定密钥构造方法
     * @param strKey 指定的密钥
     * @throws Exception
     */
    public SecurePwd(String strKey) throws Exception {

        Key key = getKey(strKey.getBytes());

        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813，和public static byte[]
     *
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     *
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception  本方法不处理任何异常，所有异常全部抛出
     */
    public  String byteArr2HexStr(byte[] arrB) throws Exception {
        //得到字节数组的长度
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组，和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     */
    public  byte[] hexStr2ByteArr(String strIn) throws Exception {
        //定义字节的数组byte[]数组
//        System.out.println("开始了public static byte[] hexStr2ByteArr方法");
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }


    /**
     * 加密字符串
     * @param strIn 需加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String strIn) throws Exception {
//        System.out.println("开始了encrypt字符串方法");
        SecurePwd securePwd = new SecurePwd();

        return securePwd.byteArr2HexStr(encryptCipher.doFinal(strIn.getBytes()));
    }




    /*    return decryptCipher.doFinal(arrB);
    }
     * 解密字符串
     * @param strIn 需解密的字符串
     * @return 解密后的字符串
     */
    public static  String decrypt(String strIn) throws Exception {
        SecurePwd securePwd = new SecurePwd();
//        System.out.println("开始了decrypt字符串方法");
        return  new String(decryptCipher.doFinal(securePwd.hexStr2ByteArr(strIn)));
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * @param arrBTmp 构成该字符串的字节数组
     * @return 生成的密钥
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
//        System.out.println("开始 Key getKey(byte[] arrBTmp)方法");
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }








}
