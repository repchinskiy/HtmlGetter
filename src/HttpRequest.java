import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.net.ssl.SSLException;
import java.io.*;
/**
 * Created by bizon on 06.04.2015.
 */
public class HttpRequest {
    /**
     * HTTP request types
     */
    public static final int POST_TYPE = 1;
    public static final int GET_TYPE = 2;
    public static final int PUT_TYPE = 3;
    public static final int DELETE_TYPE = 4;

    /**
     * HTTP request header constants
     */
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String ENCODING_GZIP = "gzip";
    public static final String MIME_FORM_ENCODED = "application/x-www-form-urlencoded";
    public static final String MIME_TEXT_PLAIN = "text/plain";

    private static DefaultHttpClient httpClient = new DefaultHttpClient();
    protected static byte[] arr = new byte[4096];

    public static void main(String[] args) {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() throws IOException {
//      httpClient.getParams().removeParameter(HttpProtocolParams.USER_AGENT, mUserAgent);

//        String bid = "893391";
//        String urlValue = new StringBuilder("http://minfin.com.ua/modules/connector/connector.php?bid=").append(bid).append("&action=auction-get-contacts&r=true").toString();
        String urlValue = new StringBuilder("http://minfin.com.ua/currency/auction/usd/buy/dnepropetrovsk/").toString();


//        httpClient.getParams().removeParameter("http.useragent");
//        httpClient.getParams().setParameter("http.useragent", "http.useragent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
        try {
//            HttpPost httpPost = new HttpPost(urlValue);
            HttpGet httpGet = new HttpGet(urlValue);
//            httpGet.addHeader("http.useragent", "http.useragent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
//            httpGet.addHeader("Content-Type", "application/json");
//            httpGet.addHeader("Accept-Encoding", "gzip");
//            httpGet.addHeader("Content-Type", "application/xml");


            HttpResponse httpResponse;
            try {
                httpResponse = httpClient.execute(httpGet);
            } catch (SSLException ex) {
//                httpPost.setURI(URI.create(webRequest.url.replaceFirst(PROTOCOL_HTTPS, PROTOCOL_HTTP)));
                httpResponse = httpClient.execute(httpGet);
            }

            HttpEntity entity = httpResponse.getEntity();

            InputStream inputStream = entity.getContent();
            try {
                if (200 == httpResponse.getStatusLine().getStatusCode()) {
                    System.out.println(new String(read(inputStream)));
                } else if (404 == httpResponse.getStatusLine().getStatusCode()) {
                    System.out.println(new String(read(inputStream)));
                } else {
                    System.out.println(new String(read(inputStream)));
                }
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ex) {

                }
                entity.consumeContent();
            }
        } catch (Exception ex) {

        }
    }

    protected static byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read;
        while ((read = is.read(arr)) > 0) {
            byteArrayOutputStream.write(arr, 0, read);
        }
        byte[] result = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return result;
    }
}
