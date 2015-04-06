
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLReader {
    public static void main(String[] args) throws Exception {
//        System.setProperty("http.agent", "");


////        <div class="au-deals-list">
////        <div class="au-deal" tabindex="0">
////        <small class="au-deal-time">14:52</small>
////        <span class="au-deal-currency">24,90</span>
////        <span class="au-deal-sum">200  <label title="доллары США">$</label></span>
////        <span class="au-dealer-phone">
////                099&nbsp;<a href="" class="js-showPhone" data-bid-id="891856" data-numbers="">xxx-x</a>1-73
////                </span>
////
////        <span class="au-deal-msg">
////        <span class="au-msg-wrapper">
////                центр,
////                целиком, По 100
////
////                </span>
////        </span>
////        </div>
//
//        Document doc = Jsoup.connect("http://minfin.com.ua/currency/auction/usd/buy/dnepropetrovsk/").get();
//        Elements elements = doc.select("div.au-deal");
////        System.out.println( doc.getElementsByClass("au-deal-time"));
////        <a href="" class="js-showPhone" data-bid-id="892816" data-numbers="">xxx-x</a>
//
//        for (Element node : elements) {
//            for (Element child : node.children()) {
////                System.out.println(child.getElementsByAttribute("data-bid_id"));
////                System.out.println(child.classNames() + ": " + child.text());
//                if (child.classNames().contains("au-dealer-phone")) {
//                    for (Element phoneChild : child.children()) {
//                        Elements jsShowPhone = phoneChild.getElementsByClass("js-showPhone");
////                        System.out.println("js-showPhone : " + jsShowPhone.get(0).getElementsByAttribute("data-bid-id"));
////                        System.out.println("js-showPhone data-bid-id: " + jsShowPhone.get(0).attr("data-bid-id"));
//                    }
//                }
//            }
////            System.out.println(element);
//        }

        getPartPhoneNumber("891856");
        getHtmlPage("893391");
    }

    public static void getPartPhoneNumber(String bid) {
        String url = new StringBuilder("http://minfin.com.ua/modules/connector/connector.php?bid=").append(bid).append("&action=auction-get-contacts&r=true").toString();
        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println(doc.getAllElements());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getHtmlPage(String bid) throws IOException {
        String urlValue = new StringBuilder("http://minfin.com.ua/modules/connector/connector.php?bid=").append(bid).append("&action=auction-get-contacts&r=true").toString();

        URL url = new URL(urlValue);
//        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}