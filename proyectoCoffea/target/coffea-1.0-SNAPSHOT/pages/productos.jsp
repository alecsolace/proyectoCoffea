<%@page import="com.sanvalero.coffea.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.domain.*"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Productos</title>
        <link href="../css/style-productos.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>

    <body>
        <% ProductDAO productDAO = new ProductDAO();
            ArrayList<Product> products = productDAO.get_products();
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> categories = categoryDAO.get_categories(); %>
        <header id="site-header">
            <div class="container">
                <a href="./index.jsp"><img src="../imagenes/logo.png" class="logoarr" alt="logo"  ></a>
                <div class="accesos"> 
                    <a href="./accesories"> ACCESORIES </a>

                </div>
                <div class="accesos">
                    <a href="./productos.jsp"> MENU </a> 
                </div>
                <div class="accesos">
                    ABOUT US
                </div>
                <div class="box">
                    <div class="container-1">
                        <img src="../imagenes/lupa.png" class="lupa" alt="lupa">
                        <input type="search" id="search" placeholder="Search..." />
                    </div>
                </div>
            </div>
        </header>

        <div class="productContainer">
            <div class="titulo">
                CHOOSE A PRODUCT
            </div>
            <% for (Category category : categories) {%>


            <div class="titulo">
                <%= category.getName()%>
            </div>
            <% for (Product product : products) {
                    if (product.getCategory().getCategoryID() == category.getCategoryID()) { %>
            <% request.setAttribute("productID", product.getProductID());%>

            <div class="products">

                <a >
                    <div class="overlay">
                        <div class="items"></div>
                        <div class="items head">
                            <p><%=product.getName()%></p>
                            <hr>
                        </div>
                        <div class="items price">
                            <p class="new">$<%=product.getPrice()%></p>
                        </div>
                        <div class="items cart">
                            <span> ADD TO CART </span>
                        </div>
                    </div>
                </a>
            </div>
            <%         }
                    }
                }%>
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
    </body>

</html>