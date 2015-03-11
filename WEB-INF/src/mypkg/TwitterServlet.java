
package twitter4j.api;

import twitter4j.*;



        import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class TwitterServlet extends HttpServlet {    	

         Twitter twitter = new TwitterFactory().getInstance(); 
        int hits = twitter.getRateLimitStatus().getRemainingHits(); 
        System.out.println(hits); 
        Status status = twitter.updateStatus("Really weird!"); 
        System.out.println("Successfullyted the status to [" 
                + status.getText() + "].");
 
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
            doPost(request, response);
        }
}