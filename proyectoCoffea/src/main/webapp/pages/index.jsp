
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.sanvalero.coffea.domain.*" %>
<%@page import="com.sanvalero.coffea.dao.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Coffea</title>
        <link href="../css/style-home.css" rel="stylesheet" type="text/css">
        <script src="../js/script2.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap"
              rel="stylesheet">
    </head>

    <body>
        <%

            if (request.getParameter("checkout") != null) {
                ArrayList<CartLine> cartLinesCheckout = new ArrayList<>();
                cartLinesCheckout = (ArrayList<CartLine>) session.getAttribute("cartLines");
                Cart cartCheckout = null;
                cartCheckout = (Cart) session.getAttribute("finalCart");
                CartDAO cartDAO = new CartDAO();
                CartLineDAO cartLineDAO = new CartLineDAO();
                int insertedLines = 0;

                if (cartLinesCheckout != null && !cartLinesCheckout.isEmpty() && cartCheckout != null) {
                    insertedLines = cartDAO.addCart(cartCheckout);
                    if (insertedLines > 0) {

                        for (CartLine cartLine : cartLinesCheckout) {
                            cartLineDAO.addCartLine(cartLine);
                        }
                    }
                }

                if (insertedLines > 0) {
                    application.setAttribute("carrito", null);

        %>
        <jsp:forward page="./index.jsp"> 
            <jsp:param name="status" value="ok"/>
        </jsp:forward >
        <%        } else {
        %>
        <jsp:forward page="./carrito.jsp"> 
            <jsp:param name="status" value="error"/>
        </jsp:forward >
        <%
                }

            }

            CartLineDAO cartLinesDAO = new CartLineDAO();
            ArrayList<CartLine> cartLines = (ArrayList<CartLine>) application.getAttribute("carrito");
            int loggedUserID = 0;

            if (request.getParameter(
                    "logout") != null) {
                session.setAttribute("user", null);
            }

            if (request.getParameter(
                    "param") != null) {
                CustomerDAO customerDAO = new CustomerDAO();
                AddressDAO addressDAO = new AddressDAO();
                int deletingUserID = Integer.parseInt(request.getParameter("param"));
                int rows = 0;
                Address deletingAddress = null;
                for (Address address : addressDAO.getAddresses()) {
                    if (address.getCustomer().getUserID() == deletingUserID) {
                        deletingAddress = address;
                    }
                }
                if (deletingAddress != null) {
                    rows = addressDAO.removeAddress(deletingAddress.getAddressID());
                } else {

                }
                if (rows > 0) {
                    rows = customerDAO.removeCustomer(deletingUserID);
                } else {

                }
                if (rows > 0) {
        %>
        <div class="deleteMessage">The user was successfully deleted</div>

        <%
                }

            }
            if (cartLines
                    == null) {
                cartLines = new ArrayList<>();
            }

            if (session.getAttribute(
                    "user") != null) {
                loggedUserID = (int) (session.getAttribute("user"));
            }

            application.setAttribute(
                    "carrito", cartLines);

        %>
        <div class="padre">
            <div class="header">
                <img src="../imagenes/logo.png" class="logoarr" alt="logo">
                <div class="header-right">
                    <a class="active" href="index.jsp">HOME</a>
                    <a href="productos.jsp">PRODUCTS</a>
                    <a href="sobrenosotros.html">ABOUT US</a>
                    <div class="caja">
                        <div class="container-1">
                            <img src="../imagenes/lupa.png" class="lupa" alt="lupa">
                            <input type="search" id="search" placeholder="Search..." />
                        </div>
                        <div class="enlaces">
                            <a href="carrito.jsp"><img src="../imagenes/carrito.png" class="carrito" alt="carrito"></a>
                            <a  href="<%if (loggedUserID
                                            != 0) {%><%= "profile.jsp?user=" + loggedUserID%><%} else {%><%= "login.jsp"%><%}%>"><img src="../imagenes/iniciar-sesion.png" class="iniciosesion"
                                   alt="iniciosesion"></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="arr">
                <div class="parteizqarr">
                    <div class="izq">
                        <img src="../imagenes/cafe1.png" class="imgizq" alt="cafe">
                    </div>
                    <div class="der">
                        <div class="derab">
                            <div class="bestseller">
                                BEST SELLER
                            </div>
                            <div class="productoprincipal">
                                <img src="../imagenes/coffeeBag.png" class="cafedelmedio"
                                     alt="cafemedio">
                                <%
                                    ProductDAO productDAO = new ProductDAO();
                                    ArrayList<Product> productList = productDAO.get_products();
                                    Product bestSeller = productDAO.getBestSeller();
                                    DecimalFormat df = new DecimalFormat("0.00");
                                %>
                                <p class="bestSeller name"> <%= bestSeller.getName()%> </p> 




                            </div>
                            <div class="infoproducto">
                                <%= bestSeller.getDescription()%>
                            </div>
                            <div class="precioshop">
                                $ <%= df.format(bestSeller.getPrice())%>

                                <div class="shopnow flex">
                                    <a class="flex" href="./productDetail.jsp?param=<%=bestSeller.getProductID()%>"> <p>SHOP NOW</p>
                                        <img src="../imagenes/flecha-apuntando-hacia-la-izquierda-dentro-de-un-circulo (1).png"
                                             class="flecha" alt="flecha"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="partederarr">

                    <img src="../imagenes/custom_header.jpg" class="imgprincipal" alt="cafetal">
                    <div class="infoimgprincipal">
                        <h3>ETHICAL SOURCING</h3>
                        Our amazing coffee would not be possible without farmers all around the world.
                        That's why we work closely with them in order to ensure sustainable and ethically
                        sourced coffee.

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
        </div>
    </body>

</html>