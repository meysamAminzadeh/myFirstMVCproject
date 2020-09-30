<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 4/30/2020
  Time: 5:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Index</title>
  </head>
  <body>
   <form action="/CheckUser.java">



     <label> Email : </label>
     <input type="text" name="email" >
     <br>

     <label> UserName : </label>
     <input type="text" name="username" value= <% out.print(request.getParameter("username"));%> >
     <br>

     <label> Password: </label>
     <input type="password" name="password">

     <br>
     <input type="submit" value="insert">


   </form>

  <%
    try {
       if(!(request.getAttribute("Error")==null))
       out.print(request.getAttribute("Error"));
    }
    catch(NullPointerException e){

    }
  %>
   <a href="login.jsp">login</a>
  </body>
</html>
