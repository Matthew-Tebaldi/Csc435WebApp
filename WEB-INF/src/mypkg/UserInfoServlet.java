package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class UserInfoServlet extends HttpServlet {  
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
             
        String sqlStr;
        ResultSet rset;  
        String userName = (String) session.getAttribute("userName");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>userInfo</title>");
        out.println("</head>");
        out.println("<body style=background-color:yellow>");     
      
        sqlStr = "select * from commentTable where author = '"+ userName +"'";  
        rset = stmt.executeQuery(sqlStr);
           
        int commentCount = 0;
        while(rset.next()){
            commentCount++;
        }
       
        sqlStr = "select * from rateTable where author = '"+ userName +"'";  
        rset = stmt.executeQuery(sqlStr);
           
        int rateCount = 0;
        while(rset.next()){
            rateCount++;
        }
        sqlStr = "select * from rateTable where author = '"+ userName +"'";  
        rset = stmt.executeQuery(sqlStr);
           
        int rateCount2 = 0;
        int rateTotal = 0;
        while(rset.next()){
            rateTotal = rateTotal + rset.getInt("rate");
            rateCount2++;
        }
        
        int avrRate = 0;
        if(rateCount==0){
            out.println("<p> you havent rated an event yet </p>");
        } else {
            avrRate = rateTotal / rateCount2;
        }
        long start = session.getCreationTime();
        long end = session.getLastAccessedTime();
        double total = end - start;       
        total = total * 0.00001666666667;
        
        out.println("<p>Amount of events you commented on :" + commentCount + "</p>");
        out.println("<br />");
        out.println("<br />");
        out.println("<p>Amount of events you have rated :" + rateCount + "</p>");     
        out.println("<br />");
        out.println("<br />");
        out.println("<p> your average rating is :" + avrRate + "</p>");
        out.println("<br />");
        out.println("<br />");
        out.println("<p> you logged on for :" + total + " minutes </p>");
        
        sqlStr = "update users SET time = time + " + total + " where name = '" + userName + "'";
        stmt.execute(sqlStr);
        
        
        sqlStr = "select time from users where name = '" + userName + "'";
        rset = stmt.executeQuery(sqlStr);
        
        double totalTime = 0.0;
        boolean found = false;
        while(rset.next()){
           totalTime = rset.getDouble("time");
           found = true;
        }
        if(found){
             out.println("<p> Your total time using this web service is: " + totalTime + "</p>");
        } else {
        out.println("<p> something went wrong</p>");}
        session.invalidate();
        
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
 
