<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cart</title>
        <link href="../css/style-header.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>
    <body>
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
                <article class="producto">
                    <header>
                        <a class="eliminado">
                            <img src="../imagenes/producto prueba.png" alt="">

                            <h3>Remove product</h3>
                        </a>
                    </header>

                    <div class="contenido">

                        <h1>Lorem ipsum</h1>

                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.


                    </div>

                    <footer class="contenido">
                        <span class="menos">-</span>
                        <span class="cant">1</span>
                        <span class="mas">+</span>

                        <h2 class="preciototal">
                            29.98?
                        </h2>

                        <h2 class="precio">
                            14.99?
                        </h2>
                    </footer>
                </article>

                <article class="producto">
                    <header>
                        <a class="eliminado">
                            <img src="../imagenes/producto prueba.png" alt="">

                            <h3>Remove product</h3>
                        </a>
                    </header>

                    <div class="contenido">

                        <h1>Lorem ipsum dolor</h1>

                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.


                    </div>

                    <footer class="contenido">

                        <span class="menos">-</span>
                        <span class="cant">1</span>
                        <span class="mas">+</span>

                        <h2 class="preciototal">
                            79.99?
                        </h2>

                        <h2 class="precio">
                            79.99?
                        </h2>
                    </footer>
                </article>

                <article class="producto">
                    <header>
                        <a class="eliminado">
                            <img src="../imagenes/producto prueba.png" alt="">

                            <h3>Remove product</h3>
                        </a>
                    </header>

                    <div class="contenido">

                        <h1>Lorem ipsum dolor ipsdu</h1>

                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.


                    </div>

                    <footer class="contenido">

                        <span class="menos">-</span>
                        <span class="cant">1</span>
                        <span class="mas">+</span>

                        <h2 class="preciototal">
                            53.99?
                        </h2>

                        <h2 class="precio">
                            17.99?
                        </h2>
                    </footer>
                </article>

            </section>

        </div>

        <footer id="abajo">
            <div class="container fix">

                <div class="izquierda">
                    <h2 class="subtotal">Subtotal: <span>163.96</span>?</h2>
                    <h3 class="tax">Taxes (5%): <span>8.2</span>?</h3>
                    <h3 class="shipping">Shipping: <span>5.00</span>?</h3>
                </div>

                <div class="derecha">
                    <h1 class="total">Total: <span>177.16</span>?</h1>
                    <a class="botonab">Checkout</a>
                </div>

            </div>
        </footer>
    </body>
</html>