package okhttp;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpTest {

    public static void main(String[] args) {

        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "I am Jdqm.";
        Request request = new Request.Builder().url("url").post(RequestBody.create(mediaType, requestBody)).build();


        System.out.println();
    }
}
