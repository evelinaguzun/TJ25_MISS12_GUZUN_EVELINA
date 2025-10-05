package com.tj25.tema1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String page = request.getParameter("page");

     
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Client IP: " + request.getRemoteAddr());
        System.out.println("User-Agent: " + request.getHeader("User-Agent"));
        System.out.println("Client Language: " + request.getHeader("Accept-Language"));
        System.out.println("Page parameter: " + page);

        
        if ("1".equals(page)) {
            response.sendRedirect("page1.html");
        } else if ("2".equals(page)) {
            response.sendRedirect("page2.html");
        } else {
            PrintWriter out = response.getWriter();
            out.println("Parametru invalid!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); 
    }
}

