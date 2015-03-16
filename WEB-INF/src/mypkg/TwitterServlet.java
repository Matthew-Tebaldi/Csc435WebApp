package mypkg;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
 
public class TwitterServlet extends HttpServlet {  

//doGet
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
           
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("pexFcxIUSMEicTUDt7Na20zJN");
        cb.setOAuthConsumerSecret("ztsBEk8babKF6tHmpwMyNx3Qz1ZnKlZB6O0gXsr5zwwVx9a8Fn");
        cb.setOAuthAccessToken("3084799437-B6zCeIuPv3gXx1apgktSOIBkbpWcxfQJc2Qo4Ax");
        cb.setOAuthAccessTokenSecret("QYrMEFeBY4yxYk1a7CCXVcNm1UReCYaBLd4cvovGItzIT");
    
   
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

      
        
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();

    try {     

   
        String[] tweets = new String[20];
        String update;
        
        List<Status> oswegoTweets =  twitter.getUserTimeline("sunyoswego");
        int i=0;
        for (Status status : oswegoTweets) {
            update = status.getText();
            if(update.substring(0, 2).equals("RT")){
            
        } else {
                tweets[i] = status.getText();
                i++;
      
        }
        }
        request.setAttribute("tweets", tweets);
        RequestDispatcher rqds = request.getRequestDispatcher("/eventYouLike");
        rqds.forward(request, response);
       
    }catch (TwitterException ex) {
            Logger.getLogger(TwitterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TwitterServlet.class.getName()).log(Level.SEVERE, null, ex);
         
    } finally {
        out.close();  
       
        
    }
    }    
//doPost
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException { 
        
    }
    
}  
        