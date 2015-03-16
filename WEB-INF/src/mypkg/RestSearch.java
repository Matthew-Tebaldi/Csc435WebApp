package mypkg;

import org.json.simple.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;
 
public class RestSearch extends HttpServlet {  
    
    
//doGet
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
         doPost(request, response);
    
   
    response.setContentType("csc435WebApp/json; charset=UTF-8");
    PrintWriter out = response.getWriter();
   
       
        Connection conn = null;
        Statement stmt = null;
 
    try {     
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost/csc435WebApp", "myuser", "xxxx");
        stmt = conn.createStatement();
             
        String sqlStr;
        ResultSet rset;  
        String eventName = (String)request.getParameter("eventName");
               
        JSONObject json = new JSONObject();
  
        
    //info
        sqlStr = "select * from events where name = '" + eventName + "'";
        rset = stmt.executeQuery(sqlStr);
        
       
        String author=null;
        String time=null;
        String date=null;
        String description=null;
        String tag=null;
         
        while(rset.next()){
            date = rset.getString("date");
            time = rset.getString("time");            
            author = rset.getString("author");
            description = rset.getString("description"); 
            tag = rset.getString("tag");
        }
        
        JSONObject infoMap = new JSONObject();
        
        infoMap.put("name", eventName);
        infoMap.put("date", date);
        infoMap.put("time", time);
        infoMap.put("author", author);
        infoMap.put("description", description);
        infoMap.put("tag", tag);
        
        json.put("infoMap", infoMap);
                
    //comment 
        JSONObject commentMap = new JSONObject(); 
        
        sqlStr = "select * from commentTable where event = '"+ eventName +"'";   
        rset = stmt.executeQuery(sqlStr);

        JSONArray cList = new JSONArray();
        JSONArray aList = new JSONArray();
        while(rset.next()){    
            aList.add(rset.getString("author")); 
            cList.add(rset.getString("comments"));
        } 

        
        commentMap.put(aList, cList);
      
        json.put("commentMap", commentMap);
                
    //rate
        JSONObject rateMap = new JSONObject();        
                
        sqlStr = "select * from rateTable where event = '"+ eventName +"'";
        rset = stmt.executeQuery(sqlStr);          
    
        String[] rList = new String[100]; 
        int k = 0;
            
        while(rset.next()){
            rList[k] = rset.getString("rate");        
            k++;     
        }
        int total = 0;
        int count = 0;
        for (int i = 0; i < 100; i++) {         
            if(rList[i]!=null){
                Integer rate = Integer.parseInt(rList[i]);               
                total = total + rate; 
                count++;
                } 
            }
        int avr = total/count;
	
        rateMap.put("AverageRate", avr);
        rateMap.put("numberOfRates", count);
                
        json.put("rate", rateMap);
       
		// finally output the json string		
		out.println(json.toString());
                
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
        

