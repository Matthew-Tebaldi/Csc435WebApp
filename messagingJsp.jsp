<!DOCTYPE html>
    <% String messaging = (String) request.getAttribute("messaging"); %>
  
<html>
    <head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>Messaging</title>
    </head>
    <body style="background-color:green">  
        <% if(messaging.equals("usernameNotFound")){ %>
            <p> Sorry, that username can't be found. </p> 
            <br/><br/>
            Try again?
      	<form style="width:300px" method="get" action="messaging">
       
        	
                	<p>User:</p>  
                 		<textarea rows="1" cols="30" name="user"></textarea>
                	<p>Message</p> 
                 		<textarea rows="5" cols="50" name="letter"></textarea>
                		<br />
				<input type="submit" value="Send" />
         	<br />
        	
     	</form>  
            
            
            <% } else if (messaging.equals("sent")){ %>
            <p> Your message has been sent.  </p> 
            <br/><br/>
          Message someone else?
            <form style="width:300px" method="get" action="messaging">
        	
        	
                	<p>User:</p>  
                 		<textarea rows="1" cols="30" name="user"></textarea>
                	<p>Message</p> 
                 		<textarea rows="5" cols="50" name="letter"></textarea>
                		<br />
				<input type="submit" value="Send" />
         	<br />
        	
     	</form>  
            
  
        <% } %>
<br/><br/>
       <a href='/csc435WebApp/twitter'>Home</a>
    </body></html>           
        
