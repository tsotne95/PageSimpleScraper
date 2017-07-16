import org.jsoup.nodes.Element;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;

/**
 * Created by tsotne on 7/16/17.
 */
public class Main {
    public static void main(String[] args) {
        //Page URL
        Parser parser=new Parser("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR0.TRC0.H0.Xsamsung.TRS0&_nkw=samsung&_sacat=0");
        parser.connect();
        HashSet<Element> set=parser.getImages();

        //Create files for images and for links:
        createImagesFile("images.txt",set);

        set=parser.getLinks();
        createLinksFile("links.txt",set);

    }
    public static void createImagesFile(String filepath,HashSet<Element> set)
    {
        Path p=Paths.get(filepath);
        try {
            Files.write(p,("#\tURL\tWidth\tHeight\tAlt"+System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i=1;
        for(Element img:set)
            try {
                Files.write(p,(i+"\t"+img.attr("abs:src")+"\t"+img.attr("width")+"\t"+img.attr("height")+"\t"+img.attr("alt")+System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void createLinksFile(String filepath,HashSet<Element> set)
    {
        Path p=Paths.get(filepath);
        try {
            Files.write(p,("#\tURL\tText"+System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i=1;
        for(Element link:set)
            try {
                Files.write(p,(i+"\t"+link.attr("abs:href")+"\t"+link.text()+System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
