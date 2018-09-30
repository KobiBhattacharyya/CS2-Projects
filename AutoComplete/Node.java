import java.util.ArrayList;

public class Node
{
  boolean isEnd = false;
  Node[] children = new Node[26]; //space for each lowercase letter in alphabet... make sure each index is char-'a'

  public void setEnd()
  {
    isEnd = true;
  }

  public boolean isEndString()
  {
    return isEnd;
  }

  public Node[] getChildren()
  {
    return children;
  }
}
