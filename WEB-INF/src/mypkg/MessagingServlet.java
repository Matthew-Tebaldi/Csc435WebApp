package mypkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class MessagingServlet extends HttpServlet {
 
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
    String sessionId = session.getId();

        Connection conn = null;
        Statement stmt = null;
 
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
   
  
	String userName = (String) session.getAttribute("userName");
        String sqlStr;
        ResultSet rset;  
        
            String author = userName;
            String user = request.getParameter("user");           
            String body = request.getParameter("letter") ;
        
            
         sqlStr = "select name from users where name = '" + user + "'";
         rset = stmt.executeQuery(sqlStr);  
            
        boolean inDatabase = false;
        while(rset.next()){
            if(rset.getString("name").equals(user)){
                inDatabase = true;
                break;
            }
        }  
          
        if(!inDatabase){
            request.setAttribute("messaging", "usernameNotFound");
            RequestDispatcher rqds = request.getRequestDispatcher("/messagingJsp.jsp");
            rqds.forward(request, response);
           
        } else {
            sqlStr = "insert messaging values ('" + author + "', '" + body + "', '" + user + "' )";
            stmt.executeUpdate(sqlStr); 
            request.setAttribute("messaging", "sent");
            RequestDispatcher rqds = request.getRequestDispatcher("/messagingJsp.jsp");
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
}  
