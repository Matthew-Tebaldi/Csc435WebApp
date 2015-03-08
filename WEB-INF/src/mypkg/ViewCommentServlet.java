package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class ViewCommentServlet extends HttpServlet {  
//doGet
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
         doPost(request, response);
    }
   
//doPost
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
             
        String sqlStr;
        ResultSet rset;  
        String eventName = (String)session.getAttribute("eventName");
               
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>comments</title>");
        out.println("</head>");
        out.println("<body style=background-color:yellow>");     
      
        sqlStr = "select * from commentTable where event = '"+ eventName +"'";
     
        rset = stmt.executeQuery(sqlStr);
           
        String[] cList = new String[100];
        String[] aList = new String[100];
        int i = 0;
      
        while(rset.next()){
            cList[i] = rset.getString("comments"); 
            aList[i] = rset.getString("author");
            i++; 
        }     
        for (i = 0; i < 100; i++) {        
             if(cList[i]!=null){                
                out.println("<p>" + aList[i] + "  <br /> <br />Wrote:<br />"+ cList[i] +"</p>");
                out.println("<hr>");
            } 
        }       
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
        
