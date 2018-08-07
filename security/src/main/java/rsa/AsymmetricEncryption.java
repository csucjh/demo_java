package rsa;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

/**
 * 非对称加密，RSA
 */
public class AsymmetricEncryption {
    public static void main(String[] args) throws Exception {
//        testRsa1();
//        testRsa2();
//        testRsa3();
        testRsa4();
    }

    private static void testRsa4() throws Exception {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGOYehRP16BdUc2XhVplpGYVpkgEY6aTcmmDN0RPHEYcG7pGzTnM7JYoNvl2B5Zg3QVsKR5GxRqwcO2xAjtrCh51UYVIob2O5s8eqj8BQHWEjt3Z6IeHZMgtTIxCulrjkCpRqbsHkigdObWy39wvgMOBv44UjTi4SyBCjAsPyxUi1d8FY7vOPvsNGjRFRefSdYZpUA43oWnoyJ+0tK+pw5TGnPPA4ZWNQ0zRcEAsRg5BtomD4FJCPOM80skcwJvXKjbnbu6ExBC7FOBlhQqsYY5PQwo3ET9mr1ehHY+clVVOsCwoubtGwuRfPimk0R9al6SBV3FToF+zQtxtniZHGLAgMBAAECggEBAKF5vfXuqHodhDSjJzbGc+W/ao5SPGNa0JV9CPVfMH+kjtgXoptjssQ0nxNpgiqCqyAbfRaxuFdy9iXw6B3ftdW+CADw5Sy4M8nF7Dmt2fL9xTMYBQvw7ZV4QnRl8ZRe74wrCRjo0lT84288AKQmjkAN+SNuChBiKC+H2dbWlpKvCNdJDvWtmNjOpfZlqHM3pu2oc5GhT69MPR6/0Mg7x01spTQRrYzzvIHWsvQz8CE+hx1N8hlztsjPTWNNR77FG5iE9XQZTOkFvtyb1u1/RyGygNUxjExQYZmzy9PsmSPtSbkWUQde4D8/Wl2vNduZsgkClcNucyIyr9FqmofGizECgYEA+aa1G+onglZNtafnfiu30h2TDM0d2CuzYwWIuCbL3FeuP+5pcr8+Likn+sSsg5YcwPAONBb37FETJo0fEU2NEdNHWOJ5jf9O+zoug7UE3ApimDtZEfgJT4sudBgWpPhWWOH4BnJfWp3qIlmE6wYe4VwyE7eKe2clIv7G9V5GZfMCgYEAy0QF4VwgHNu//2vuYqjANgL/wcDndzpjr9g75JFmTcnrmkdKlX0/HlbbNeM/ASZQweYW6NUIUuBVob5F7E/rX7ULA1MktxnQbfdKDIrHFs4skiyCijL+FlO/Hq7Dpt3AnTGMz3z4XsBRL0V5PWq0Pmp4Wibn3nZMdIKmPgfUtAkCgYAuhe+YPSGRu22N3bVZIOKHqHU/3HWkVDiAyoY48xnofPp+9Xn3JUZ8KbVGmSIg/3dqSRIMiP84rb8o3wGcItyYJsxT9xh56ssYLDaA2toF+3V2MxEdNSDfDuvgPKDzq0fEFpA2+1djtqvsLMyFU8Wrb5fxbrHC32zfQaVoEmKCGwKBgFMjBlivan8DLltVFNKLfNpajar8sG9mlWFVyuFoyvQkE1YttIbCDG/nTI+U268nsb4s0s/2WgozsTKPdV/7LCUgEloQxqKRT1u62jGZDWb5eudqhxXpV11gcXomNuXoKyDgJFy1G4z9kfcIrlb9bq1ctM8CvLGTpx+8VCxo3YmpAoGAVzR//khWqsjpkm6NRDgtc3Emh8DouaoEuP7YdKBxYJS935i+GlBUySDgXSL2R33DJosScatNEiQPAcYl6iF9B9ei5ken9Nj0xgBS5sx2wq/TdHjREWWiRTt4Cx55JCF1dtzqSwp10vI4wT+1YNLIpYzaun0AIsxtEmuYMaWgsyk=";
        Map<String, String> map = RSAUtil.nedFromPrivateKey(privateKey);

        String n_str = map.get("N");
        String e_str = map.get("e");
        String d_str = map.get("d");
        String p_Str = map.get("p");
        String q_Str = map.get("q");
        String ep_Str = map.get("ep");
        String eq_Str = map.get("eq");
        String coeff_Str = map.get("coeff");

        PublicKey pbk = RSAUtil.getPulbickey(n_str, e_str);
        String publicKeyStr = new String(new Base64().encode(pbk.getEncoded()));
        PrivateKey prk = RSAUtil.getPrivateCrtkey(n_str, d_str, e_str, p_Str, q_Str, ep_Str, eq_Str, coeff_Str);
        String privateKeyStr = new String(new Base64().encode(prk.getEncoded()));
    }


