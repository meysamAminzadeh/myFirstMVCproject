<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 5/1/2020
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main</title>
</head>
<body>


<TABLE BORDER="1">
    <TR>
        <TH>email</TH>
        <TH>password</TH>
    </TR>
    <%
        try{
        Map<String,String> map= (Map<String, String>)
                request.getAttribute("ResultSetMap");

        if(!map.isEmpty()) {
            Object[] set= map.keySet().toArray();
            for (int i=0;i<set.length;i++){

           // Use 'do...while' to process the first row, while continuing to process remaining rows

    %>
    <TR>
        <TD> <%= (String)set[i] %></td>
        <TD> <%= map.get((String)set[i]) %></td>
   </TR>
    <%  }
    }
    }catch(Exception e){
        out.print(request.getAttribute("Result"));
    }
    %>
</TABLE>
</body>
</html>
