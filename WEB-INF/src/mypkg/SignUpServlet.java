package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class SignUpServlet extends HttpServlet {    	

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
//session declarations
        String pass = request.getParameter("pswrd");
        String user = request.getParameter("user");           
//sql declarations
        Connection conn = null;
        Statement stmt = null;
    try {           
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
       
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>SignUP</title>");
        out.println("</head>");
        out.println("<body style=background-color:green>");     
                
//See if the user exists
        String sqlStr = "select name from users where name = '" + user + "'";
        ResultSet rset = stmt.executeQuery(sqlStr);  
            
        boolean inDatabase = false;
        while(rset.next()){
            if(rset.getString("name").equals(user)){
                inDatabase = true;
                break;
            }
        }  
         
        if(inDatabase){
           request.setAttribute("logCheck", "userNameTaken");
         	RequestDispatcher rqds = request.getRequestDispatcher("/loginJsp.jsp");
         	rqds.forward(request, response);
            
        } else {
            sqlStr = "insert into users values('" + user + "', '" + pass + "', 0.0)";
            stmt.execute(sqlStr);  
                 
                request.setAttribute("logCheck", "signedUp");
         	RequestDispatcher rqds = request.getRequestDispatcher("/loginJsp.jsp");
         	rqds.forward(request, response);
               
        }
            out.println("<p><a href='/csc435WebApp'>home</a></p>");
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
