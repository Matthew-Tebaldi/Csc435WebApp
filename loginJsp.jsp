<!DOCTYPE html>
    <% String logCheck = (String) request.getAttribute("logCheck"); %>
<html>
    <head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>Login</title>
    </head>
    <body style="background-color: yellow">  
        <% if(logCheck.equals("userNameTaken")){ %>
            <p> Sorry, but that user name has already been taken. Try a different user name. </p> 
        <form method="post" action="signup">
        	Login: <br /> <textarea rows="1" cols="15" name="user"></textarea> <br />
        	Password: <br /> <input type="password"  name="pswrd" /> 
    	
	<br />
        <input type="submit" value="Sign-Up" />    
    	</form>
            <% } else if (logCheck.equals("signedUp")){ %>
            <p> You have signed up!  </p>  
             <form method="post" action="eventYouLike">
        	
	<br />
        <input type="submit" value="To home page" />    
    	</form>
            
        <% } else if (logCheck.equals("loginNameNotFound")){ %>
            <p> Sorry, your username can't be found. If you would like to sign up:</p> 
             <form method="post" action="signup">
        	Login: <br /> <textarea rows="1" cols="15" name="user"></textarea> <br />
        	Password: <br /> <input type="password"  name="pswrd" /> 
    	
	<br />
        <input type="submit" value="Sign-Up" />    
    	</form>
            
          <% } else if (logCheck.equals("wrongPass")){ %>   
          <p>Your password was wrong please try again:</p>
            <form  method="post" action="login">
    		Login: <br /> <textarea rows="1" cols="15" name="loginText"></textarea> <br />
    		Password: <br /> <input type="password"  name="pswrd" /> 
    	
	<br />
	<input type="submit" value="login" />    
        </form>
          
            <% } else if (logCheck.equals("loggedIn")){ %>
             <p> You have Signed in.  </p>  
            <br/><br/>
            <p><a href='/csc435WebApp/twitter'>Home</a></p> 
            
    
          
           <% } else if (logCheck.equals("loggedOut")){ %>   
          <p>You have logged out.</p>         
        <% } %>

        <p><a href='/csc435WebApp'>Login Page</a></p> 
    </body></html>           
        