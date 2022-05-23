<%@page import="com.sanvalero.coffea.domain.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.dao.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link href="../css/style-profile.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <%
            CustomerDAO customerDAO = new CustomerDAO();
            ArrayList<Customer> customerList = customerDAO.getCustomers();
            int loggedUserID = 0;
            Customer loggedCustomer = null;
            ArrayList<Address> customerAddresses = new ArrayList<>();
            int orders = 0;
            if (request.getParameter("user") != null) {
                loggedUserID = Integer.parseInt(request.getParameter("user"));
            }
            for (Customer customer : customerList) {
                if (customer.getUserID() == loggedUserID) {
                    loggedCustomer = customer;
                }
            }

            if (loggedCustomer != null) {
                CartDAO cartDAO = new CartDAO();
                ArrayList<Cart> cartList = cartDAO.getCarts();
                for (Cart cart : cartList) {
                    if (cart.getCustomer().getUserID() == loggedCustomer.getUserID()) {
                        orders++;
                    }
                }
                AddressDAO addressDAO = new AddressDAO();
                ArrayList<Address> addressList = addressDAO.getAddresses();
                for (Address address : addressList) {
                    if (address.getAddressID() == loggedCustomer.getAddress().getAddressID()) {
                        customerAddresses.add(address);
                    }
                }
        %>
        <div class="header">
            <img src="../imagenes/logo.png" class="logoarr" alt="logo">
            <div class="header-right">
                <a href="index.jsp">HOME</a>
                <a href="productos.jsp">PRODUCTS</a>
                <a href="sobrenosotros.html">ABOUT US</a>
                <div class="caja">
                    <div class="container-1">
                        <img src="../imagenes/lupa.png" class="lupa" alt="lupa">
                        <input type="search" id="search" placeholder="Search..." />
                    </div>
                    <div class="enlaces">
                        <a href="carrito.jsp"><img src="../imagenes/carrito.png" class="carrito" alt="carrito"></a>
                        <a class="active" href="<%if (loggedUserID != 0) {%><%= "profile.jsp?user=" + loggedUserID%><%} else {%><%= "login.jsp"%><%}%>"><img src="../imagenes/iniciar-sesion.png" class="iniciosesion"
                                                                                                                                                             alt="iniciosesion"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="izquierda">
                <img src="../imagenes/foto_perfil.jpg" class="fotoperfil" alt="fotoperfil">
                <div class="cambia">
                    Change your photo here!
                </div>
            </div>
            <div class="derecha">
                <h1>INFORMATION</h1>
                <div class="dato">
                    FIRST NAME: <%= loggedCustomer.getName()%>
                </div>
                <div class="dato">
                    LAST NAME: <%= loggedCustomer.getLastName()%>
                </div>
                <div class="dato">
                    EMAIL: <%= loggedCustomer.getEmail()%>
                </div>
                <div class="dato">
                    LOCATION: <% for (Address customerAddress : customerAddresses) {
                    %> <%= customerAddress.getStreetName() + " " + customerAddress.getStreetNumber() + " " + customerAddress.getAppartment()%> <%
                        }
                    %>
                </div>
                <div class="dato">
                    NUMBER OF ORDERS: <%= orders%>
                </div>
            </div>
        </div>

        <div class="abajo">
            <div class="colores">
                <img src="../imagenes/separador.png" class="separador" alt="separador">
            </div>
            <div class="contactanos">
                <div class="contactanos-coffea">
                    <h3>COFFEA</h3>
                    <p>
                        Our company was made to offer our customers the best coffe in the world.
                        Our world renowned coffe has a 100% satisfaction rate among our customers.
                    </p>
                    <ul class="socials">
                        <li><a href="#"><img src="../imagenes/twitter.png" class="i"></a></li>
                        <li><a href="#"><img src="../imagenes/facebook.png" class="i"></a></li>
                        <li><a href="#"><img src="../imagenes/instagram.png" class="i"></a></li>
                        <li><a href="#"><img src="../imagenes/youtube.png" class="i"></a></li>
                        <li><a href="#"><img src="../imagenes/pinterest.png" class="i"></a></li>
                    </ul>
                </div>
            </div>
            <div class="colores">
                <img src="../imagenes/separador.png" class="separador2" alt="separador2">
            </div>
        </div>
        <% }%>
    </body>
</html>