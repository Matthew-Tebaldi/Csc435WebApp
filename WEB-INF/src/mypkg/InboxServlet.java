package mypkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InboxServlet extends HttpServlet {
 
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

        Connection conn = null;
        Statement stmt = null;
 
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>inbox</title>");
        out.println("</head>");
        out.println("<body style=background-color:green>");     
  
        String sqlStr;
        ResultSet rset;  
        
        String userName = (String) session.getAttribute("userName");

    
            sqlStr = "select * from messaging where destination = '" + userName +"'";
            rset = stmt.executeQuery(sqlStr); 
           
            while(rset.next()){
               
                out.println("<p> Event user: </p>");
                out.println(rset.getString("author"));
                out.println("<br />");
         
                  out.println("<p> Has sent you this message: </p>");
                out.println(rset.getString("message"));
                  out.println("<br />");
                    out.println("<br />");
                      out.println("<br />");
                        out.println("<br />");
                        
            }
           out.println("<br />");   out.println("<br />");
            out.println("<p><a href='/csc435WebApp/eventYouLike'>home</a></p>");
            out.println("</body></html>");
        
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        out.close();  
        try {        
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }  
}  
        
