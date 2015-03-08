package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class RateServlet extends HttpServlet {  
//doGet
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
         
   
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
    
        String userName = (String)session.getAttribute("userName");     
        Connection conn = null;
        Statement stmt = null;
 
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
        
      
        String sqlStr;
        ResultSet rset;  
        String eventName = (String)session.getAttribute("eventName");
        String rate = request.getParameter("rating");
        Integer intRate = Integer.parseInt(rate);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>comments</title>");
        out.println("</head>");
        out.println("<body style=background-color:green>");     
     
      out.println("<p> hi </p>");
     
        sqlStr = "select * from rateTable";
        rset = stmt.executeQuery(sqlStr);
          
        
    
        int i = 1; 
        Boolean alreadyRated = false;
        while(rset.next()){        
           if(rset.getString("event").equals(eventName) && rset.getString("author").equals(userName)){
              
                alreadyRated = true;
               break;
           }                      
            i++;     
        }
        
        String tag = null;
        
        if(alreadyRated){
            
             out.println("<p> Sorry but you already rated the event</p>");
               out.println("<p><a href='/csc435WebApp/eventYouLike'>home</a></p>");
             out.println("</body></html>");
        }else{
            sqlStr = "select tag from events where name = '" + eventName + "'";
            rset = stmt.executeQuery(sqlStr);
            
            while(rset.next()){
                tag = rset.getString("tag");
            }
                sqlStr = "select tag from events where name = '" + eventName + "'";
                rset = stmt.executeQuery(sqlStr);
                
                
        sqlStr = "insert rateTable values (" + i + ", '"+ userName + "', '"+ eventName + "', '" + intRate + "', '" + tag + "')";
        stmt.executeUpdate(sqlStr);
   
        RequestDispatcher rqds = request.getRequestDispatcher("search?searchEvent=" + session.getAttribute("eventName"));
        rqds.forward(request, response);
        }
       
       
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
//doPost
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException { 
        
    }
    
}  
        