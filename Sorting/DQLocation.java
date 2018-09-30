/** class to establish DQLocation variable*/
public class DQLocation implements Comparable<DQLocation>
{
  double distance;
  double longitude;
  double latitude;

/** Constructor uses gps coordinates to find the distance from tutt for each of the DQLocations
* @param lat latitude
* @param lon longitude
*/
  public DQLocation(double lat, double lon)
  {
    this.latitude = lat;
    this.longitude = lon;
//formula taken from https://andrew.hedges.name/experiments/haversine/
    double dlon = Math.toRadians(-104.822077 - longitude);
    double dlat = Math.toRadians(38.849675 - latitude);
    double a = Math.sin(dlat/2)*Math.sin(dlat/2) + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(38.849675)) * Math.sin(dlon/2)*Math.sin(dlon/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    double d = 3961 * c;

    distance = d;
  }

/** getter for distance
* @return distance
*/
  public double getDistance()
  {
    return distance;
  }

  /** getter for latitude
  * @return latitude
  */
  public double getLatitude()
  {
    return latitude;
  }

  /** getter for longitude
  * @return longitude
  */
  public double getLongitude()
  {
    return longitude;
  }

/** method that implements Comparable; compares two objects of same type
* @param object object to compare
* @return -1 if subject is closer to Tutt science, 0 if same distance from Tutt, 1 if subject is further from Tutt
*/
  public int compareTo(DQLocation object)
  {
    int returnValue = 0;
    double dist = object.getDistance();

    if(distance>dist)
    {
      returnValue = 1;
    }
    else if(distance<dist)
    {
      returnValue = -1;
    }

    return returnValue;
  }
}
