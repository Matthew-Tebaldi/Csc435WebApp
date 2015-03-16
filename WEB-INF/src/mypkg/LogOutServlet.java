package mypkg;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class LogOutServlet extends HttpServlet {
     
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
	String userName = (String) session.getAttribute("userName");
 
       
	long start = session.getCreationTime();
        long end = session.getLastAccessedTime();
        double total = end - start;       
        total = total * 0.00001666666667;
        sqlStr = "update users SET time = time + " + total + " where name = '" + userName + "'";
        stmt.execute(sqlStr);
        
	session.invalidate();  
        
      
        RequestDispatcher rqds = request.getRequestDispatcher("/webParse");
        rqds.forward(request, response);    
        
    } catch (SQLException ex){
	ex.printStackTrace();
    } finally {
        out.close();      
	try {
		if(stmt !=null) stmt.close();
		if (conn != null) conn.close();
	} catch (SQLException ex){
		ex.printStackTrace();
    	}
    }
    }   
}
