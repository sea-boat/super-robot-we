package com.seaboat.superrobot.vo;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>article vo.</p>
 */
public class Article {
  private String Title;
  private String Description;
  private String PicUrl;
  private String Url;

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public String getPicUrl() {
    return PicUrl;
  }

  public void setPicUrl(String picUrl) {
    PicUrl = picUrl;
  }

  public String getUrl() {
    return Url;
  }

  public void setUrl(String url) {
    Url = url;
  }

}
