<%@ page language="java" contentType="text/html"%>
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
            	padding:30px;	 
                font-size:200%;
            }
            section{
                width:900px;       	
            	padding:10px;	 	 
            }
        </style>
</head>

<body style="background-color:green">
    <header>   
<h>We have found your event:</h>
   
<h2>
    <form method="post" action="eventYouLike">
        <input type="submit" value="Home">
    </form>    
</h2>   
    </header>
    <br />
    <section style="background-color:white; text-align:center">
           <p>            
            Event: <%=  session.getAttribute("eventName")%>
            <br />
            Date: <%=  request.getAttribute("date")%>
            <br />     
            Time: <%=  request.getAttribute("time")%>
            <br />
            Created by:<%=   request.getAttribute("author")%>
            <br /> <br />
        </p> 
       
        <p> This has been rated: </p>   
      
        <iframe src="/csc435WebApp/viewRate"  width="30" height="30"></iframe>
        <br /> 
        <br /> 
        Description:
        <p style="padding:10px">      
            
            <%= request.getAttribute("description")%>
        </p>
               
      
         <form method="get" action="rate" style="background-color:white; text-align:center">
            <input type="radio" name="rating"  value="1" >1
            <input type="radio" name="rating"  value="2" >2
            <input type="radio" name="rating"  value="3" >3
            <input type="radio" name="rating"  value="4" >4
            <input type="radio" name="rating"  value="5" >5
        <br /> 
        <br />
        <input type="submit" value="rate">      
        </form>    
        
        
        <form style="background-color:white; padding:20px; width:350px; float:left" method="post" action="comment">               
        <fieldset>
            <legend>Leave a Comment: </legend>
            <textarea rows="2" cols="30" name="userComment"></textarea>
        </fieldset>
   
        <input  type="submit" value="Comment!" >
        <br />
        <br />                  
        </form>      
        
               <p> here are the comments: </p>
       
        <iframe src="/csc435WebApp/viewcomment"  width="500" height="300"></iframe>
     
    </section> 
         




</body>
</html>

