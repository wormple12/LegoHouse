<%-- 
    Document   : RegisterUser
    Author     : Simon Asholt Norup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register New user</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/LegoHouse/legohouse.css">
    </head>
    <body>
        <jsp:include page="/includes/LoginTopMenu.jsp" />

        <h1> Create new user </h1>

        <div class="panel panel-default">
            <div class="panel-body">
                <form name="registerform" action="/LegoHouse/c/registration" method=POST>
                    Username: <br> <input type=text name=username style="color: black"> <br> <br>
                    Password: <br> <input type= password name=password style="color: black" oninput="update()"> <br> 
                    Confirm password: <br> <input type= password name=password2 style="color: black" oninput="update()"> <p id="pwMSG" class="error"></p> <br> 
                    Email: <br> <input type= text name=email style="color: black" oninput="update()"> <p id="emailMSG" class="error"></p> <br> <br>
                    <input class="btn btn-primary" id="submitinput" type=submit value="Register" disabled>
                </form>

                <script>
                    function update() {
                        if (!checkPasswords()) {
                            document.getElementById("pwMSG").innerHTML = "The entered passwords are not alike.";
                            toggleSubmit(false);
                        } else {
                            document.getElementById("pwMSG").innerHTML = "";
                            if (!validateEmail()) {
                                document.getElementById("emailMSG").innerHTML = "An email must match the pattern '?@?.?'.";
                                toggleSubmit(false);
                            } else {
                                document.getElementById("emailMSG").innerHTML = "";
                                toggleSubmit(true);
                            }
                        }
                    }
                    function checkPasswords() {
                        var pw1 = document.registerform.password.value;
                        var pw2 = document.registerform.password2.value;
                        return (pw1 === pw2);
                    }
                    function validateEmail() {
                        var email = document.registerform.email.value;
                        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                        return email.match(mailformat);
                    }
                    function toggleSubmit(toggle) {
                        document.getElementById("submitinput").disabled = (!toggle);
                    }
                </script>
                <br>
                <jsp:include page="/includes/ErrorMsg.jsp" />
            </div>
        </div>
    </body>
</html>