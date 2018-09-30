public class DiningParty implements Comparable<DiningParty>
{
  private String name = null;
  private int arrivalTime = 0;
  private int diningTime = 0;
  private int partySize = 0;
  private int priority = 0;
  private int waitTime = 0;

  /** constructor initializes values from text file
  * @param name party's name
  * @param arrivalTime time party is added to queue
  * @param diningTime amount of time required for party to eat
  * @param partySize size of party
  * @param priority numerical value that defines how close to front of queue party gets
  */
  public DiningParty(String name, int arrivalTime, int diningTime, int partySize, int priority)
  {
    this.name = name;
    this.arrivalTime = arrivalTime;
    this.diningTime = diningTime;
    this.partySize = partySize;
    this.priority = priority;
  }

/** getter for name
* @return party name
*/
  public String getName()
  {
    return name;
  }

/** increments wait time */
  public void addWait()
  {
    waitTime++;
  }

  /** getter for party's waiting time
  * @return wait time
  */
  public int getWaitTime()
  {
    return waitTime;
  }

  /** getter for name
  * @return party name
  */
  public int getPriority()
  {
    return priority;
  }
  /** getter for arrival time
  * @return party's arrival time
  */
  public int getArrivalTime()
  {
    return arrivalTime;
  }

  /** getter for party size
  * @return amount of people in party
  */
  public int getPartySize()
  {
    return partySize;
  }

  /** decreases amount of time in dining time
  */
  public void eat()
  {
    diningTime--;
  }

  /** getter for dining time
  * @return party's dining time
  */
  public int getDiningTime()
  {
    return diningTime;
  }

/** method from Comparable interface that compares two objects of type comparable
* @param other second object to compare
* @return 1 if subject's priority is lower than the object's, 0 if same priority, -1 if subject's priority is higher than the objects's
*/
  public int compareTo(DiningParty other)
  {
    int otherPriority = other.getPriority();
    int finalValue=0;
    if(priority<otherPriority)
    {
      finalValue = 1;
    }
    if(priority>otherPriority)
    {
      finalValue = -1;
    }

    return finalValue;
  }
}
