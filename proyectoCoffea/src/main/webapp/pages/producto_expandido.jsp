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
        <link href="../css/style-productoexpandido.css" rel="stylesheet" type="text/css">
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
            Product selectedProduct = (Product) session.getAttribute("selectedProduct");
            for (Category category : categories) {
                if (category.getCategoryID() == selectedProduct.getCategory().getCategoryID()) {
        %>
        <div class="padre">
            <div class="arr">
                <div class="parteizqarr">
                    <div class="izq">
                        <img src="../imagenes/logo.png" class="logoarr" alt="logo">
                        <img src="../imagenes/cafe1.png" class="imgizq" alt="cafe">
                    </div>
                    <div class="der">
                        <div class="derarr">
                            <div class="accesos">
                                <a id="nounderline" class="accesos" href="./index.jsp">   HOME </a>
                            </div>
                            <div class="accesos" href="./productos.jsp">
                                <a class="accesos" href="./productos.jsp">MENU</a>
                            </div>
                            <div class="accesos">
                                <a class="accesos" href="./sobrenosotros.html">  ABOUT US </a>
                            </div>
                        </div>
                        <div class="derab">
                            <div class="categoria">
                                <%=category.getName()%>
                            </div>
                            <div class="productoprincipal">
                                <%= selectedProduct.getName()%>
                                <img src="../imagenes/coffeeBag.png" class="cafedelmedio" alt="cafemedio">
                            </div>
                            <div class="infoproducto">
                                <%= selectedProduct.getDescription()%>

                            </div>
                            <div class="precioshop">
                                $<%= df.format(selectedProduct.getPrice())%>
                                <div class="shopnow">
                                    SHOP NOW
                                    <img src="..//imagenes/flecha-apuntando-hacia-la-izquierda-dentro-de-un-circulo (1).png"
                                         class="flecha" alt="flecha">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="partederarr">
                    <div class="box">
                        <div class="container-1">
                            <img src="../imagenes/lupa.png" class="lupa" alt="lupa">
                            <input type="search" id="search" placeholder="Search..." />
                        </div>
                    </div>
                    <img src="<%= selectedProduct.getImage()%>" class="imgprincipal" alt="producto">
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
        <%  }

            }%>
    </body>

</html>