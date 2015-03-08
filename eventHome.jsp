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

   <form style="float:right; color: black; font-size: small" method="get" action="search">              
        Search for an Event: <textarea  rows="1" cols="30" name="searchEvent"></textarea>                    
        <input type="submit" value="Search" />                   
   </form>  
</h2>

</header>
   <br />
   <br />
   <br />
    
<section style="background-color: white; float:left; text-align: center">
   
    <h1> Here is an event You might Like: </h1>        
        <p>            
            Event: <%=  request.getAttribute("name")%>
            <br />
            Date: <%=  request.getAttribute("date")%>
            <br />     
            Time: <%=  request.getAttribute("time")%>
            <br />
            Created by:<%=   request.getAttribute("author")%>        
        </p> 
        
	<br /> 
        <br />
        
        <p style="margin:10px"> This has been rated: </p>   
      
        <iframe src="/csc435WebApp/viewRate"  width="30" height="30"></iframe>
        <br /> 
        <br /> 
        Description:
        <p style="padding:10px">      
            
            <%= request.getAttribute("description")%>
        </p>
      
        <form method="get" action="rate" style="background-color:white">
            <input type="radio" name="rating"  value="1" >1
            <input type="radio" name="rating"  value="2" >2
            <input type="radio" name="rating"  value="3" >3
            <input type="radio" name="rating"  value="4" >4
            <input type="radio" name="rating"  value="5" >5
        <br /> 
        <br />
        <input type="submit" value="rate">      
        </form>    
        
        <br />
        <br />
        
        <form style="background-color:white; " method="get" action="comment">               
        <fieldset>
            <legend>Leave a Comment: </legend>
            <textarea rows="10" cols="50" name="userComment"></textarea>
        </fieldset>
   
        <input  type="submit" value="Comment!" >                      
        </form>      
         
        <br />
        <br />
        <p> here are the comments: </p>       
            <iframe src="/csc435WebApp/viewcomment"  width="500" height="300"></iframe> 
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
