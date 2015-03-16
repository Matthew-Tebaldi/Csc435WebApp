<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>

<% ArrayList<String> names = (ArrayList<String>)request.getAttribute("eventNames"); %>
<% ArrayList<String> whens = (ArrayList<String>)request.getAttribute("whens"); %>
<% ArrayList<String> authors = (ArrayList<String>)request.getAttribute("authors"); %>
<% ArrayList<String> descriptions = (ArrayList<String>)request.getAttribute("descriptions"); %>
<% ArrayList<String> tags = (ArrayList<String>)request.getAttribute("tags"); %>
<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <title>event names</title>
</head>

<body style="background-color:green">
  
<% for(int i=0; i < names.size(); i++){
%>  Name:<%= names.get(i)%>
    <br/><br/>
   When: <%= whens.get(i)%>
   <br/><br/>
   Created by:  <%= authors.get(i)%>
    <br/><br/>
    Description: <%= descriptions.get(i)%>
    <br/><br/>
    category: <%= tags.get(i)%>
    <br/><br/><hr>
<%}%>
    
    
<a href='/csc435WebApp/twitter'>home</a>


</body>
</html>