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
    
  
        String sqlStr;
        ResultSet rset;  
        
        String userName = (String) session.getAttribute("userName");

    
            sqlStr = "select * from messaging where destination = '" + userName +"'";
            rset = stmt.executeQuery(sqlStr); 
           
            String[] author = new String[100];          
            String[] message = new String[100];
            int i=0;
            
            while(rset.next()){    
                author[i] = rset.getString("author");
                message[i] =rset.getString("message"); 
                i++;
            }
            request.setAttribute("authorArray", author);
            request.setAttribute("messageArray", message);
            RequestDispatcher rqds = request.getRequestDispatcher("/inboxJsp.jsp");
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
}  
        
