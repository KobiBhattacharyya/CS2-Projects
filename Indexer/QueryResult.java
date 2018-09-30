import java.util.ArrayList;

public class QueryResult
{
  String html, searchWord;
  String snippet;

  public QueryResult(String searchWord, String html,String snippet)
  {
    this.html = html;
    this.searchWord = searchWord;
    this.snippet = snippet;
  }

  public String getWord()
  {
    return searchWord;
  }

  public String getSnippet()
  {
    return snippet;
  }

  public String getHTML()
  {
    return html;
  }
}
