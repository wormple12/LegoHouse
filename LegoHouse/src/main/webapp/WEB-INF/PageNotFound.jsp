<%-- 
    Document   : PageNotFound
    Created on : Mar 12, 2019, 3:19:05 PM
    Author     : Simon Asholt Norup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Not Found</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/LegoHouse/legohouse.css">
    </head>
    <body>

        <h1>Error 404!</h1>
        <p>Path not found.</p>

        <jsp:include page="/includes/ErrorMsg.jsp" />
    </body>
</html>
