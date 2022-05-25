<%@page import="com.sanvalero.coffea.domain.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.dao.*"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="../css/style-register.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>

    <body>
        <%
            CustomerDAO customerDAO = new CustomerDAO();
            ArrayList<Customer> customerList = customerDAO.getCustomers();
            Customer newCustomer = (Customer) application.getAttribute("newCustomer");
            if (request.getParameter("firstname") != null && request.getParameter("lastname") != null && request.getParameter("email") != null && request.getParameter("password") != null) {
                for (Customer customer : customerList) {
                    if (customer.getEmail() != null) {

                        if (!request.getParameter("email").equals(customer.getEmail())) {
                            newCustomer = new Customer(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"), request.getParameter("password"));
                            session.setAttribute("registeringCustomer", newCustomer); %>
        <jsp:forward page="./register2.jsp"> 
            <jsp:param name="newCustomer" value="newCustomer"/>
        </jsp:forward >
        <% }
                    }
                }
            }
        %>
        <div class="container">
            <div class="detras">
                <div class="signupMsg">
                    <div class="texto">
                        <p class="titulo">Have an account?</p>
                        <p>Log in to see all your cart.</p>
                        <button><a href="login.html">Log In</a></button>
                    </div>
                </div>
            </div>
            <div class="delante">
                <div class="signup">
                    <h2>SIGN UP</h2>
                    <form action="register.jsp" method="post">
                        <div class="inputs">
                            <input type="text" name="firstname" placeholder="  FIRST NAME">
                            <input type="text" name="lastname" placeholder="  LAST NAME">
                            <input type="text" name="email" placeholder="  EMAIL">
                            <input type="password" name="password" placeholder="  PASSWORD">
                        </div>
                        <input type="submit" name="submit" id="next" value="SIGUIENTE">
                    </form>

                </div>
            </div>
        </div>
    </body>

</html>
</body>