
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.mycompany.legohouse.logic.help_classes.House"%>
<%@page import="com.mycompany.legohouse.logic.help_classes.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.legohouse.logic.help_classes.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>   
        <link rel="stylesheet" type="text/css" href="/LegoHouse/legohouse.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="/includes/MainTopMenu.jsp" />

        <h2> List of Orders: </h2>

        <div class="panel panel-default">
            <div class="panel-body">
                <table border = "1">
                    <tr>
                        <td> Order ID </td>
                        <td> Customer </td>
                        <td> House Dimensions </td>
                        <td> Ordered </td>
                        <td> Shipped </td>
                        <td> Bill of Materials </td>
                    </tr>
                    <%
                        ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
                        User user = (User) request.getSession().getAttribute("user");
                        boolean isAdmin = user.getAdmin();

                        for (Order order : orders) {
                            House house = order.getHouse();
                            LocalDateTime shipped = order.getShipped();
                    %>
                    <tr>
                        <td><%=order.getID()%></td>
                        <td><%=order.getCustomer()%></td>
                        <td><%="" + house.getLength() + " x " + house.getWidth() + " x " + house.getHeight()%></td>
                        <td><%=order.getOrdered().toString()%></td>

                        <% if (shipped != null) {%>
                        <td><%=shipped.toString()%></td>
                        <% } else if (isAdmin) {%>
                        <td>
                            <form action="/LegoHouse/c/orders" method=POST>
                                <input type="hidden" name="ship_id" value="<%=order.getID()%>"/>
                                <button type="submit"> Mark as shipped </button>
                            </form>
                        </td>
                        <% } else { %>
                        <td> Not Shipped </td>
                        <% }%>

                        <td>
                            <form action="/LegoHouse/c/partlist" method=POST>
                                <input type="hidden" name="origin" value="listed"/>
                                <input type="hidden" name="length" value="<%=house.getLength()%>"/>
                                <input type="hidden" name="width" value="<%=house.getWidth()%>"/>
                                <input type="hidden" name="height" value="<%=house.getHeight()%>"/>
                                <button type="submit"> View </button>
                            </form>
                        </td>
                    </tr>
                    <%  }%>
                </table>
                <br>
                <button onclick="window.location.href = '/LegoHouse/c/main';">Return to the main page.</button>
            </div>
        </div>
    </body>
</html>

