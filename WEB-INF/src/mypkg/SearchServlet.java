package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class SearchServlet extends HttpServlet {  
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
        
        
        String eventName = request.getParameter("searchEvent");
        sqlStr = "select * from events where name = '" + eventName +"'";
        rset = stmt.executeQuery(sqlStr);
        
        String date = null;
        String time = null;   
        String author = null;
        String description = null;
                
        Boolean foundEvent=false;
        while(rset.next()){
            if(rset.getString("name").equals(eventName)){
                date = rset.getString("date");
                time = rset.getString("time");            
                author = rset.getString("author");
                description = rset.getString("description");
                foundEvent=true;
                break;
            }
        }
       
        if(!foundEvent){
            request.setAttribute("eventNotFound", true);
            RequestDispatcher rqds = request.getRequestDispatcher("/search.jsp");
            rqds.forward(request, response);
        } else{
            
   //event info      
        session.setAttribute("eventName", eventName);
        request.setAttribute("date", date);
        request.setAttribute("time", time);
        request.setAttribute("author", author);
        request.setAttribute("description", description);
         
    //get rating
        out.println(eventName);
        sqlStr = "select * from rateTable";
        rset = stmt.executeQuery(sqlStr);          
        Boolean contains=false;
            
        while(rset.next()){
            if(rset.getString("event").equals(eventName)){
                contains = true;
            }
        }
        
        if(contains){
        sqlStr = "select * from rateTable where event = '"+ eventName +"'";
        rset = stmt.executeQuery(sqlStr);          
    
        String[] rList = new String[100];
        
        int i = 0;
            
        while(rset.next()){
            rList[i] = rset.getString("rate");        
            i++;     
        }
        int total = 0;
        int count = 0;
        for (i = 0; i < 100; i++) {         
            if(rList[i]!=null){
                Integer rate = Integer.parseInt(rList[i]);               
                total = total + rate; 
                count++;
                } 
            }
        int avr = total/count;
         
        request.setAttribute("eRateAvr", avr); 
        } else { request.setAttribute("eRateAvr", 0); }
    
   // getting comments     
         sqlStr = "select * from commentTable where event = '"+ eventName +"'";
     
        rset = stmt.executeQuery(sqlStr);
           
        String[] cList = new String[100];
        String[] aList = new String[100];
        int j = 0;
      
        while(rset.next()){
            cList[j] = rset.getString("comments"); 
            aList[j] = rset.getString("author");
            j++; 
        }     
        request.setAttribute("cList", cList);
        request.setAttribute("aList", aList);
        
        
     //forwarding   
         RequestDispatcher rqds = request.getRequestDispatcher("/search.jsp");
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
        
