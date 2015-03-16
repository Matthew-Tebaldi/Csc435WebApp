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
            request.setAttribute("alreadyRated", true);
            RequestDispatcher rqds = request.getRequestDispatcher("/rateJsp.jsp");
            rqds.forward(request, response);
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
        