package algorithm;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密
 * <p>
 * AES与DES
 */
public class SymmetricalEncryption {

    public static void main(String[] args) throws Exception {
//        testAes();
        testDes();
    }

    public static void testAes() throws Exception {
        String string = "呵呵，测试AES加密";
        String key = "aes_csu_cjh_1111";
        byte[] bytes = AesEncryption.aesEncrypt(string, key);
        String decryStr = AesEncryption.aesDecrypt(bytes, key);
        System.out.println(decryStr);
    }

    /**
     * @throws Exception
     */
    public static void testDes() throws Exception {
        String string = "呵呵，测试DES加密";
        String key = "csu_cjh_";
        byte[] bytes = DesEncryption.desEncrypt(string, key);
        String decryStr = DesEncryption.desDecrypt(bytes, key);
        System.out.println(decryStr);
    }
}


/**
 * AES密钥长度可以选择128位【16字节】，192位【24字节】和256位【32字节】密钥
 */
class AesEncryption {
    /**
     * 使用AES对字符串加密
     *
     * @param str utf8编码的字符串
     * @param key 密钥（16字节）
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return bytes;
    }

    /**
     * 使用AES对数据解密
     *
     * @param bytes utf8编码的二进制数据
     * @param key   密钥（16字节）
     * @return 解密结果
     * @throws Exception
     */
    public static String aesDecrypt(byte[] bytes, String key) throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

}

/**
 * 指定为DES就行。3DES指定为”DESede”
 * <p>
 * DES密钥56位，还有附加的8位校验位，3DES加长了密钥长度，可以为112位(16位校验位)或168位(24位校验位)
 * 56+8=64一共64位，64/8=8，所以密钥是8字节
 * 112+16=128，密钥是16字节
 * 168+24=192,密钥是24字节
 */
class DesEncryption {
    /**
     * 使用DES对字符串加密
     *
     * @param str utf8编码的字符串
     * @param key 密钥（56位，7字节）
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] desEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "DES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return bytes;
    }

    /**
     * 使用DES对数据解密
     *
     * @param bytes utf8编码的二进制数据
     * @param key   密钥（16字节）
     * @return 解密结果
     * @throws Exception
     */
    public static String desDecrypt(byte[] bytes, String key) throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "DES"));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }


    /**
     * 3DES加密
     *
     * @param key    密钥，24位
     * @param srcStr 将加密的字符串
     * @return lee on 2017-08-09 10:51:44
     */
    public static String encode3Des(String key, String srcStr) {
        byte[] src = srcStr.getBytes();
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(key.getBytes("utf-8"), "DESede");
            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, deskey);

            String pwd = Base64.encodeBase64String(c1.doFinal(src));
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES解密
     *
     * @param key    加密密钥，长度为24字节
     * @param desStr 解密后的字符串
     * @return lee on 2017-08-09 10:52:54
     */
    public static String decode3Des(String key, String desStr) {
        byte[] src = Base64.decodeBase64(desStr);

        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(key.getBytes("utf-8"), "DESede");
            //解密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            String pwd = new String(c1.doFinal(src));
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
