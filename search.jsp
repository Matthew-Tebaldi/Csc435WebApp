<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>Search</title>       
    <style>
            header {
                background-color:green;
            	color:yellow;
            	text-align:center;
            	padding:30px;	 
                font-size:200%;
            }
            section {
                columns:100px 3;
                
                    
            }
           
        </style>
</head>

<body style="background-color:green">
    
    <% Boolean eventNotFound = false; %>
    <% if(request.getAttribute("eventNotFound")!=null){
        eventNotFound = (Boolean)request.getAttribute("eventNotFound");
        }
        if(eventNotFound){ %>
                That name is not in our system.
            <br/>
            <br/>
            <br/>
            <br/>
            
            <form style="color: black; font-size: small" method="get" action="search">              
                Try again: <textarea  rows="1" cols="30" name="searchEvent"></textarea>                    
                <input type="submit" value="Search" />                   
            </form>  
            
            <br/>
            <br/>
               <a href='/csc435WebApp/twitter'>Home</a>     
    <%} else { %>
    
    <header>   
<h>We have found your event:</h>
   
<h2>
    <a href='/csc435WebApp/twitter'>Home</a>      
</h2>   
    </header>
   
    <section style="background-color:white; text-align:center">
         <br /> <br />  
        <p>            
            Event: <%=  session.getAttribute("eventName")%>
            <br />    <br />
            When: <%=  request.getAttribute("time")%>
            <br />    <br />
            Created by:<%=   request.getAttribute("author")%>
            <br /> <br />
        </p> 
        
        <p> This has been rated: </p> 
        
            <% int eRate = 6; %>
            <% if(request.getAttribute("eRateAvr")!=null){
            eRate = (int) request.getAttribute("eRateAvr");
            }%>
            
            <%if(eRate!=6){%>
                <p> <%=eRate%> </p>
            <%}%>
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
          <br/><br/>
        <form style="background-color:white" method="post" action="comment">               
        <fieldset>
            <legend>Leave a Comment: </legend>
            <textarea rows="2" cols="30" name="userComment"></textarea>
        </fieldset>
   
        <input  type="submit" value="Comment!" >
        <br />
        <br />                  
        </form> 
        
        <p style="background-color:yellow"> here are the comments: </p>   
        
            <% String[] cList = (String[]) request.getAttribute("cList"); %>
            <% String[] aList= (String[]) request.getAttribute("aList"); %>
      
        <% for(int i=0; i<100; i++){
        if(cList[i]!=null){ %>     
            <p style="background-color: yellow"> <%= aList[i] %>
                said: <%= cList[i]%>
            </p>     
            <hr>
        <%}}%>    
    </section> 
    
    <% } %>
    
</body>
</html>

