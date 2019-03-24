<%-- 
    Document   : MainPage
    Author     : Simon Asholt Norup
--%>

<%@page import="com.mycompany.legohouse.logic.help_classes.User"%>
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
        <jsp:include page="/includes/MainTopMenu.jsp" />

        <h1> Welcome to the Lego House Generator! </h1>
        <%
            User user = (User) request.getSession().getAttribute("user");
            String username = user.getUsername();

            out.println("<h3>You are logged in as    : " + username + "</h3>");
        %>
        <p> Pass the desired dimensions and we will calculate a bill of materials for you. </p>

        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/LegoHouse/c/partlist" method=POST>
                    <input type=hidden name=origin value="calculator">
                    Length: <br> <input type=number name=length style="color: black"> <br> 
                    Width: <br> <input type=number name=width style="color: black"> <br>
                    Height: <br> <input type=number name=height style="color: black"> <br> <br>
                    <input class="btn btn-primary" type=submit value="Calculate">
                </form>
                <br>
                <button onclick="window.location.href = '/LegoHouse/c/orders';">
                    <%
                        Boolean admin = user.getAdmin();
                        if (admin) {
                            out.println("See all orders.");
                        } else {
                            out.println("My orders");
                        }
                    %>
                </button>
                <button onclick="window.location.href = '/LegoHouse/c/logout';">Log out.</button>
                <br>
                <jsp:include page="/includes/ErrorMsg.jsp" />
            </div>
        </div>
    </body>
</html>
