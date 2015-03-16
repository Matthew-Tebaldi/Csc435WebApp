<!DOCTYPE html>

<% Boolean alreadyRated = false; %>
<% if(request.getAttribute("alreadyRated")!=null){
    alreadyRated = (Boolean) request.getAttribute("alreadyRated");
}%>

<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <title>Rate</title>
</head>

<body style="background-color:white">
   <%if(alreadyRated){%>
   <p> Sorry but you already rated this event </p>        
    <br/>
    <br/>
    
<a href='/csc435WebApp/twitter'>home</a>
<%}%>

</body>
</html>