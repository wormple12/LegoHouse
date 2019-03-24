<%-- 
    Document   : ErrorMsg
    Created on : 24-03-2019, 09:38:21
    Author     : Simon Asholt Norup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Message</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/LegoHouse/legohouse.css">
    </head>
    <body>
        <%
            Exception error = (Exception) request.getAttribute("error");
            String errormessage = "";
            if (error != null) {
                errormessage = error.getLocalizedMessage();
                if (errormessage == null) {
                    errormessage = "An unknown error occurred. Please contact support.";
                }
            }
            out.println("<p class=\"error\" >" + errormessage + "</p>");
        %>
    </body>
</html>
