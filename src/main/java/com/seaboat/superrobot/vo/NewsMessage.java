package com.seaboat.superrobot.vo;

import java.util.List;

import com.seaboat.superrobot.util.Constants;


/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>news message vo.</p>
 */
public class NewsMessage extends BaseMessage {

  private int ArticleCount;
  private List<Article> Articles;

  public NewsMessage() {
    super();
  }

  public NewsMessage(String fromUserName, String toUserName) {
    super(fromUserName, toUserName);
    super.setMsgType(Constants.RESP_NEWS_TYPE);
  }

  public int getArticleCount() {
    return ArticleCount;
  }

  public void setArticleCount(int articleCount) {
    ArticleCount = articleCount;
  }

  public List<Article> getArticles() {
    return Articles;
  }

  public void setArticles(List<Article> articles) {
    Articles = articles;
  }

}
