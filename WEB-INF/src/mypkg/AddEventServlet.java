package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class AddEventServlet extends HttpServlet {

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
    
//declarations
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String sessionId = session.getId();
 
        Connection conn = null;
        Statement stmt = null;

        String userName = (String)session.getAttribute("userName");
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Add</title>");
        out.println("</head>");
        out.println("<body style=background-color:green>");     
  
        String sqlStr;
        ResultSet rset;  
           
        Webparse wParse = new WebParse();
        
        
            String eventName = request.getParameter("eventName");
            String author = userName;
            String date = request.getParameter("date") ;
            String time = request.getParameter("time") ;
            String description = request.getParameter("description") ;
            String tag = request.getParameter("tags") ;
            
        sqlStr = "select name from events where name = '" + eventName +"'";       
        rset = stmt.executeQuery(sqlStr);

//check to see if event already exists
        
        boolean alreadyAnEvent = false;
        while(rset.next()){
            if(rset.getString("name").equals(eventName)){
                alreadyAnEvent = true;
                break;
            }
        }
    
        if(alreadyAnEvent){
            out.println("<p> Sorry, there is already an event with that name.</p>");
        } else{
            sqlStr = "insert events values ('" + eventName + "', '" + author 
                    + "', '" + date +"', '" + time + "', '" + description 
                    + "', '" + tag + "' )"; 
            stmt.executeUpdate(sqlStr);            
            RequestDispatcher rqds = request.getRequestDispatcher("/eventYouLike");
            rqds.forward(request, response);
        }
        
    
        out.println("<p><a href='/csc435WebApp/addEvent.jsp'>home</a></p>");
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
        
