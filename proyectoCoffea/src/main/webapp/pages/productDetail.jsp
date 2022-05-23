<%@page import="com.sanvalero.coffea.domain.Product"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.sanvalero.coffea.domain.Category"%>
<%@page import="com.sanvalero.coffea.dao.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.dao.ProductDAO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Producto</title>
        <link href="../css/style-productoexppruebas.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>

    <body>
        <% ProductDAO productDAO = new ProductDAO();
            ArrayList<Product> products = productDAO.get_products();
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> categories = categoryDAO.get_categories();
            DecimalFormat df = new DecimalFormat("0.00");
            int selectedProductID = Integer.parseInt(request.getParameter("param"));
            int cont = 1;
            int loggedUserID = 0;
            if (session.getAttribute("user") != null) {
                loggedUserID = (int) (session.getAttribute("user"));
            }
            for (Product product : products) {
                if (product.getProductID() == selectedProductID) {

                    for (Category category : categories) {
                        if (category.getCategoryID() == product.getCategory().getCategoryID()) {
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
                        <a href="<%if (loggedUserID != 0) {%><%= "profile.jsp?user=" + loggedUserID%><%} else {%><%= "login.jsp"%><%}%>"><img src="../imagenes/iniciar-sesion.png" class="iniciosesion"
                                                                                                                                              alt="iniciosesion"></a>
                    </div>
                </div>
            </div>
        </div>
        <main>
            <div class="container">
                <div class="grid navegacion">
                    <div class="ruta">
                        <nav>
                            <ol class="listaruta">
                                <li class="sitioruta"><a id="linkRuta" href="./index.jsp">Home</a></li>
                                <li class="sitioruta"><a id="linkRuta" href="./productos.jsp"> Products</a></li>
                                <li class="sitioruta activa"><%= product.getName()%></li>
                            </ol>
                        </nav>
                    </div>
                </div>
                <div class="grid producto">
                    <div class="column-xs-12 column-md-7">
                        <div class="product-gallery">
                            <div class="imagenproducto">
                                <img class="active" src="<%=product.getImage()%>">
                            </div>
                            <ul class="listaimagen">
                                <li class="imagenab"><img src="../imagenes/recolectores.jpg"></li>
                                <li class="imagenab"><img src="../imagenes/granoscafe.jpg"></li>
                                <li class="imagenab"><img src="../imagenes/cafehecho.jpg"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="infoproducto">
                        <h1><%=product.getName()%> </h1>
                        <h2>$<%= df.format(product.getPrice())%></h2>
                        <div class="descripcion">
                            <p><%= product.getDescription()%></p>
                        </div>
                        <button class="añadircarrito"><a href="./carrito.jsp?param=<%=product.getProductID()%>">Add To Cart</a></button>
                    </div>
                </div>
                <div class="grid productosrelacionados">
                    <div class="recom">
                        <h3>You may also like</h3>
                    </div>
                    <%
                        for (Product relatedProduct : products) {
                            if (relatedProduct.getProductID() != product.getProductID() && cont < 4 && relatedProduct.getCategory().getCategoryID() == product.getCategory().getCategoryID()) {


                    %>
                    <div class="otros"><a href="./productDetail.jsp?param=<%=product.getProductID()%>" >
                            <img src="<%= relatedProduct.getImage()%>" class="active">
                            <h4><%=relatedProduct.getName()%></h4>
                            <p class="precio">$<%= df.format(relatedProduct.getPrice())%></p>
                        </a></div>
                        <% cont++;

                                }
                            } %>
                </div>
            </div>
        </main>
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
        <% }
                    }
                }
            }%>
    </body>

</html>