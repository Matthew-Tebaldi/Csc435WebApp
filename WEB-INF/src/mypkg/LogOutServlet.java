package mypkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class LogOutServlet extends HttpServlet {
     
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }
        
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
      
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(); 
        
    try {        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Logout</title>");
        out.println("</head>");
        out.println("<body style=background-color:green>");     
        
            session.setAttribute("userName", " ");
            
        out.println("<h3>You have been logged out.</h3>");       
        out.println("<p><a href='/csc435WebApp'>home</a></p>");               
        out.println("</body></html>");                     
    } finally {
        out.close();      
    }
    }   
}
