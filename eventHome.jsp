<!DOCTYPE html>

<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <title>Events Home</title>

<style>
    header {
    	background-color:green;
    	color:yellow;
    	text-align:center;
    	padding:5px;	 
    }
    nav {
    	line-height:30px;
    	background-color:yellow;
    	height:600px;
    	width:200px;
    	float:left;
    	padding:5px;	      
    }
    section {
    	
    	
    	padding:10px;	 	 
    }
    footer {
    	background-color:black;
    	color:white;
    	clear:both;
    	text-align:center;
    	padding:5px;	 	 
    }
    btn {
	background:#E27575;
	height:30px;
	}

</style>
</head>

<body style="background-color:green">

<header>
<h1>Welcome To Oswego Events!</h1>

<h2>
    <form style="float: left" method="post" action="addEvent.jsp">
        <input type="submit" value="Click to add an event">
    </form>
    
   <form style="float:left; color:black" method="post" action="inbox">                      
         <input type="submit" value="Click to see your messages" />                        
   </form>   

   <form style="float:left; color:black" method="get" action="logout">                      
         <input type="submit" value="logout" />                        
   </form>
    
     <form style="float: left" method="get" action="getEvents">
        <input type="submit" value="All Events">
    </form>
   
   <form style="float:right; color: black; font-size: small" method="get" action="search">              
        Search for an Event: <textarea  rows="1" cols="30" name="searchEvent"></textarea>                    
        <input type="submit" value="Search" />                   
   </form>  
</h2>

</header>
   <br />
   <br />
   <br />
    
<section style="background-color: white; column-count:3; text-align: center">
   
    <h1> Here is an event You might Like: </h1>        
    
    <% String eName = (String) request.getAttribute("eName"); %>
    <% if(eName==null){%>
    <p> We don't have enough events, help us out by adding an event! </p>
    <% } else { %>  
    We have came up with this event for you:
    <form method="get" action="search">
    <textarea rows="1" cols="30" name="searchEvent"><%=eName%></textarea>
    <br/>
    <input type="submit" value="Go to event" />  
    </form>
    <%}%>
    
</section>
    <hr>
    
<section style="background-color: white; column-count:3; text-align: center">
    <h> Here are tweets from Suny Oswego: </h>
    <br/><br/><br/>
    <% String[] tweets = (String[])request.getAttribute("tweets");%> 
        <%for(int i=0; i< 20; i++){
        if(tweets[i]!=null){%>  <%=tweets[i]%>
    <br/><br/>
    <%
            
    }}%>
      
</section>
     
<section>
	<form style="background-color:white; padding:20px; float:right" method="get" action="messaging">
        	<fieldset>  
        	<legend>Message Someone!</legend>
                	<p>User:</p>  
                 		<textarea rows="1" cols="30" name="user"></textarea>
                	<p>Message</p> 
                 		<textarea rows="5" cols="50" name="letter"></textarea>
                		<br />
				<input type="submit" value="Send" />
         	<br />
        	</fieldset>
     	</form>   
</section> 

</body>
</html>
