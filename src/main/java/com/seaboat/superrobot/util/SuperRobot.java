package com.seaboat.superrobot.util;

import java.util.List;

import com.seaboat.robot.SuperEngine;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>super robot.</p>
 */
public class SuperRobot {

  private static SuperRobot instance;
  private SuperEngine engine;

  public SuperRobot() {
    this.engine = new SuperEngine();
  }

  public static SuperRobot getInstance() {
    if (instance != null) return instance;
    if (instance == null) {
      synchronized (SuperRobot.class) {
        if (instance == null) instance = new SuperRobot();
      }
    }
    return instance;
  }

  public void setWord2vecPath(String word2vecPath) {
    engine.setWord2vecPath(word2vecPath);
  }

  public void setQaFileList(List list) {
    engine.setQaFileList(list);
  }

  public void initMatcher() {
    engine.initMatcher();
  }

  public String createSession() {
    return engine.getSessionManager().requestSessionId();
  }

  public String response(String input, String sessionId) {
    return engine.respond(input, sessionId);
  }

  public String mnist(String sessionId) {
    return engine.respond("SUPER-ROBOT-MNIST", sessionId);
  }
}
