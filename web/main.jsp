<%@ page import="java.sql.ResultSet" %><%--
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
        <TH>ID</TH>
        <TH>email</TH>
        <TH>userName</TH>
        <TH>password</TH>
    </TR>
    <%
        ResultSet resultset= (ResultSet) request.getAttribute("ResultSet");
        if(resultset.next()) {
            do { // Use 'do...while' to process the first row, while continuing to process remaining rows

    %>
    <TR>
        <TD> <%= resultset.getString(1) %></td>
        <TD> <%= resultset.getString(2) %></td>
        <TD> <%= resultset.getString(3) %></TD>
        <TD> <%= resultset.getString(4) %></TD>
    </TR>
    <%  } while (resultset.next());
    }
    else{
        out.print(request.getAttribute("Result"));
    }
    %>
</TABLE>
</body>
</html>
