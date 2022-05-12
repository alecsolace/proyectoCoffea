<%@page import="com.sanvalero.coffea.domain.Customer"%>
<%@page import="com.sanvalero.coffea.dao.CustomerDAO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="../css/style3.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@500;600&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="backbox">
                <div class="signupMsg">
                    <div class="textcontent">
                        <p class="title">Have an account?</p>
                        <p>Log in to see all your cart.</p>
                        <button><a href="login.html">Log In</a></button>
                    </div>
                </div>
            </div>
            <div class="frontbox">
                <div class="signup">
                    <h2>SIGN UP</h2>
                    <form action="register2.jsp" method="post">
                        <div class="inputbox">
                            <input type="text" name="fullname" placeholder="  FULLNAME">
                            <input type="text" name="email" placeholder="  EMAIL">
                            <input type="password" name="password" placeholder="  PASSWORD">
                            <input type="text" name="direccion" placeholder="  DIRECCION">
                            <input type="text" name="numero" placeholder="  NUMERO">
                            <input type="text" name="cp" placeholder="  CP">
                        </div>
                        <br>
                        <input type="submit" name="submit" id="next" value="SIGUIENTE">
                    </form>
                    <br>
                </div>
            </div>
        </div>
    </body>

</html>
</body>