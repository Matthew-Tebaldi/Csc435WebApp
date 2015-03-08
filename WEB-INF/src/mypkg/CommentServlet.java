package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class CommentServlet extends HttpServlet {  
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
    
        String userName = (String)session.getAttribute("userName");
    
        Connection conn = null;
        Statement stmt = null;
 
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
        
    
      
        String sqlStr;
        ResultSet rset;  
        
        
        String comment = request.getParameter("userComment");
        sqlStr = "select num from commentTable";
        rset = stmt.executeQuery(sqlStr);
        

        int num = 0;
        while(rset.next()){
            num = rset.getInt("num");            
        }
        
        num++;
        
        String eventName = (String)session.getAttribute("eventName");
           
            
        sqlStr = "insert commentTable values (" + num + ", '"+ userName +"', '" +  eventName + "', '" + comment + "')";
        stmt.execute(sqlStr);
        
         RequestDispatcher rqds = request.getRequestDispatcher("search?searchEvent=" + session.getAttribute("eventName"));
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
        