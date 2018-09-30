import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/** class that indexes words in text file from html parser
*/
public class Indexer
{
  HashMap<String, ArrayList<QueryResult>> map = new HashMap<String, ArrayList<QueryResult>>();
  Scanner scan;
  File file;

/** constructor takes in text file and assigns each word to an index in hashmap
* @param fileName path name for text file
*/
  public Indexer(String fileName)
  {
    try
    {
      file = new File(fileName);
      scan = new Scanner(file);
      scan.useDelimiter("\\n");
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }

    while(scan.hasNext())
    {
      String line = scan.next().toLowerCase(); //gets line
      String[] wordList = line.split(" "); //splits line into tokens
      String htmlName = wordList[0];

      for(int i = 1;i<wordList.length;i++)
      {
        String word = wordList[i];
        String snippet = null;

        if(i==1||i==2)
        {
          snippet = wordList[i]+" "+wordList[i+1]+" "+wordList[i+2];
        }
        else if(i==wordList.length-1||i==wordList.length-2)
        {
          snippet = wordList[i-2]+" "+wordList[i-1]+" "+wordList[i];
        }
        else
        {
          snippet =  wordList[i-2]+" "+wordList[i-1]+" "+wordList[i]+" "+wordList[i+1]+" "+wordList[i+2];
        }

        if(!map.containsKey(word))
        {
          QueryResult newItem = new QueryResult(word,htmlName,snippet);
          ArrayList<QueryResult> newList = new ArrayList<QueryResult>();
          newList.add(newItem);
          map.put(word,newList);
        }
        else
        {
          ArrayList<QueryResult> findList = map.get(word);
          QueryResult newItem = new QueryResult(word,htmlName,snippet);
          findList.add(newItem);
        }
      }
    }
  }

/** getter for HashMap
* @return hashmap with indexed results
*/
  public HashMap<String, ArrayList<QueryResult>> getMap()
  {
    return map;
  }

/** main method
* @param args unused
*/
  public static void main(String[] args)
  {
    Indexer test = new Indexer("DataBase.txt");
    System.out.println("Enter a word to search for: ");
    Scanner scan = new Scanner(System.in);
    String input = scan.next();
    HashMap<String, ArrayList<QueryResult>> map = test.getMap();

    if(!map.containsKey(input))
    {
      System.out.println(input+" never appears!");
    }
    else
    {
      ArrayList<QueryResult> list = map.get(input);
      for(int i = 0;i<list.size();i++)
      {
        System.out.println((i+1)+". "+list.get(i).getWord()+", "+list.get(i).getHTML());
        System.out.println("Word in context: ..."+list.get(i).getSnippet()+"... ");
      }
    }
  }
}
