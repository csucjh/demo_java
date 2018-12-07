package charset;

import java.io.UnsupportedEncodingException;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = "测试".getBytes("GBK");
        String str = new String(bytes, "UTF-8");
        System.out.println(str);
    }
}
