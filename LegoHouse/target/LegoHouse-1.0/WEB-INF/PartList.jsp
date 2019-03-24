
<%@page import="java.util.LinkedList"%>
<%@page import="com.mycompany.legohouse.logic.help_classes.Part"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill of Materials</title>
        <link rel="stylesheet" type="text/css" href="/LegoHouse/legohouse.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="/includes/MainTopMenu.jsp" />

        <h2> Bill of Materials for a House with dimensions: 
            <%
                String length = request.getParameter("length");
                String width = request.getParameter("width");
                String height = request.getParameter("height");
                out.print(length + " x ");
                out.print(width + " x ");
                out.print(height);
            %>
        </h2>

        <div class="panel panel-default">
            <div class="panel-body">
                <%        
                    LinkedList<LinkedList<Part>> partlists = (LinkedList<LinkedList<Part>>) request.getSession().getAttribute("parts");
                    
                    int level = 1;
                    for (LinkedList<Part> partlist : partlists) {
                        out.print("<h3> Height Level " + level++ + ": </h3>");
                %>
                <table border = "1">
                    <tr>
                        <td>Brick</td>
                        <td>Side 1</td>
                        <td>Side 2</td>
                        <td>Side 3</td>
                        <td>Side 4</td>
                        <td>Total</td>
                    </tr>
                    <%        
                        for (Part p : partlist) {
                    %>
                    <tr>
                        <td><%=p.getType()%></td>
                        <td><%=p.getSide1()%></td>
                        <td><%=p.getSide2()%></td>
                        <td><%=p.getSide3()%></td>
                        <td><%=p.getSide4()%></td>
                        <td><%=p.getTotal()%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <br>
                <%    
                    }
                    
                    String origin = (String) request.getParameter("origin");
                    if (origin.equals("calculator")) {
                %>
                <form action="/LegoHouse/c/addorder" method=POST>
                    <input type="hidden" name="origin" value="ordered"/>
                    <input type="hidden" name="length" value="<%=length%>"/>
                    <input type="hidden" name="width" value="<%=width%>"/>
                    <input type="hidden" name="height" value="<%=height%>"/>
                    <button type="submit"> Order this house! </button>
                </form>
                <%
                    } else if (origin.equals("ordered")) {
                        out.println("<p> The house materials have been ordered. Check your list of orders from the main page at any time to see the details of the order. </p>");
                        out.println("<p> On that page it also becomes updated to show when your order has been shipped. </p>");
                    }
                %>
                <br>
                <% if (origin.equals("listed")) { %>
                <button onclick="window.location.href = '/LegoHouse/c/orders';">Return to the list of orders.</button>
                <% } else { %>
                <button onclick="window.location.href = '/LegoHouse/c/main';">Return to the main page.</button>
                <% }%>
            </div>
        </div>
    </body>
</html>
