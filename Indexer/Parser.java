import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
/**
 * A rather hard coded way to parse the website WildAnimalsOnline. also takes for ever because I am dumb
 *
 * @author Drew Shippey
 * @version 5/9/18
 *
 */
public class Parser
{
    PrintWriter writer;
    Parser() throws IOException{
        String url = "http://www.wildanimalsonline.com/";
        Document doc = Jsoup.connect(url).get();
        int height = 0;
        ArrayList<String> types = new ArrayList<String>();
        writer = new PrintWriter("DataBase.txt", "UTF-8");
        types.add("insect");
        types.add("fish");
        types.add("amphibians");
        types.add("reptiles");
        types.add("birds");
        types.add("mammals");
        for (String t: types) {
               getAnimals(url+t+"/");
               //break;
        }
        writer.close();

    }
    public void getAnimals(String url) throws IOException{
        Document animal = Jsoup.connect(url).get();
        Elements animals = animal.getElementsByClass("sl");
        String temp;
        for(Element a : animals){
            Document type = Jsoup.connect(url + a.attr("href")).get();
            Elements info = type.getElementsByTag("tbody");
            String[] array = info.get(2).text().split("//s+");
            writer.print(a.attr("href")+ " ");
            temp ="";
            for(int i =0;i<info.get(2).text().length();i++){
                if(info.get(2).text().charAt(i) != '(' && info.get(2).text().charAt(i) != ')' && info.get(2).text().charAt(i) != '-'){
                    temp += (info.get(2).text().charAt(i));
                }    
            }
            writer.print(temp);
            writer.println();
            System.out.println();
            //System.out.println(a.attr("href")+ " " +info.get(2).text());
            //break;
        }

    }

}
