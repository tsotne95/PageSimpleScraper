import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by tsotne on 7/16/17.
 */
public class Parser {
    private Document doc;
    private String url;
    private boolean urlValidate(String url)
    {
        UrlValidator urlVal=new UrlValidator();
        return urlVal.isValid(url);
    }
    public Parser(String url) throws IllegalArgumentException
    {
        if(urlValidate(url)!=true)
            throw new IllegalArgumentException("URL is not correct");
        this.url=url;
    }
    public void connect()
    {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashSet<Element> getImages()
    {
        Elements images=doc.getElementsByTag("img");
        return new HashSet<Element>(images);
    }
    public HashSet<Element> getLinks()
    {
        Elements links=doc.select("a[href]");
        return new HashSet<Element>(links);
    }
}
