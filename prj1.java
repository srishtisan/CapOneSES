/**
 * Created by Srishti on 3/21/2016.
 */


import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;
import java.util.*;
import java.util.HashMap;
import java.util.Map;


public class CapitalOneSummarizer {
    static String[] cmnWords = new String[] {"a","an","the","and","mention","New","really","several","high","low","faster","have","had","will","at","The",
            "by","did","for","during","During","just","over","with","there","then","in","it","even","if","other","now","could","would","should",
            "while","than","and","been","be","to","this","that","of","or","but","But","its","he","you","I","they","we","said","on","in","into","more","Ã¢â‚¬â€�","about","Ã¢â‚¬ï¿½","also",
            "has","not","another","only","is","was","were","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","may","might","new","old","as"};
    public static void httpGet(String urlStr) throws IOException {
        Document doc;
        String title = " ";
        String bodycontent[];
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, String> revMap = new HashMap<>();

        try { doc = Jsoup.connect(urlStr).get();
            title = doc.title();
            Elements paras = doc.select("article[itemprop=\"articleBody\"]"); // a with href


            bodycontent = paras.text().split("[ .,]+");
            Elements author = doc.select("span[itemprop=\"name\"]");
            System.out.println("Author: " + author.text());
            Elements authorBio = doc.getElementsByClass("post-body-bio");
            System.out.println("Author Bio: \n\n" + authorBio.text());
            System.out.println("Total Number of Paragraphs: " + paras.select("p").size());
            System.out.println("Title: " +doc.title());
            System.out.println("Summary: \n");
            System.out.println(paras.select("p").get(0).text());
            Elements links = paras.select("a[href^=http:]"); // links with href
            System.out.println("Links in the page: \n\n" + links);
            System.out.println();
            // Perform Wordcount by using map, provide a cutoff for the important words. It is coded to minimum frequency 4
            // Create a word and frequency list only if the word is not a common word listed above

            for (String keyword: bodycontent){
                Integer n = map.get(keyword);
                n = (n == null) ? 1: ++n;
                if (!Arrays.asList(cmnWords).contains(keyword)){map.put(keyword,n);}
            }
            //Print the frequenctly used word criteria frequency>3, common words have been removed
            System.out.println("Most important keywords :");
            for (Object variableName: map.keySet()){
                if (map.get(variableName) > 3){
                    System.out.println(variableName +" : " + map.get(variableName));
                }
            }
            /**************************************************************************************************/

            // Isolate the body in paragraphs so that we can find the most important para
            System.out.println("Original Article:" + "\n");
            int i = 0;
            for (i=0; i<paras.select("p").size();i++) {
                System.out.println(paras.select("p").get(i).text());
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        return title;
    }

    public static void main(String [ ] args) throws IOException {
        //System.out.println(httpGet("http://www.nytimes.com"));
        httpGet("https://www.washingtonpost.com/world/hunt-for-cell-behind-islamic-state-attacks-extends-across-europe/2016/03/27/59acfc5a-f39a-11e5-a2a3-d4e9697917d1_story.html?hpid=hp_hp-top-table-main_brussels-0714am%3Ahomepage%2Fstory");
    }
}