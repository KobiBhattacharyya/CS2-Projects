/** class to establish Word object*/
public class Word implements Comparable<Word>
{
  String subj;

/** constructor initializes subj
* @param incomingWord word from shuffled text file
*/
  public Word(String incomingWord)
  {
    subj = incomingWord;
  }
/** getter method for word
* @return word
*/
  public String getWord()
  {
    return subj;
  }

  /** method that implements Comparable; compares two objects of same type
  * @param otherWord object to compare
  * @return -1 if subject alphabetically comes before otherWord, 0 if same word, 1 if subject alphabetically comes after object
  */
  public int compareTo(Word otherWord)
  {
    return subj.compareTo(otherWord.getWord());
  }
  /* This method to overwrite compareTo for strings, not needed!!
  public int compareTo(Word obj)
  {
    int returnValue=0;
    int len1 = subj.length();
    int len2 = obj.getWord().length();

    if(len1<len2)
    {
      for(int i=0;i<len1;i++)
      {
        if(subj.charAt(i)<obj.getWord().charAt(i))
        {
          returnValue = -1;
          break;
        }
        else if(subj.charAt(i)>obj.getWord().charAt(i))
        {
          returnValue = 1;
          break;
        }
      }
      if(returnValue==0)
      {
        returnValue = -1;
      }
    }
    else if(len2<len1)
    {
      for(int i=0;i<len2;i++)
      {
        if(subj.charAt(i)<obj.getWord().charAt(i))
        {
          returnValue = -1;
          break;
        }
        else if(subj.charAt(i)>obj.getWord().charAt(i))
        {
          returnValue = 1;
          break;
        }
      }
      if(returnValue==0)
      {
        returnValue = 1;
      }
    }
    else
    {
      for(int i=0;i<len1;i++)
      {
        if(subj.charAt(i)<obj.getWord().charAt(i))
        {
          returnValue = -1;
          break;
        }
        else if(subj.charAt(i)>obj.getWord().charAt(i))
        {
          returnValue = 1;
          break;
        }
      }
    }
    return returnValue;
  }*/
}
