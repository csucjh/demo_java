import javax.crypto.Cipher;
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

    /**
     * AES密钥长度可以选择128位【16字节】，192位【24字节】和256位【32字节】密钥（其他不行，因此别乱设密码哦）。
     *
     * @throws Exception
     */
    public static void testAes() throws Exception {
        String string = "呵呵，测试AES加密";
        String key = "aes_csu_cjh_1111";
        byte[] bytes = AesEncryption.aesEncrypt(string, key);
        String decryStr = AesEncryption.aesDecrypt(bytes, key);
        System.out.println(decryStr);
    }

    /**
     * DES密钥56位，还有附加的8位校验位，3DES加长了密钥长度，可以为112位(16位校验位)或168位(24位校验位)
     *
     * 56+8=64一共64位，64/8=8，所以密钥是8字节
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
}
