import java.text.*;

/** class to create stock object */
class Stock implements Comparable<Stock> {

  private String ticker = null;
  private double price = 0.0;
  DecimalFormat df = new DecimalFormat("#.##");

/** constructor initializes ticker and price
* @param tickerSymbol ticker's string name
* @param startingPrice price of stock
*/
  public Stock(String tickerSymbol, double startingPrice)
  {
    this.ticker = tickerSymbol;
    this.price = startingPrice;
  }

/** getter for price
* @return price of stock
*/
  public double getPrice()
  {
    return price;
  }

  /** getter for ticker
  * @return name of stock
  */
  public String getTicker()
  {
    return ticker;
  }

/** method to update price of stock
* @param change price change as instantiated by price_updates.txt
*/
  public void updatePrice(double change)
  {
    price = price + change;

    double newPrice = Double.parseDouble(df.format(price));
    price = newPrice;
  }

/** method for implementation of Comparable
* @param other item to compare
* @return 1 if stock ticker comes after other's ticker name, 0 if same name, -1 if ticker comes before other ticker's name
*/
  public int compareTo(Stock other)
  {
    int returnValue = 0;

    if(ticker.compareTo(other.getTicker())<0)
    {
      returnValue =-1;
    }
    else if(ticker.compareTo(other.getTicker())>0)
    {
      returnValue = 1;
    }
    else
    {
      returnValue = 0;
    }

    return returnValue;
  }
}
