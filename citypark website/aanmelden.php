<?php
require_once("db_connection.php");
    ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>CityPark Parking Project</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <link href='http://fonts.googleapis.com/css?family=Playfair+Display|Pontano+Sans' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="header-wrapper">
    <div class="header">
        <div class="header-content">
            <div class="logo">
                <h1>CityPark Parking</h1>
            </div>

            <div class="menu">
                <ul>
                    <li><a class="active" href="index.php">Home</a></li>
                    <li><a href="inloggen.php">Inloggen</a></li>
                    <li><a href="aanmelden.php">Aanmelden</a></li>


                </ul>
            </div>
        </div>
    </div>
</div>
<!--- header-wrapper div end -->
<div class="clearing"></div>
<div class="cotainer">
    <div class="col-wrapper">
        <div class="col1 marRight">
            <h1>Sub Menu</h1>

            <ul>

                <li><a href="#">Quod maxime placeat facere possimus</a></li>
                <li class="bdrBottom"><a href="#">Itaque earum rerum hic teneturenime</a></li>
            </ul>
        </div>
        <div class="col2">
            <h1>About Us</h1>
            <form tag="Create Logon">
                <div align="center">
                    Voornaam *: <input type="username" name="username" /></br>

                    Achternaam *: <input type="surname" name="surname" /></br>

                    Wachtwoord *: <input type="password" name="pwd" /></br>

                    Geboortedatum *: <input type="date of birth" name="dob" /></br>

                    E-mail *: <input type="email" name="email" /></br>

                    Telefoon: <input type="telephone" name="tel" /></br>

                    Adres *: <input type="address" name="add" /></br>

                    postcode *: <input type="postcode" name="ptc" /></br>


                    <input type="submit" value="Verstuur" />
                </div>
            </form>


        </div>

    </div>

</div>
<div class="clearing"></div>
<div class="footer-wrapper">




</div>
<!--- footer-wrapper div end -->
<div class="footer-strip-wrapper">
    <div class="footer-strip">
        <p>Copyright (c) 2015 CityPark. All rights reserved.</p>
    </div>
</div>
</body>
</html>