<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.domain.Product"%>
<%@page import="com.sanvalero.coffea.dao.ProductDAO"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coffea</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script src="js/script2.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
</head>

<body>
    <div class="padre">
        <div class="arr">
            <div class="parteizqarr">
                <div class="izq">
                    <img src="imagenes/logo.png" class="logoarr" alt="logo">
                    <img src="imagenes/cafe1.png" class="imgizq" alt="cafe">
                </div>
                <div class="der">
                    <div class="derarr">
                        <div class="accesos">
                            HOME
                        </div>
                        <div class="accesos">
                            MENU
                        </div>
                        <div class="accesos">
                            ABOUT US
                        </div>
                    </div>
                    <div class="derab">
                        <div class="bestseller">
                            BEST SELLER
                        </div>
                        <div class="productoprincipal">
                            <% ProductDAO productDAO = new ProductDAO();
                              Product bestSeller = productDAO.getBestSeller(); %>
                              <%= bestSeller.getName() %>
                            <img src="imagenes/coffeeBag.png" class="cafedelmedio" alt="cafemedio">
                        </div>
                        <div class="infoproducto">
                            <%= bestSeller.getDescription()  %>
                        </div>
                        <div class="precioshop">
                            $ <%= bestSeller.getPrice() %>
                            <div class="shopnow">
                                SHOP NOW
                                <img src="imagenes/flecha-apuntando-hacia-la-izquierda-dentro-de-un-circulo (1).png"
                                    class="flecha" alt="flecha">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="partederarr">
                <div class="box">
                    <div class="container-1">
                        <img src="imagenes/lupa.png" class="lupa" alt="lupa">
                        <input type="search" id="search" placeholder="Search..." />
                    </div>
                </div>
                <img src="imagenes/custom_header.jpg" class="imgprincipal" alt="cafetal">
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
                <img src="imagenes/separador.png" class="separador" alt="separador">
            </div>
            <div class="contactanos">
                <div class="contactanos-coffea">
                    <h3>COFFEA</h3>
                    <p>
                        Our company was made to offer our customers the best coffe in the world.
                        Our world renowned coffe has a 100% satisfaction rate among our customers.
                    </p>
                    <ul class="socials">
                        <li><a href="#"><img src="imagenes/twitter.png" class="i"></a></li>
                        <li><a href="#"><img src="imagenes/facebook.png" class="i"></a></li>
                        <li><a href="#"><img src="imagenes/instagram.png" class="i"></a></li>
                        <li><a href="#"><img src="imagenes/youtube.png" class="i"></a></li>
                        <li><a href="#"><img src="imagenes/pinterest.png" class="i"></a></li>
                     </ul>
                </div>
            </div>
            <div class="colores">
                <img src="imagenes/separador.png" class="separador2" alt="separador2">
            </div>
        </div>
    </div>
</body>

</html>

