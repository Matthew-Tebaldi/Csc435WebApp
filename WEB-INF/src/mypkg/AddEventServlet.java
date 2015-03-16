package mypkg;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
 
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
      
        String sqlStr;
        ResultSet rset;  
           
     
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
            request.setAttribute("addFailed", true);
            RequestDispatcher rqds = request.getRequestDispatcher("/addEvent.jsp");
            rqds.forward(request, response);
        } else{
            sqlStr = "insert events values ('" + eventName + "', '" + author 
                    + "', '" + date +"', '" + time + "', '" + description 
                    + "', '" + tag + "' )"; 
            stmt.executeUpdate(sqlStr);    
            
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("pexFcxIUSMEicTUDt7Na20zJN");
        cb.setOAuthConsumerSecret("ztsBEk8babKF6tHmpwMyNx3Qz1ZnKlZB6O0gXsr5zwwVx9a8Fn");
        cb.setOAuthAccessToken("3084799437-B6zCeIuPv3gXx1apgktSOIBkbpWcxfQJc2Qo4Ax");
        cb.setOAuthAccessTokenSecret("QYrMEFeBY4yxYk1a7CCXVcNm1UReCYaBLd4cvovGItzIT");
    
   
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
            
            twitter.updateStatus(eventName + " is going to be going on at this time: " + time );
            RequestDispatcher rqds = request.getRequestDispatcher("/twitter");
            rqds.forward(request, response);
        }
        
    
     
    } catch (SQLException ex) {
        ex.printStackTrace();
    }   catch (TwitterException ex) {
            Logger.getLogger(AddEventServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        
