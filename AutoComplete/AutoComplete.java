import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class AutoComplete
{
  Trie trie = new Trie();
  File file;
  Scanner scan;

  public AutoComplete(String fileName)
  {
    try
    {
      file = new File(fileName);
      scan = new Scanner(file);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }

    while(scan.hasNext())
    {
      String word = scan.next();
      if(word.charAt(0)>=97&&word.charAt(0)<=122)
      {
        String newWord = word.toLowerCase();
        trie.insert(newWord);
      }
    }
  }

  /*public void autoComplete(String test)
  {
    for(String key : trie.keysWithPrefix(test))
    {
      String returnStr = trie.get(key);
      System.out.println(returnStr);
    }
  }*/

  public Trie getTrie()
  {
    return trie;
  }

  public static void main(String[] args)
  {
    AutoComplete test1 = new AutoComplete("politician_words.txt");
    AutoComplete test2 = new AutoComplete("words.txt");
    Trie trie1 = test1.getTrie();
    Trie trie2 = test2.getTrie();
    System.out.println("Enter a prefix: ");
    Scanner scan = new Scanner(System.in);
    String input = scan.next();
    trie1.getWordList(input, new Node(), true);
    trie2.getWordList(input, new Node(), true);
    ArrayList<String> list1 = trie1.getList();
    ArrayList<String> list2 = trie2.getList();

    if(list1.size()!=0)
    {
      System.out.println();
      System.out.println("Matching words for "+input+" in politician_words.txt: ");
      for(int i =0;i<list1.size();i++)
      {
        System.out.println(list1.get(i));
      }
    }
    else
    {
      System.out.println();
      System.out.println("politician_words.txt doesn't contain that prefix!");
    }
    if(list2.size()!=0)
    {
      System.out.println();
      System.out.println("Matching words for "+input+" in words.txt: ");
      for(int i =0;i<list2.size();i++)
      {
        System.out.println(list2.get(i));
      }
    }
    else
    {
      System.out.println();
      System.out.println("words.txt doesn't contain that prefix!");
    }
  }
}
