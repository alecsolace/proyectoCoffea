<%@page import="com.sanvalero.coffea.dao.AddressDAO"%>
<%@page import="com.sanvalero.coffea.domain.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.domain.Customer"%>
<%@page import="com.sanvalero.coffea.dao.CustomerDAO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="../css/style-register2.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>

    <body>
        <%
            CustomerDAO customerDAO = new CustomerDAO();
            Customer newCustomer = (Customer) session.getAttribute("newCustomer");
            newCustomer.setUserID(customerDAO.getCustomers().size() + 1);
            AddressDAO addressDAO = new AddressDAO();
            if (request.getParameter("direccion") != null && request.getParameter("numero") != null && request.getParameter("cp") != null) {
                Address newAddress = new Address(request.getParameter("direccion"), Integer.parseInt(request.getParameter("numero")), request.getParameter("cp"));
                newCustomer.setAddress(newAddress);
        %>El CustomerID = <%= newCustomer.getUserID()%> 
        El AddressID = <%= newCustomer.getAddress().getAddressID()%> 
        El Nombre = <%= newCustomer.getName()%> 
        <% int addressRows = addressDAO.addAddress(newAddress);
            if (addressRows > 0) {
                int customerRows = customerDAO.addUser(newCustomer);
                if (customerRows > 0) {
                    session.setAttribute("user", newCustomer.getUserID());
        %>
        <jsp:forward page="./index.jsp"> 
            <jsp:param name="newCustomer" value="newCustomer"/>
        </jsp:forward >
        <%
        } else {
        %>No ha creado el usuario<%
            }
        } else {
        %>No ha creado el Address<%
            }
        } else {
        %> No tengo ni idea bro <%
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
                    <form action="register2.jsp" method="post">
                        <div class="inputs">
                            <input type="text" name="direccion" placeholder="  DIRECCION">
                            <input type="text" name="numero" placeholder="  NUMERO">
                            <input type="text" name="cp" placeholder="  CP">
                        </div>
                        <input type="submit" name="submit" id="signUp" value="SIGN UP">
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
</body>