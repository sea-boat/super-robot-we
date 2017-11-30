package com.seaboat.superrobot.util;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>constants for the whole application.</p>
 */
public final class Constants {

  public static String APP_ID = null;

  public static String APP_SECRET = null;
  
  public static String TOKEN = null;

  public static final String GET = "GET";
  public static final String POST = "POST";

  public static final String REQ_TEXT_TYPE = "text";
  public static final String REQ_EVENT_TYPE = "event";
  public static final String REQ_SUBSCRIBE_TYPE = "subscribe";
  public static final String REQ_UNSUBSCRIBE_TYPE = "unsubscribe";

  public static final String RESP_TEXT_TYPE = "text";
  public static final String RESP_NEWS_TYPE = "news";

  public static final Integer TEXT_CODE = 100000;
  public static final Integer TRAIN_CODE = 305000;
  public static final Integer FLIGHT_CODE = 306000;
  public static final Integer LINK_CODE = 200000;
  public static final Integer NEWS_CODE = 302000;
  public static final Integer MENU_CODE = 308000;
  public static final Integer LENGTH_WRONG_CODE = 40001;
  public static final Integer EMPTY_CONTENT_CODE = 40002;
  public static final Integer KEY_WRONG_CODE = 40003;
  public static final Integer NUMBER_DONE_CODE = 40004;
  public static final Integer NOT_SUPPORT_CODE = 40005;
  public static final Integer UPGRADE_CODE = 40006;
  public static final Integer DATA_EXCEPTION_CODE = 40007;

  public final static String ACCESS_TOKEN_URL =
      "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

}