    /**
     * 通过N e d三个特征值可以还原出公钥，私钥与原来的不一样了
     *
     * @throws Exception
     */
    private static void testRsa3() throws Exception {
        Map<String, String> map = RSAUtil.generateKeyPair2();
        String n_str = map.get("N");
        String e_str = map.get("e");
        String d_str = map.get("d");

        PublicKey publicKey = RSAUtil.getPulbickey(n_str, e_str);
        System.out.println("publicKey:" + new String(new Base64().encode(publicKey.getEncoded())));
        PrivateKey privateKey = RSAUtil.getPrivatekey(n_str, d_str);
        System.out.println("privateKey:" + new String(new Base64().encode(privateKey.getEncoded())));
    }

    private static void testRsa2() throws Exception {
        Map<String, String> map = RSAUtil.generateKeyPair1();//生成密钥对
        PublicKey publicKey = RSAUtil.getPulbickey(map.get("publicKey"));
        PrivateKey privateKey = RSAUtil.getPrivatekey(map.get("privateKey"));
    }

    private static void testRsa1() throws Exception {
        RSAUtil.generateKeyPair();//生成密钥对
        String source = "Hello World!";// 要加密的字符串
        byte[] cryptograph = RSAUtil.encrypt(source);// 生成的密文

        //可以将密文进行base64编码进行传输
        System.out.println(new String(new Base64().encode(cryptograph)));

        String target = RSAUtil.decrypt(cryptograph);// 解密密文
        System.out.println(target);
    }
}


class RSAUtil {
    /**
     * 指定加密算法为RSA
     */
    private static String ALGORITHM = "RSA";
    /*指定加密模式和填充方式*/
    private static String ALGORITHM_MODEL = "RSA/ECB/PKCS1Padding";
    /**
     * 指定key的大小，一般为1024，越大安全性越高
     */
    private static int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "PrivateKey";


    /**
     * ####################################################################################################
     */

