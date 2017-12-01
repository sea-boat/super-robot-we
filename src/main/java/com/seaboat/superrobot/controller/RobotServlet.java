package com.seaboat.superrobot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seaboat.robot.SessionManager;
import com.seaboat.robot.SuperContext;
import com.seaboat.robot.util.Constants;
import com.seaboat.superrobot.util.SuperRobot;

public class RobotServlet extends HttpServlet {

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		SuperRobot.getInstance();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String input = request.getParameter("question");
		input = new String(input.getBytes("iso-8859-1"), "UTF-8");
		SuperRobot robot = SuperRobot.getInstance();
		String sessionId=null;
//		if (request.getSession().getAttribute(Constants.ROBOT_SESSION_ID) == null) {
//			request.getSession().setAttribute(Constants.ROBOT_SESSION_ID,
//					robot.createSession());
//		}
//		sessionId = (String) request.getSession().getAttribute(
//				Constants.ROBOT_SESSION_ID);
//		SuperContext context = SessionManager.getInstance().getContext(
//				sessionId);
//		List sentences = context.getAttributes(Constants.SENTENCES) == null ? null
//				: (List) context.getAttributes(Constants.SENTENCES);
//		if (sentences == null) {
//			sentences = new ArrayList();
//			context.setAttribute(Constants.SENTENCES, sentences);
//		}
//		sentences.add(input);
//		System.out.println(input);
		String robResponse = robot.response(input, sessionId);
		out.print(robResponse);
		out.flush();
		out.close();
	}
}
