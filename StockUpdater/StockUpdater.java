import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;

/** driver class */
public class StockUpdater
{
  Scanner scan, updateScan, updateScan2;
  File file, updateFile;
  ArrayList<Stock> stockList = new ArrayList<Stock>();
  TreeMap<String,Stock> stockTree = new TreeMap<String,Stock>();
  int counter = 0;

/** constructor takes 2 files and initializes arraylist and treemap
* @param fileName file "shuffled_stocks.csv"
* @param fileName2 file "price_updates.txt"
*/
  public StockUpdater(String fileName,String fileName2)
  {
    try
    {
      file = new File(fileName);
      scan = new Scanner(file);
      scan.useDelimiter(",|\\n");
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }
    try
    {
      updateFile = new File(fileName2);
      updateScan = new Scanner(updateFile);
      updateScan2 = new Scanner(updateFile);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }

    while(scan.hasNextLine())
    {
      String ticker = scan.next();
      double price = scan.nextDouble();

      Stock newStock1 = new Stock(ticker, price);
      Stock newStock2 = new Stock(ticker, price);
      stockList.add(newStock1);
      stockTree.put(ticker,newStock2);

      scan.nextLine();
    }
  }

/** method to update the arraylist of stocks */
  public void updateList()
  {
    while(updateScan.hasNextLine())
    {
      String tickerSearch = updateScan.next();
      double priceUpdate = updateScan.nextDouble();
      for(int i =0;i<stockList.size();i++)
      {
        Stock search = stockList.get(i);
        if(search.getTicker().equals(tickerSearch))
        {
          search.updatePrice(priceUpdate);
          break;
        }
      }

      updateScan.nextLine();
    }
  }

/** method to update the treemap of stocks */
  public void updateTree()
  {
    while(updateScan2.hasNextLine())
    {
      String tickerSearch = updateScan2.next();
      double priceUpdate = updateScan2.nextDouble();

      Stock stock = stockTree.get(tickerSearch);
      stock.updatePrice(priceUpdate);

      updateScan2.nextLine();
    }
  }

/** main method
* @param args unused
*/
  public static void main(String[] args)
  {
    StockUpdater newUpdater = new StockUpdater("shuffled_stocks.csv","price_updates.txt");

    long startTime1 = System.currentTimeMillis();
    newUpdater.updateList();
    long endTime1 = System.currentTimeMillis();
    long totalTime1 = endTime1-startTime1;
    System.out.println("ArrayList update time: "+totalTime1);
    //ArrayList runtime: ~3.6s

    long startTime2 = System.currentTimeMillis();
    newUpdater.updateTree();
    long endTime2 = System.currentTimeMillis();
    long totalTime2 = endTime2-startTime2;
    System.out.println("Tree update time: "+totalTime2);
    //Tree runtime: ~0.4s
  }
}
