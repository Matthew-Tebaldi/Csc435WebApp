<!DOCTYPE html>
    <% String[] messageArray = (String[]) request.getAttribute("messageArray"); %>
    <% String[] authorArray = (String[]) request.getAttribute("authorArray"); %>
<html>
    <head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>Messaging</title>
    </head>
    <body>  
        
        <% for(int i=0; i<100; i++){
        if(messageArray[i]!=null){ %>
        <%= authorArray[i] %>
        said: <%= messageArray[i]%>
        <br/>
        <%}}%>

        <p><a href='/csc435WebApp/twitter'>Home</a></p> 
    </body></html>           
        
