import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/** class for implementing Digraph data structure*/
public class PageRank
{
  Digraph graph;
  File file;
  Scanner scan1,scan;
  HashMap<Integer,Double> data = new HashMap<Integer,Double>();
  double damping = 0.85;

/** constructor for Grapher takes in file name and initializes digraph connections and hashmap key and object values
* @param fileName name of input file
*/
  public PageRank(String fileName)
  {
    //import text file
    try
    {
      file = new File(fileName);
      scan1 = new Scanner(file);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }
//check number of nodes for digraph
    String line1 = scan1.nextLine();
    Scanner scan2 = new Scanner(line1);
    int counter = 0;
    while(scan2.hasNextInt())
    {
      counter++;
      scan2.nextInt();
    }
    graph = new Digraph(counter);

//initialize hashmap to make each key 1/N
    for(int i =0;i<counter;i++)
    {
      data.put(i,1/(double)counter);
    }

    try
    {
      scan = new Scanner(file);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }

//initialize edges of digraph
    scan.useDelimiter("\\n");
    for(int positionY=0;positionY<counter;positionY++)
    {
      String line = scan.next();
      Scanner item = new Scanner(line);

      for(int positionX =0;positionX<counter;positionX++)
      {
        int link = item.nextInt();
        if(link==1)
        {
          graph.addEdge(positionY,positionX);
        }
      }
    }
  }

/** method to assign each node a PageRank and assigns rank to HashMap */
  public void rank()
  {
    Digraph reverse = graph.reverse(); //digraph to retrieve connections to specific node, not from
    HashMap<Integer,Double> oldData = new HashMap<Integer,Double>();//hashmap that is set to one iteration prior to newly updated hashmap with PageRank values
//initializes oldData
    for(int i=0;i<graph.V();i++)
    {
      oldData.put(i,1/(double)graph.V());
    }
    boolean fullEnd = false;
//goes through full loop of setting PageRank values
    while(!fullEnd) //while difference in PageRank values are greater than 5%, continue iterating
    {
      int sEnd =0;
//sets oldData to updated hashmaps values
      for(int i=0;i<graph.V();i++)
      {
        oldData.replace(i,data.get(i));
      }
//goes through each point in digraph and updates the PageRank values
      for(int i=0;i<graph.V();i++)
      {
        double  incomingSum = 0;
        for(int point : reverse.adj(i))
        {
          incomingSum = incomingSum + (data.get(point))/(graph.outdegree(point));
        }
        double pageRank = ((1 - damping)/(double)graph.V()) + damping*incomingSum;
        data.replace(i,pageRank);
      }
      //if the values in oldData are still greater than 5% of abs val of updated data then keep iterating
      for(int i=0;i<graph.V();i++)
      {
        double diff = Math.abs(data.get(i)-oldData.get(i));
        float threshold = 0.05f*(1/(float)graph.V()); //gives 5% of initialized value
        System.out.println("Difference for point "+i+": "+diff);
        if(diff<threshold)
        {
          sEnd++;
        }
      }
      if(sEnd>=graph.V())
      {
        fullEnd = true;
      }
    }
  }

/** getter for hashmap
* @return map with PageRank values
*/
  public HashMap<Integer,Double> getMap()
  {
    return data;
  }

/** main method for testing algorithm
* @param args unused
*/
  public static void main(String[] args)
  {
    PageRank graph = new PageRank("bigger_graph.txt");
    graph.rank();
    HashMap<Integer,Double> map = graph.getMap();

    double addSum = 0;
    for(int i=0;i<map.size();i++)
    {
      addSum = addSum + map.get(i);
      System.out.println("PageRank for page "+i+": "+map.get(i));
    }
    System.out.println("Sum of all PageRanks: "+addSum);
  }
}
