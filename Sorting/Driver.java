import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/** Class to run each sorting algorithm*/
public class Driver
{
  /** main method
  * @param args unused
  * @throws FileNotFoundException if file does not exist
  * @throws UnsupportedEncodingException if file cannot be written to
  */
  public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
  {
    /************************
    Word
    ************************/
    Scanner scan1 = null;
    File file1 = null;
    InsertionSort sorter = new InsertionSort();
    QuickSort idkwhattocallthis = new QuickSort();
    Word[] list1 = new Word[63788];
    Word[] newList = new Word[63788];
    PrintWriter writer1 = null;
    PrintWriter writer2 = null;
    try
    {
      writer1 = new PrintWriter("WordQuicksort.txt", "UTF-8");
      writer2 = new PrintWriter("WordInsertionsort.txt", "UTF-8");
    }
    catch(UnsupportedEncodingException e)
    {
      System.out.println("idk what this error is lol");
    }
    try
    {
      file1 = new File("words.txt");
      scan1 = new Scanner(file1);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }
    //adds elements to both lists
    int counter = 0;
    while(scan1.hasNextLine())
    {
      Word newWord = new Word(scan1.nextLine());
      list1[counter] = newWord;
      newList[counter] = newWord;
      counter++;
    }
    //sorts and times list using quicksort
    long startTime5 = System.currentTimeMillis();
    idkwhattocallthis.quickSort(newList);
    long endTime5 = System.currentTimeMillis();
    long totalTime5 = endTime5-startTime5;
    //writes to new text file
    writer1.println("Word - QuickSort:");
    for(int i=0;i<newList.length;i++)
    {
      writer1.println(newList[i].getWord());
    }
    writer1.close(); //REMEMBER TO CLOSE BUFFERED WRITER AFTER YOU FINISH WRITING TO IT!!!

    System.out.println("Word - Quicksort Time (ms): "+totalTime5);

    //sorts and times list using insertionsort
    long startTime1 = System.currentTimeMillis();
    sorter.sort(list1);
    long endTime1 = System.currentTimeMillis();
    long totalTime1 = endTime1-startTime1;
    //writes to new text file
    writer2.println("Word - InsertionSort:");
    for(int i=0;i<list1.length;i++)
    {
      writer2.println(list1[i].getWord());
    }
    writer2.close();

    System.out.println("Word - InsertionSort time (ms): "+totalTime1);

    /************************
    IPAddress
    ************************/
    IPAddress[] list2 = new IPAddress[70865];
    IPAddress[] newList2 = new IPAddress[70865];
    PrintWriter writer3 = null;
    PrintWriter writer4 = null;
    File file2 = null;
    Scanner scan2 = null;
    try
    {
      writer3 = new PrintWriter("IPAddressQuicksort.txt", "UTF-8");
      writer4 = new PrintWriter("IPAddressInsertionsort.txt", "UTF-8");
    }
    catch(UnsupportedEncodingException e)
    {
      System.out.println("idk what this error is lol");
    }
    try
    {
      file2 = new File("shuffled_ips.csv");
      scan2 = new Scanner(file2);
      scan2.useDelimiter(",");
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }

    //adds elements to list, after picking out necessary information (just IP addresses)
    int counter2=0;
    while(scan2.hasNextLine())
    {
      String part = scan2.next();
      String newIP = part.substring(1,part.length()-1);
      IPAddress item = new IPAddress(newIP);
      list2[counter2] = item;
      newList2[counter2] = item;
      counter2++;
      scan2.nextLine();
    }

    //sorts and times list using quicksort
    QuickSort idkwhattocallthis2 = new QuickSort();
    long startTime6 = System.currentTimeMillis();
    idkwhattocallthis2.quickSort(newList2);
    long endTime6 = System.currentTimeMillis();
    long totalTime6 = endTime6-startTime6;
    //writes to new txt file
    writer3.println("IPAddress - QuickSort:");
    for(int i=0;i<newList2.length;i++)
    {
      writer3.println(newList2[i].getIP());
    }
    writer3.close();

    System.out.println("IPAddress - Quicksort Time (ms): "+totalTime6);
    //sorts and times list using insertionsort
    InsertionSort sorter2 = new InsertionSort();
    long startTime2 = System.currentTimeMillis();
    sorter2.sort(list2);
    long endTime2 = System.currentTimeMillis();
    long totalTime2 = endTime2-startTime2;
    //writes to new txt file
    writer4.println("IPAddress - InsertionSort:");
    for(int i=0;i<list2.length;i++)
    {
      writer4.println(list2[i].getIP());
    }
    writer4.close();
    //prints runtime to console
    System.out.println("IPAddress - Insertionsort Time (ms): "+totalTime2);

    /************************
    DQLocation
    ************************/
    DQLocation[] list3 = new DQLocation[4284]; //CSV FILE HAS LONGITUDE THEN LATITUDE
    //TUTT SCIENCE LOCATION: (38.849675, -104.822077)(latitude,longitude)
    DQLocation[] list4 = new DQLocation[4284];
    File file3 = null;
    Scanner scan3 = null;
    try
    {
      file3 = new File("shuffled_dqs.csv");
      scan3 = new Scanner(file3);
      scan3.useDelimiter(",");
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }
    //adds elements to list, after picking out and parsing each element in the csv file
    int counter3 = 0;
    while(scan3.hasNextLine())
    {
      String longitude1 = scan3.next();
      String latitude1 = scan3.next();
      double lon1 = Double.parseDouble(longitude1);
      double lat1 = Double.parseDouble(latitude1);
      DQLocation newLoc = new DQLocation(lat1,lon1);
      list3[counter3] = newLoc;
      list4[counter3] = newLoc;
      counter3++;
      scan3.nextLine();
    }

    QuickSort quicksort = new QuickSort();
    InsertionSort insertsort = new InsertionSort();
    PrintWriter writer5 = null;
    PrintWriter writer6 = null;
    try
    {
      writer5 = new PrintWriter("DQLocationQuicksort.txt", "UTF-8");
      writer6 = new PrintWriter("DQLocationInsertionsort.txt", "UTF-8");
    }
    catch(UnsupportedEncodingException e)
    {
      System.out.println("idk what this error is lol");
    }
    //sorts and times list using insertionsort
    long startTime4 = System.currentTimeMillis();
    insertsort.sort(list4);
    long endTime4 = System.currentTimeMillis();
    long totalTime4 = endTime4-startTime4;
    //writes to new txt file
    writer5.println("DQLocation - QuickSort (latitude,longitude):");
    for(int i=0;i<list4.length;i++)
    {
      writer5.println("("+list4[i].getLatitude()+", "+list4[i].getLongitude()+")");
    }
    writer5.close();
    //sorts and times list using quicksort
    long startTime3 = System.currentTimeMillis();
    quicksort.quickSort(list3);
    long endTime3 = System.currentTimeMillis();
    long totalTime3 = endTime3-startTime3;
    //writes to new txt file
    writer6.println("DQLocation - InsertionSort (latitude,longitude):");
    for(int i=0;i<list3.length;i++)
    {
      writer6.println("("+list3[i].getLatitude()+", "+list3[i].getLongitude()+")");
    }
    writer6.close();

    System.out.println("Quicksort time DQLocation (ms): "+totalTime3);
    System.out.println("InsertionSort time DQLocation (ms): "+totalTime4);
  }
}
