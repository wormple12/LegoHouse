<%-- 
    Document   : Login
    Created on : Mar 12, 2019, 2:34:47 PM
    Author     : Simon Asholt Norup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/LegoHouse/legohouse.css">
    </head>

    <body>
        <jsp:include page="/includes/LoginTopMenu.jsp" />

        <h1> Welcome to Lego House Generator! </h1>
        <h4> by Simon Asholt Norup </h4>

        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/LegoHouse/c/login" method=POST>
                    Username: <br> <input type=text name=username style="color: black"> <br> 
                    Password: <br> <input type=password name=password style="color: black"> <br> <br>
                    <input class="btn btn-primary" type=submit value="Log In">
                </form>
                <br>
                <jsp:include page="/includes/ErrorMsg.jsp" />
            </div>
        </div>
    </body>
</html>
