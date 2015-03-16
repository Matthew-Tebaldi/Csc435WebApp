package mypkg;  


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebParseServlet extends HttpServlet {  
   
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
      
	Document doc = Jsoup.connect("https://lakerlife.oswego.edu/Events").get();
        Element events = doc.getElementById("results");
       
        Elements lakerNames = events.select("h5");
        Elements lakerTime = doc.select("em");
        Elements lakerDescription = events.select("p");
        
        Object[] lakerNameArray = lakerNames.toArray();
       
     
        String eventName;
        String time;
        String description;
        boolean alreadyAnEvent = false;
     
        for (int i = 0; i < lakerNameArray.length; i++) {
            eventName = lakerNameArray[i].toString();
            eventName = eventName.substring(121, eventName.length()-11);
    
            Element lakerTimeEl = lakerTime.get(i);
            Element lakerDescriptionEl = lakerDescription.get(i);
               
            time = lakerTimeEl.html();
            description = lakerDescriptionEl.text();
                   
            sqlStr = "select name from events";       
            rset = stmt.executeQuery(sqlStr);
            
            alreadyAnEvent = false;
            while(rset.next()){               
                if(rset.getString("name").equals(eventName)){
                alreadyAnEvent = true;
                break;
            }
            }
            rset.beforeFirst();
  
            if(!alreadyAnEvent){  
                 out.println("<p>" + i + "<p/>");
                    sqlStr = "insert events values ('"+ eventName +"', 'Laker Life', 'date', '" 
                            + time + "', '" + description.substring(0, 2) + "', 'lakerLife')";               
                    stmt.executeUpdate(sqlStr);          
                } 
        }
        request.setAttribute("logCheck", "loggedOut");
        RequestDispatcher rqds = request.getRequestDispatcher("/loginJsp.jsp");
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
        


  
