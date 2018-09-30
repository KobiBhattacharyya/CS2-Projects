import java.util.ArrayList;

/** class creates trie
*/
public class Trie
{
  Node root = new Node();
  ArrayList<String> completeList = new ArrayList<String>();

/** method to add specific word to the trie
* @param word word to be added to trie
*/
  public void insert(String word)
  {
    Node currNode = root;
    for(int i =0;i<word.length();i++)
    {
      int index = word.charAt(i)-'a';
      Node[] arr = currNode.getChildren();
      if(arr[index] == null)
      {
        Node newNode = new Node();
        if(i==word.length()-1)
        {
          newNode.setEnd();
        }
        arr[index] = newNode;
        currNode = newNode;
      }
      else
      {
        currNode = arr[index];
      }
    }
  }

/** method to recursively traverse trie to get to desired node
* @param curr current node
* @param tPoint prefix string to traverse up to
* @param counter integer to count up how many times method has repeated
* @return Node at which tPoint is located
*/
  public Node traverse(Node curr, String tPoint, int counter)
  {
    if(curr ==null)
    {
      return null;
    }
    if(counter==tPoint.length())
    {
      return curr;
    }

    /*if(counter == 0)
    {
      curr = root;
    }*/
    int index = tPoint.charAt(counter)-'a';
    Node[] arr = curr.getChildren();
    return traverse(arr[index],tPoint,counter+1);
  }

/** method to recursively add words to the ArrayList of words
* @param prefix prefix which the trie should initially traverse up to
* @param curr current node
* @param start true if method has just started
*/
  public void getWordList(String prefix, Node curr, boolean start)
  {
    if(start)
    {
      curr = traverse(root,prefix,0);
    }
    if(curr == null)
    {
      return;
    }

    if(curr.isEndString())
    {
      completeList.add(prefix);
    }
    for(int i =0;i<26;i++)
    {
      Node[] children = curr.getChildren();
      if(children[i] != null)
      {
        int a = 'a';
        char place = (char)(i+a);
        prefix = prefix + Character.toString(place);
        getWordList(prefix,children[i],false);
        prefix = prefix.substring(0,prefix.length()-1);
      }
    }
  }

/** getter for word list
* @return word list*/
  public ArrayList<String> getList()
  {
    return completeList;
  }


}
