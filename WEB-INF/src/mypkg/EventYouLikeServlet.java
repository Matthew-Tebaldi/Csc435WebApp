package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class EventYouLikeServlet extends HttpServlet {  
   
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
 
        sqlStr = "select * from rateTable where author = '" + userName + "'"; 
        rset = stmt.executeQuery(sqlStr);
                
// calc averages
        int movieCount = 1;
        int movieTotal = 0;
        int artCount = 1;
        int artTotal = 0;        
        int lecturesCount = 1;
        int lecturesTotal = 0;
        int natureCount = 1;
        int natureTotal = 0;  
        int musicCount = 1;
        int musicTotal = 0;
        int socialCount = 1;
        int socialTotal = 0;      
        int otherCount = 1;
        int otherTotal = 0;
          
        while(rset.next()){
            if(rset.getString("tag").equals("Movies")){
                movieCount++;
                movieTotal = movieTotal + rset.getInt("rate");
            } else
            if(rset.getString("tag").equals("Art")){
                artCount++;
                artTotal = artTotal + rset.getInt("rate");
            } else
            if(rset.getString("tag").equals("Lectures")){
                lecturesCount++;
                lecturesTotal = lecturesTotal + rset.getInt("rate");
            } else
            if(rset.getString("tag").equals("Nature")){
                natureCount++;
                natureTotal = natureTotal + rset.getInt("rate");
            } else
            if(rset.getString("tag").equals("Music")){
                musicCount++;
                musicTotal = musicTotal + rset.getInt("rate");
            } else
            if(rset.getString("tag").equals("Social")){
                socialCount++;
                socialTotal = socialTotal + rset.getInt("rate");
            } else
            if(rset.getString("tag").equals("Other")){
                lecturesCount++;
                lecturesTotal = lecturesTotal + rset.getInt("rate");
            }         
        }        
        int movieAvr = movieTotal / movieCount;      
        int artAvr = artTotal / artCount;
        int lecturesAvr = lecturesTotal / lecturesCount;
        int natureAvr = natureTotal / natureCount;
        int musicAvr = musicTotal / musicCount;
        int socialAvr = socialTotal / socialCount;
        int otherAvr = otherTotal / otherCount;
    

        
        int[][] rateArray = new int[7][2];
        rateArray[0][0] = movieAvr;
        rateArray[1][0] = artAvr;
        rateArray[2][0] = lecturesAvr;
        rateArray[3][0] = musicAvr;
        rateArray[4][0] = socialAvr;
        rateArray[5][0] = natureAvr;
        rateArray[6][0] = otherAvr;
        
        rateArray[0][1] = 1;
        rateArray[1][1] = 2;
        rateArray[2][1] = 3;
        rateArray[3][1] = 4;
        rateArray[4][1] = 5;
        rateArray[5][1] = 6;
        rateArray[6][1] = 7;
        
        int temp1;
        int temp2;
        int loopCount = 0;
      
        
        while(loopCount < 7){
            for (int i = 0; i < 6; i++) {
                if(rateArray[i][0] <= rateArray[i+1][0]){
                    temp1 = rateArray[i][0];
                    temp2 = rateArray[i][1];
                
                    rateArray[i][0] = rateArray[i+1][0];
                    rateArray[i][1] = rateArray[i+1][1];
                
                    rateArray[i+1][0] = temp1;
                    rateArray[i+1][1] = temp2;                             
                   
                }                                   
            }        
        loopCount++;
        }
                        
        Random rndm = new Random();
        int num = rndm.nextInt(100);
        
        int category = 45;
        
        if(num<40){
            category = rateArray[0][1];
        }  else if(num<60){         
            category = rateArray[1][1];
        } else if(num<75){
            category = rateArray[2][1];
        } else if(num<80){
            category = rateArray[3][1];
        }
        else if(num<90){
            category = rateArray[4][1];
        }
        else if(num<96){
            category = rateArray[5][1];
        }
        else if(num<100){
            category = rateArray[6][1];
        }
        
        String strCategory = null;
        if(category==0){
            strCategory = "Movie";
        }
        if(category==1){
            strCategory = "Art";
        }
        if(category==2){
            strCategory = "Lecture";
        }
        if(category==3){
            strCategory = "Music";
        }
        if(category==4){
            strCategory = "Social";
        }
        if(category==5){
            strCategory = "Nature";
        }
        if(category==6){
            strCategory = "Other";
        }
        
        
        String date = null;
        String time = null;
        String eName = null;
        String author = null;
        String description = null;
        
        sqlStr = "select name from events where tag = '" + strCategory + "'";
        rset = stmt.executeQuery(sqlStr);
         
        int count = 0;
        
        if(!rset.next()){
            
            request.setAttribute("name", "There are not enough events yet");
            request.setAttribute("date", "n/a");
            request.setAttribute("time", "n/a");
            request.setAttribute("author", "n/a");
            request.setAttribute("description", "Help us fill up our event database by adding events!");
	    session.setAttribute("eventName", " ");           
  
            RequestDispatcher rqds = request.getRequestDispatcher("/eventHome.jsp");
            rqds.forward(request, response);
            
        } else {
            rset.first();
            count++;
        }    
      
        while(rset.next()){          
            count++;
        }
        
        count = rndm.nextInt(count);
        loopCount = 0;
        
        sqlStr = "select * from events where tag = '" + strCategory + "'";
        rset = stmt.executeQuery(sqlStr);
       
        while(rset.next()){
            if(loopCount!=count){
               loopCount++;
            } else {
                date = rset.getString("date");
                time = rset.getString("time");
                eName = rset.getString("name");
                author = rset.getString("author");
                description = rset.getString("description");
            }
            
        }
         
	session.setAttribute("eventName", eName); 
        request.setAttribute("name", eName);
        request.setAttribute("date", date);
        request.setAttribute("time", time);
        request.setAttribute("author", author);
        request.setAttribute("description", description);
    
             
        RequestDispatcher rqds = request.getRequestDispatcher("/eventHome.jsp");
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
        
