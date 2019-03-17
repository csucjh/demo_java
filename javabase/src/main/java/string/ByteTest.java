package string;

import java.io.UnsupportedEncodingException;

public class ByteTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "0x20001";

        byte[] buff = str.getBytes();

        int i = buff.length;

        System.out.println(i);
    }
}
