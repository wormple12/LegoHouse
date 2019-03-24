<%-- 
    Document   : About
    Created on : 24-03-2019, 19:55:34
    Author     : Simon Asholt Norup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lego House Generator</title>
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
                <p>This is a Web Project where you can order a bill of materials for a lego house of any given dimensions. Includes login/registration functionality.
                    It is the result of an educational project.</p>

                <p>The purpose was to gain understanding of:</p>
                <ul>
                    <li>The concept of backend and frontend programming.</li>
                    <li>Connecting to and using droplets, herein deployment of .war files on a server.</li>
                    <li>Retrieval and removal of SQL data on a MySQL database located on a server.</li>
                    <li>Architecture and design principles, including multi-layer architecture (data, logic and presentation layers).</li>
                </ul>
                <p>Furthermore, the project included introductions to: HTML, CSS, .jsp sites and more.</p>
            </div>
        </div>
    </body>
</html>