    /**
     * 生成密钥对，公钥私钥使用对象流序列化
     */
    public static void generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        /** 用对象流将生成的密钥写入文件 */
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
                PUBLIC_KEY_FILE));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
                PRIVATE_KEY_FILE));
        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);
        /** 清空缓存，关闭文件输出流 */
        oos1.close();
        oos2.close();
    }

    /**
     * 加密方法 source： 源数据
     */
    public static byte[] encrypt(String source) throws Exception {

        /** 将文件中的公钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                PUBLIC_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODEL);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return b1;
    }

    /**
     * 解密算法 cryptograph:密文
     */
    public static String decrypt(byte[] cryptograph) throws Exception {
        /** 将文件中的私钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                PRIVATE_KEY_FILE));
        Key key = (Key) ois.readObject();
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODEL);
        cipher.init(Cipher.DECRYPT_MODE, key);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(cryptograph);
        return new String(b);
    }


/**
 * ####################################################################################################
 */

    /**
     * 生成密钥对，公钥私钥以base64编码
     */

    public static Map<String, String> generateKeyPair1() throws Exception {
        SecureRandom sr = new SecureRandom();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024, sr);
        KeyPair kp = kpg.generateKeyPair();
        Key publicKey = kp.getPublic();
        Key privateKey = kp.getPrivate();
        byte[] pb = publicKey.getEncoded();
        String pbStr = new String(new Base64().encode(pb));
        byte[] pr = privateKey.getEncoded();
        String prStr = new String(new Base64().encode(pr));
        Map<String, String> map = new HashMap<String, String>();
        map.put("publicKey", pbStr);
        map.put("privateKey", prStr);
        return map;
    }

    //从base64编码的公钥恢复公钥
    public static PublicKey getPulbickey(String key_base64) throws Exception {
        byte[] pb = new Base64().decode(key_base64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pb);
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        return keyfactory.generatePublic(keySpec);
    }

    //从base64编码的私钥恢复私钥
    public static PrivateKey getPrivatekey(String key_base64) throws Exception {
        byte[] pb = new Base64().decode(key_base64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pb);
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        return keyfactory.generatePrivate(keySpec);
    }

    /**
     * ####################################################################################################
     */


    /**
     * 提取特征值保存，以base64编码密钥
     * <p>
     * 记录RSA的密钥特征值并进行密码恢复
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> generateKeyPair2() throws Exception {
        SecureRandom sr = new SecureRandom();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024, sr);
        KeyPair kp = kpg.generateKeyPair();
        Key publicKey = kp.getPublic();
        Key privateKey = kp.getPrivate();
        RSAPublicKey rpk = (RSAPublicKey) publicKey;
        RSAPrivateKey rpr = (RSAPrivateKey) privateKey;
        //三个特征值都是BigInteger类型。
        BigInteger N = rpk.getModulus();//N值
        BigInteger e = rpk.getPublicExponent();//e值
        BigInteger d = rpr.getPrivateExponent();//d值
        Map<String, String> map = new HashMap<String, String>();
        //将BigInteger转为byte[],然后以base64保存
        map.put("N", new String(new Base64().encode(N.toByteArray())));
        map.put("e", new String(new Base64().encode(e.toByteArray())));
        map.put("d", new String(new Base64().encode(d.toByteArray())));

        System.out.println("publicKey:" + new String(new Base64().encode(rpk.getEncoded())));
        System.out.println("privateKey:" + new String(new Base64().encode(rpr.getEncoded())));
        return map;
    }

    /**
     * 从私钥提取特征值保存，以base64编码密钥
     * <p>
     * 记录RSA的密钥特征值并进行密码恢复
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> nedFromPrivateKey(String privateKey) throws Exception {
        PrivateKey privateK = getPrivatekey(privateKey);
        RSAPrivateKey rpr = (RSAPrivateKey) privateK;
        //三个特征值都是BigInteger类型。
        BigInteger N = rpr.getModulus();//N值
        BigInteger d = rpr.getPrivateExponent();//d值
        BigInteger e = (BigInteger) getFieldVal(rpr, "e");//e值
        BigInteger p = (BigInteger) getFieldVal(rpr, "p");//e值
        BigInteger q = (BigInteger) getFieldVal(rpr, "q");//e值
        BigInteger pe = (BigInteger) getFieldVal(rpr, "pe");//e值
        BigInteger qe = (BigInteger) getFieldVal(rpr, "qe");//e值
        BigInteger coeff = (BigInteger) getFieldVal(rpr, "coeff");//e值
        Map<String, String> map = new HashMap<String, String>();
        //将BigInteger转为byte[],然后以base64保存
        map.put("N", new String(new Base64().encode(N.toByteArray())));
        map.put("d", new String(new Base64().encode(d.toByteArray())));
        map.put("e", new String(new Base64().encode(e.toByteArray())));
        map.put("p", new String(new Base64().encode(p.toByteArray())));
        map.put("q", new String(new Base64().encode(q.toByteArray())));
        map.put("ep", new String(new Base64().encode(pe.toByteArray())));
        map.put("eq", new String(new Base64().encode(qe.toByteArray())));
        map.put("coeff", new String(new Base64().encode(coeff.toByteArray())));
        return map;
    }

    private static Object getFieldVal(Object obj, String name) throws Exception {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
    }

    //从base64编码的特征值(N,e)恢复公钥
    public static PublicKey getPulbickey(String N_Str, String e_Str) throws Exception {
        BigInteger N = new BigInteger(1, new Base64().decode(N_Str.getBytes()));
        BigInteger e = new BigInteger(1, new Base64().decode(e_Str.getBytes()));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec ps = new RSAPublicKeySpec(N, e);
        PublicKey pkey = kf.generatePublic(ps);
        return pkey;
    }

    //从base64编码的特征值（N,d）恢复私钥
    public static PrivateKey getPrivatekey(String N_Str, String d_Str) throws Exception {
        BigInteger N = new BigInteger(1, new Base64().decode(N_Str.getBytes()));
        BigInteger d = new BigInteger(1, new Base64().decode(d_Str.getBytes()));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec ps = new RSAPrivateKeySpec(N, d);
        PrivateKey pkey = kf.generatePrivate(ps);
        return pkey;
    }

    //从base64编码的特征值（N,d）恢复私钥
    public static PrivateKey getPrivateCrtkey(String N_Str, String d_Str, String e_Str, String p_Str, String q_Str, String ep_Str, String eq_Str, String coeff_Str) throws Exception {
        BigInteger N = new BigInteger(1, new Base64().decode(N_Str.getBytes()));
        BigInteger d = new BigInteger(1, new Base64().decode(d_Str.getBytes()));
        BigInteger e = new BigInteger(1, new Base64().decode(e_Str.getBytes()));
        BigInteger p = new BigInteger(1, new Base64().decode(p_Str.getBytes()));
        BigInteger q = new BigInteger(1, new Base64().decode(q_Str.getBytes()));
        BigInteger ep = new BigInteger(1, new Base64().decode(ep_Str.getBytes()));
        BigInteger eq = new BigInteger(1, new Base64().decode(eq_Str.getBytes()));
        BigInteger coeff = new BigInteger(1, new Base64().decode(coeff_Str.getBytes()));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec ps = new RSAPrivateCrtKeySpec(N, d, e, p, q, ep, eq, coeff);
        PrivateKey pkey = kf.generatePrivate(ps);
        return pkey;
    }


}