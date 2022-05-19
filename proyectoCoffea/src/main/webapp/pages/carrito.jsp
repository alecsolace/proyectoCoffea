<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.sanvalero.coffea.domain.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sanvalero.coffea.dao.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cart</title>
        <link href="../css/style-carrito.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <%
            CartLineDAO cartLinesDAO = new CartLineDAO();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date(System.currentTimeMillis());

            ProductDAO productDAO = new ProductDAO();
            ArrayList<Product> productList = productDAO.get_products();
            ArrayList<CartLine> cartLines;
            cartLines = (ArrayList<CartLine>) application.getAttribute("carrito");
            CartDAO cartDAO = new CartDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            int loggedUserID = 1;
            ArrayList<Customer> customerList = customerDAO.getCustomers();
            if (session.getAttribute("user") != null) {
                loggedUserID = (int) (session.getAttribute("user"));
            }
            DecimalFormat df = new DecimalFormat("0.00");
            boolean noExiste = false;
            boolean existe = false;
            Cart cart;
            int totalPrice = 0;
            if (cartLines == null) {
                cartLines = new ArrayList<>();
            }
            int selectedProductID = Integer.parseInt(request.getParameter("param"));
            for (Customer customer : customerList) {
                if (customer.getUserID() == loggedUserID) {

                    cart = new Cart(cartDAO.getCarts().size(), customer, totalPrice, date);

                    for (Product productS : productList) {

                        if (productS.getProductID() == selectedProductID) {
                            if (cartLines.size() > 0) {

                                for (CartLine cartLine : cartLines) {
                                    if (selectedProductID == cartLine.getProduct().getProductID()) {
                                        cartLine.setQuantity(cartLine.getQuantity() + 1);
                                        cartLine.setPrice(cartLine.getProduct().getPrice() + cartLine.getPrice());
                                        existe = true;
                                    } else {
                                        noExiste = true;
                                    }
                                }
                                if (noExiste && !existe) {
                                    Product productAdded = productS;
                                    CartLine newCartLine = new CartLine((cartLines.size() + 1), productAdded, 1,
                                            productAdded.getPrice());
                                    cartLines.add(newCartLine);
                                    application.setAttribute("carrito", cartLines);
                                }
                            } else {
                                Product productAdded = productS;
                                CartLine newCartLine = new CartLine((cartLines.size() + 1), productAdded, 1,
                                        productAdded.getPrice());
                                cartLines.add(newCartLine);
                                application.setAttribute("carrito", cartLines);
                            }
                        }

                    }


        %>

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
                        <a href="carrito.html"><img src="../imagenes/carrito.png" class="carrito" alt="carrito"></a>
                        <a href="login.html"><img src="../imagenes/iniciar-sesion.png" class="iniciosesion" alt="iniciosesion"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">

            <section id="carrito"> 

                <%  for (CartLine cartLineProduct : cartLines) {
                        for (Product product : productList) {
                            if (product.getProductID() == cartLineProduct.getProduct().getProductID()) {
                %>
                <article class="producto">
                    <header>
                        <a class="eliminado">
                            <img src="<%=product.getImage()%>" alt="">

                            <h3>Remove product</h3>
                        </a>
                    </header>

                    <div class="contenido">

                        <h1><%=product.getName()%></h1>

                        <%=product.getDescription()%>

                    </div>

                    <footer class="contenido">
                        <span class="menos">-</span>
                        <span class="cant"><%=cartLineProduct.getQuantity()%></span>
                        <span class="mas">+</span>

                        <h2 class="preciototal">
                            $<%= df.format(product.getPrice() * cartLineProduct.getQuantity())%>
                        </h2>

                        <h2 class="precio">
                            $<%= df.format(product.getPrice())%>
                        </h2>
                    </footer>
                </article>
                <%
                            }
                        }
                        totalPrice += cartLineProduct.getPrice();
                    }
                %>
            </section>

        </div>
        <% cart.setPrice(totalPrice);%>
        <footer id="abajo">
            <div class="container fix">

                <div class="izquierda">
                    <h2 class="subtotal">Subtotal: <span>$<%= df.format(cart.getPrice())%> </span></h2>
                    <h3 class="tax">Taxes (10%): <span>$<%= df.format(0.1 * cart.getPrice())%> </span></h3>
                    <h3 class="shipping">Shipping: <span>$5.00</span></h3>
                </div>

                <div class="derecha">
                    <h1 class="total">Total: <span>$<%=df.format(cart.getPrice() + (0.21 * cart.getPrice()) + 5)%> </span></h1>
                    <a class="botonab" onclick="<% cartDAO.addCart(cart);%>">Checkout</a>
                </div>

            </div>
        </footer>
        <%
                }
            }
        %>
    </body>
</html>