<!DOCTYPE html>
<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <title>Add</title>

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
    	width:900px;
    	float:right;
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
<h1>Add an events</h1>

<h2>
    <form method="post" action="eventYouLike">
        <input type="submit" value="Home">
    </form>
    
</h2>
</header>

      
<section style="float:left">
        
    <form style="background-color:white; padding:20px; width:700px" method="get" action="addEvent">
        <fieldset style="padding:10px">
        <legend>Add Event</legend>
        
        Name: <textarea rows="1" cols="30" name="eventName"></textarea>       
     

        Date: <input type="date" name="date" />
     
        Time: <input type="time" name="time"/>
        <br />
        <br />     
        Description:   
        <br />
        <textarea rows="5" cols="50" name="description"></textarea>
        <br />
        <br />
        Pick which most describes your event:
        <br />
        <br />
        <input type="radio" name="tags" value="Art">Art
     
        <input type="radio" name="tags" value="Lectures">Lectures
      
        <input type="radio" name="tags" value="Movies">Movies
    
        <input type="radio" name="tags" value="Nature">Nature
     
        <input type="radio" name="tags" value="Sports">Sports
     
        <input type="radio" name="tags" value="music">Music
   
        <input type="radio" name="tags" value="Social">Social
   
        <input type="radio" name="tags" value="Other">Other
        <br />
        <br />
        <input type="submit" value="Add" />            
            </fieldset>
            <br />
        </form>
    
    </section> 

</body>
</html>
