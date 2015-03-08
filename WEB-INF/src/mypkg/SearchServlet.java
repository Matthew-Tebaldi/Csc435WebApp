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
 
public class SearchServlet extends HttpServlet {  
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
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>search</title>");
        out.println("</head>");
        out.println("<body style=background-color:green>");     
  
        String sqlStr;
        ResultSet rset;  
        
        
        String eventName = request.getParameter("searchEvent");
        sqlStr = "select * from events where name = '" + eventName +"'";
        rset = stmt.executeQuery(sqlStr);
        
        String date = null;
        String time = null;   
        String author = null;
        String description = null;
                
        Boolean foundEvent=false;
        while(rset.next()){
            if(rset.getString("name").equals(eventName)){
                date = rset.getString("date");
                time = rset.getString("time");            
                author = rset.getString("author");
                description = rset.getString("description");
                foundEvent=true;
                break;
            }
        }
       
        if(!foundEvent){
             out.println("<p> Event Not Found! Try again</p>");
               out.println("<p><a href='/csc435WebApp/eventYouLike'>home</a></p>");
        } else{
         
        session.setAttribute("eventName", eventName);
        request.setAttribute("date", date);
        request.setAttribute("time", time);
        request.setAttribute("author", author);
        request.setAttribute("description", description);
          
         RequestDispatcher rqds = request.getRequestDispatcher("/search.jsp");
         rqds.forward(request, response);
       
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
        
