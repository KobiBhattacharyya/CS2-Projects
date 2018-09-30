import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//WITH THIS METHOD OF THE SIMULATOR, IF A PERSON SITS AT THE TABLE, THEY FINISH THEIR FOOD
//BEORE THE NEXT PERSON IN THE QUEUE IS ALLOWED TO EAT
public class RestaurantSimulator
{
  public static void main(String[] args)
  {
    PriorityQueue<DiningParty> tableForFour = new PriorityQueue<DiningParty>();
    PriorityQueue<DiningParty> tableForSix = new PriorityQueue<DiningParty>();
    PriorityQueue<DiningParty> tableForEight = new PriorityQueue<DiningParty>();
    ArrayList<DiningParty> partyList = new ArrayList<DiningParty>();
    int totalTime=0;
    int listSize = 0;

    File file=null;
    Scanner scan = null;
    try
    {
      file = new File("sorted_customers.txt");
      scan = new Scanner(file);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }

    while(scan.hasNextLine())
    {
      String name = scan.next()+" "+scan.next();
      int arrival = scan.nextInt();
      int dineTime = scan.nextInt();
      int partySize = scan.nextInt();
      int priority = scan.nextInt();
      DiningParty sample = new DiningParty(name, arrival, dineTime, partySize, priority);
      partyList.add(sample);
      scan.nextLine();
    }

/***********FULL SIMULATION*************/
    DiningParty atTable1 = null;
    DiningParty atTable2 = null;
    DiningParty atTable3 = null;
    int listCounter = 0;
    while(listCounter<1000||tableForFour.peek()!=null||tableForSix.peek()!=null||tableForEight.peek()!=null)
    {
      //looks through the partyList queue and checks if the guests arrival time is equal to
      //the actual time, if so adds them to respective priorityqueue
      if(listCounter<1000)
      {
        while(partyList.get(listCounter).getArrivalTime() == totalTime)
        {
          DiningParty nextParty = partyList.get(listCounter);
          listCounter++;
          if(nextParty.getPartySize()<=4)
          {
            tableForFour.add(nextParty);
          }
          else if(nextParty.getPartySize()<=6 && nextParty.getPartySize()>4)
          {
            tableForSix.add(nextParty);
          }
          else
          {
            tableForEight.add(nextParty);
          }
          if(listCounter==1000)
          {
            break;
          }
        }
      }

      //goes through each tables queue and removes time from head party's diningTime or removes
      //head party if their diningTime is 0
      if(tableForFour.size()!=0)
      {
        if(atTable1==null)
        {
          atTable1 = tableForFour.poll();
        }
        if(atTable1.getDiningTime()==0)
        {
          atTable1 = tableForFour.poll();
        }
        else
        {
          atTable1.eat();
        }
      }
      if(tableForSix.size()!=0)
      {
        if(atTable2==null)
        {
          atTable2 = tableForSix.poll();
        }
        if(atTable2.getDiningTime()==0)
        {
          atTable2 = tableForSix.poll();
        }
        else
        {
          atTable2.eat();
        }
      }
      if(tableForEight.size()!=0)
      {
        if(atTable3==null)
        {
          atTable3 = tableForEight.poll();
        }
        if(atTable3.getDiningTime()==0)
        {
          atTable3 = tableForEight.poll();
        }
        else
        {
          atTable3.eat();
        }
      }
/*

      THIS METHOD USED IF YOU WANT TO INTERUPT PARTYS DINNER FOR A PARTY WITH HIGHER PRIORITY
      if(tableForFour.size()!=0)
      {
        if(tableForFour.peek().getDiningTime()==0)
        {
          String n = tableForFour.poll().getName();
          System.out.println(n);
        }
        else
        {
          tableForFour.peek().eat();
        }
      }
      if(tableForSix.size()!=0)
      {
        if(tableForSix.peek().getDiningTime()==0)
        {
          String n = tableForSix.poll().getName();
          System.out.println(n);
        }
        else
        {
          tableForSix.peek().eat();
        }
      }
      if(tableForEight.size()!=0)
      {
        if(tableForEight.peek().getDiningTime()==0)
        {
          String n = tableForEight.poll().getName();
          System.out.println(n);
        }
        else
        {
          tableForEight.peek().eat();
        }
      }*/

      //increments time of total simulation and wait time for each individual party in a queue
      for(DiningParty party : tableForFour)
      {
        party.addWait();
      }
      for(DiningParty party : tableForSix)
      {
        party.addWait();
      }
      for(DiningParty party : tableForEight)
      {
        party.addWait();
      }
      totalTime++;
    }
/**********END OF SIMULATION**********/
    double average=0;
    for(int i=0;i<partyList.size();i++)
    {
      double wait = (double) partyList.get(i).getWaitTime();
      average = average+wait;
    }
    average = average/1000.0;
    System.out.println("Average waiting time: "+average);
  }
}
