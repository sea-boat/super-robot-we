package com.seaboat.superrobot.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>a util for http protocol operation.</p>
 */
public final class HttpUtil {
  private static String boundary = "thisIsBoundary";
  private static String twoHyphens = "--";
  private static String end = "\r\n";

  private HttpUtil() {}

  public static String post(String requestUrl, Map<String, String> fileMaps,
      Map<String, String> parameters) {
    String result = null;
    try {
      HttpURLConnection httpUrlConn = initHttpURLConnection(requestUrl, "POST");
      DataOutputStream dos = new DataOutputStream(httpUrlConn.getOutputStream());
      if (fileMaps != null) {
        for (Entry<String, String> entry : fileMaps.entrySet()) {
          writeFileParams(dos, entry.getKey(), entry.getValue());
        }
      }

      if (parameters != null) {
        for (Entry<String, String> entry : parameters.entrySet()) {
          writeStringParams(dos, entry.getKey(), entry.getValue());
        }
      }

      paramsEnd(dos);
      dos.close();

      InputStream inputStream = httpUrlConn.getInputStream();
      result = getStringFromInputStream(inputStream);
      httpUrlConn.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String get(String requestUrl) {
    String result = null;
    try {
      HttpURLConnection httpUrlConn = initHttpURLConnection(requestUrl, "GET");
      InputStream inputStream = httpUrlConn.getInputStream();
      result = getStringFromInputStream(inputStream);
      httpUrlConn.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private static HttpURLConnection initHttpURLConnection(String requestUrl, String type)
      throws IOException {
    URL url = new URL(requestUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setUseCaches(false);
    conn.setConnectTimeout(10000);
    conn.setRequestMethod(type);
    if (type.equalsIgnoreCase("POST")) {
      conn.setRequestProperty("Connection", "Keep-Alive");
      conn.setRequestProperty("Charset", "UTF-8");
      conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
    }
    return conn;
  }

  private static void writeStringParams(DataOutputStream dos, String name, String value)
      throws IOException {
    dos.writeBytes(twoHyphens + boundary + end);
    dos.writeBytes("Content-Disposition:form-data;name=\"" + name + "\"" + end);
    dos.writeBytes(end);
    dos.write(value.getBytes("UTF-8"));
    dos.writeBytes(end);
  }

  private static void writeFileParams(DataOutputStream dos, String name, String fileUrlStr)
      throws IOException {
    URL fileUrl = new URL(fileUrlStr);
    URLConnection fileConn = fileUrl.openConnection();
    fileConn.setReadTimeout(10000);
    fileConn.setDoOutput(true);
    String fileExt = fileUrlStr.substring(fileUrlStr.lastIndexOf("."));
    String contentType = getContentType(fileExt);
    String fileName =
        fileUrlStr.substring(fileUrlStr.lastIndexOf("/") + 1, fileUrlStr.lastIndexOf("."));
    dos.writeBytes(twoHyphens + boundary + end);
    dos.writeBytes(
        String.format("Content-Disposition:form-data;name=\"" + name + "\";filename=\"%s%s\"" + end,
            fileName, fileExt));
    dos.writeBytes(String.format("Content-Type:%s" + end, contentType));
    dos.writeBytes(end);
    DataInputStream dis = new DataInputStream(fileConn.getInputStream());
    byte[] buf = new byte[1024 * 8];
    int length;
    while ((length = dis.read(buf)) != -1) {
      dos.write(buf, 0, length);
    }
    dos.writeBytes(end);
    dis.close();
  }

  private static void paramsEnd(DataOutputStream dos) throws IOException {
    dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
    dos.writeBytes(end);
  }

  private static String getStringFromInputStream(InputStream inputStream) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    StringBuilder builder = new StringBuilder();
    String str;
    while ((str = bufferedReader.readLine()) != null) {
      builder.append(str);
    }

    bufferedReader.close();
    inputStream.close();

    return builder.toString();
  }

  private static String getContentType(String fileExt) {
    String contentType = "text/html; charset=UTF-8";
    if (fileExt != null && fileExt.length() > 0) {
      if (".jpg".equalsIgnoreCase(fileExt))
        contentType = "image/jpeg";
      else if (".amr".equalsIgnoreCase(fileExt))
        contentType = "application/octet-stream";
      else if (".mp3".equalsIgnoreCase(fileExt))
        contentType = "audio/mp3";
      else if (".mp4".equalsIgnoreCase(fileExt))
        contentType = "video/mp4";
      else
        contentType = "application/octet-stream";
    }
    return contentType;
  }
}
