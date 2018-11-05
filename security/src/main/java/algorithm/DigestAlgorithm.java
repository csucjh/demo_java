package algorithm;

import algorithm.enums.AlgorithmEnum;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 测试摘要算法。
 * 常用的摘要算法有MD5,SHA1
 */
public class DigestAlgorithm {

    public static void main(String[] args) throws Exception {

        String file = "D:\\data\\2222.jpg";

        // md5
        String md5 = digest(file, AlgorithmEnum.MD5);
        System.out.println(md5);


        // sha-1
        String sha_1 = digest(file, AlgorithmEnum.SHA_1);
        System.out.println(sha_1);


        // 使用commons-codec包的工具类
        FileInputStream ins = new FileInputStream(new File(file));
        sha_1 = DigestUtils.sha1Hex(ins);
        System.out.println(sha_1);
        ins.close();

        ins = new FileInputStream(new File(file));
        md5 = DigestUtils.md5Hex(ins);
        System.out.println(md5);
        ins.close();
    }

    public static String digest(String path, AlgorithmEnum algorithm) {
        String pathName = path;
        String encryStr = "";
        try {
            File file = new File(pathName);
            FileInputStream ins = new FileInputStream(file);
            FileChannel ch = ins.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance(algorithm.getName());
            md.update(byteBuffer);
            ins.close();
            encryStr = toHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encryStr;
    }

    //以16进制编码进行输出
    final static char hex[] = "0123456789ABCDEF".toCharArray();

    public static String toHexString(byte[] tmp) {
        String s;
        char str[] = new char[tmp.length * 2];
        int k = 0;
        for (int i = 0; i < tmp.length; i++) {
            byte byte0 = tmp[i];
            str[k++] = hex[byte0 >>> 4 & 0xf];
            str[k++] = hex[byte0 & 0xf];
        }
        s = new String(str);
        return s;
    }
}


