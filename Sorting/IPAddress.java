/** class to establish IPAddress object*/
public class IPAddress implements Comparable<IPAddress>
{
  String ip;

/** constructor initializes IPAddress
* @param ip String for ip address
*/
  public IPAddress(String ip)
  {
    this.ip = ip;
  }
  /** getter for ip
  * @return ip address string
  */
  public String getIP()
  {
    return ip;
  }

  /** method that implements Comparable; compares two objects of same type
  * @param object object to compare
  * @return -1 if subject smaller than object, 0 if same number, 1 if subject is larger than object
  */
  public int compareTo(IPAddress object)
  {
    String newIP = object.getIP();
    int returnValue =0;

    //this method goes through each number in the ip, stopping at "."
    //first numbers (number before first ".") take priority... if smaller, they are placed before in list
    int objectEnd1 = newIP.indexOf(".");
    String objectCompare1 = newIP.substring(0,objectEnd1);
    int objectSort1 = Integer.parseInt(objectCompare1);
    int subjectEnd1 = ip.indexOf(".");
    String subjectCompare1 = ip.substring(0,subjectEnd1);
    int subjectSort1 = Integer.parseInt(subjectCompare1);

    if(subjectSort1<objectSort1)
    {
      returnValue = -1;
    }
    else if(subjectSort1>objectSort1)
    {
      returnValue = 1;
    }
    else
    {
      int subjectEnd2 = ip.indexOf(".",subjectEnd1+1);
      String subjectCompare2 = ip.substring(subjectEnd1+1,subjectEnd2);
      int subjectSort2 = Integer.parseInt(subjectCompare2);
      int objectEnd2 = newIP.indexOf(".",objectEnd1+1);
      String objectCompare2 = newIP.substring(objectEnd1+1,objectEnd2);
      int objectSort2 = Integer.parseInt(objectCompare2);
      if(subjectSort2<objectSort2)
      {
        returnValue = -1;
      }
      else if(subjectSort2>objectSort2)
      {
        returnValue = 1;
      }
      else
      {
        int subjectEnd3 = ip.indexOf(".",subjectEnd2+1);
        String subjectCompare3 = ip.substring(subjectEnd2+1,subjectEnd3);
        int subjectSort3 = Integer.parseInt(subjectCompare3);
        int objectEnd3 = newIP.indexOf(".",objectEnd2+1);
        String objectCompare3 = newIP.substring(objectEnd2+1,objectEnd3);
        int objectSort3 = Integer.parseInt(objectCompare3);
        if(subjectSort3<objectSort3)
        {
          returnValue = -1;
        }
        else if(subjectSort3>objectSort3)
        {
          returnValue = 1;
        }
        else
        {
          //since there's only 4 numbers in the ip, we move from subjectEnd3+1 to the string length
          String subjectCompare4 = ip.substring(subjectEnd3+1,ip.length());
          int subjectSort4 = Integer.parseInt(subjectCompare4);
          String objectCompare4 = newIP.substring(objectEnd3+1,newIP.length());
          int objectSort4 = Integer.parseInt(objectCompare4);
          if(subjectSort4<objectSort4)
          {
            returnValue = -1;
          }
          else
          {
            returnValue = 1;
          }
        }
      }
    }
    return returnValue;
  }
}
