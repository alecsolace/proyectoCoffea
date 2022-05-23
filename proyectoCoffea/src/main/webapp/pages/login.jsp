<%@page import="com.sanvalero.coffea.domain.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.dao.CustomerDAO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="../css/style-login.css" rel="stylesheet" type="text/css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>

    <body>
        <%
            CustomerDAO customerDAO = new CustomerDAO();
            ArrayList<Customer> customerList = customerDAO.getCustomers();
            boolean loggedIn = false;
            Customer loggedCustomer = null;
            if (request.getParameter("email") != null && request.getParameter("password") != null) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                for (Customer registeredCustomer : customerList) {
                    if (registeredCustomer.getEmail().equals(email) && registeredCustomer.getPassword().equals(password)) {
                        loggedIn = true;
                        loggedCustomer = registeredCustomer;
                    }
                }
            }
            if (loggedIn && loggedCustomer != null) {
                session.setAttribute("user", loggedCustomer.getUserID());
        %> <jsp:forward page="./index.jsp"> 
            <jsp:param name="newCustomer" value="newCustomer"/>
        </jsp:forward >
        <%
            }
            if (!loggedIn && request.getParameter("email") != null && request.getParameter("password") != null) {
        %>
        <div class="deleteMessage">The email and password entered do not match with any registered users</div>
        <%} %>
        <div class="container">
            <div class="detras">
                <div class="loginMsg">
                    <div class="texto">
                        <p class="titulo">Don't have an account?</p>
                        <p>Sign up to have many discounts.</p>
                        <button><a href="./register.jsp">Sign Up</a></button>
                    </div>
                </div>
            </div>

            <div class="delante">
                <div class="login">
                    <h2>LOG IN</h2>
                    <form action="./login.jsp" method="post">
                        <div class="inputs">
                            <input class="input" type="email" name="email" placeholder="  EMAIL">
                            <input class="input" type="password" name="password" placeholder="  PASSWORD">
                        </div>
                        <p>FORGET PASSWORD?</p>
                        <input type="submit" name="submit" id="next" value="Login">
                    </form>
                </div>
                <%
                %>
            </div>
        </div>
    </body>

</html>