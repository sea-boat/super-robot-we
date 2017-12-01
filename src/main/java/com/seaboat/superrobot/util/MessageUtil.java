package com.seaboat.superrobot.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seaboat.superrobot.vo.Article;
import com.seaboat.superrobot.vo.NewsMessage;
import com.seaboat.superrobot.vo.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>a util for message operation.</p>
 */
public final class MessageUtil {

  private MessageUtil() {}

  private static XStream xstream = new XStream(new XppDriver() {
    public HierarchicalStreamWriter createWriter(Writer out) {
      return new PrettyPrintWriter(out) {
        boolean cdata = true;

        @SuppressWarnings("rawtypes")
        public void startNode(String name, Class clazz) {
          super.startNode(name, clazz);
        }

        protected void writeText(QuickWriter writer, String text) {
          if (cdata) {
            writer.write("<![CDATA[");
            writer.write(text);
            writer.write("]]>");
          } else {
            writer.write(text);
          }
        }
      };
    }
  });

  public static String streamConvertToStr(InputStream inputStream) throws IOException {
    byte[] bytes = new byte[1024 * 1024];
    int nRead = 1;
    int nTotalRead = 0;
    while (nRead > 0) {
      nRead = inputStream.read(bytes, nTotalRead, bytes.length - nTotalRead);
      if (nRead > 0) nTotalRead = nTotalRead + nRead;
    }
    return new String(bytes, 0, nTotalRead, "utf-8");
  }

  @SuppressWarnings("unchecked")
  public static Map<String, String> parseXml(InputStream inputStream) throws Exception {

    StringBuilder xmlMsg = new StringBuilder();
    byte[] b = new byte[4096];
    for (int n; (n = inputStream.read(b)) != -1;) {
      xmlMsg.append(new String(b, 0, n, "UTF-8"));
    }
    // inputStream.close();
    Map<String, String> map = new HashMap<String, String>();
    SAXReader reader = new SAXReader();
    Document document = reader.read(new ByteArrayInputStream(xmlMsg.toString().getBytes()));
    Element root = document.getRootElement();
    List<Element> elementList = root.elements();
    for (Element e : elementList) {
      map.put(e.getName(), e.getText());
    }
    return map;
  }

  public static <T> String ObjectToXml(T t) {
    xstream.alias("xml", t.getClass());
    if (t instanceof NewsMessage) {
      xstream.alias("item", new Article().getClass());
    }
    return xstream.toXML(t);
  }


  private static void assembleNews(NewsMessage news, List<JSONObject> list, int code) {
    String titleKey = "article";
    String descriptionKey = "article";
    if (Constants.MENU_CODE.equals(code)) {
      titleKey = "name";
      descriptionKey = "info";
    }
    List<Article> articles = new ArrayList<Article>();
    for (JSONObject obj : list) {
      Article article = new Article();
      if (Constants.TRAIN_CODE.equals(code)) {
        article.setTitle(obj.getString("trainnum") + ":" + obj.getString("starttime"));
        article.setDescription(obj.getString("start") + "(" + obj.getString("starttime") + ")>"
            + obj.getString("terminal") + "(" + obj.getString("endtime") + ")");
      } else if (Constants.FLIGHT_CODE.equals(code)) {
        article.setTitle(obj.getString("flight") + " :" + obj.getString("starttime"));
        article.setDescription(obj.getString("route") + obj.getString("starttime") + ">"
            + obj.getString("endtime") + "\n:" + obj.getString("state"));
      } else {
        article.setTitle(obj.getString(titleKey));
        article.setDescription(obj.getString(descriptionKey));
      }
      article.setPicUrl(obj.getString("icon"));
      article.setUrl(obj.getString("detailurl"));
      articles.add(article);
      if (articles.size() == 10) {
        break;
      }
    }
    news.setArticles(articles);
    news.setArticleCount(articles.size());
  }
}
