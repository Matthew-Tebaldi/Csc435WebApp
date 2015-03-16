package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class GetEventsServlet extends HttpServlet {

//doGet
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
     
      
//declarations
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        Connection conn = null;
        Statement stmt = null;

       
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
      
        String sqlStr;
        ResultSet rset;  
            
        

        sqlStr = "select * from events";       
        rset = stmt.executeQuery(sqlStr);


        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> whens = new ArrayList<>();     
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
       
             
        while(rset.next()){         
            names.add(rset.getString("name"));         
            whens.add(rset.getString("time"));
            authors.add(rset.getString("author"));
            descriptions.add(rset.getString("description"));
            tags.add(rset.getString("tag"));     
               
            }
        
  
            request.setAttribute("eventNames", names);
            request.setAttribute("whens", whens);
            request.setAttribute("authors", authors);
            request.setAttribute("descriptions", descriptions);
            request.setAttribute("tags", tags);
    
            RequestDispatcher rqds = request.getRequestDispatcher("/getEvents.jsp");
            rqds.forward(request, response);
        
        
    
     
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
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException { 
    }   
}  
     