package com.star;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/dmeo2")
public class Demo extends HttpServlet{


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = req.getParameter("callback");
		resp.setContentType("application/x-javascript");
		PrintWriter out = resp.getWriter();
		out.print(parameter+"('data123');");
		out.flush();
		out.close();
	}
	
}
