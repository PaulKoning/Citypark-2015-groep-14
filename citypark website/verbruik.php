<?php
include ("include/dbconnect.php");
include ("include/sessions.php"); // Sessies
$query  = " SELECT * FROM `gebruiker`   ";
$query  .= " JOIN pas on gebruiker.Gebruiker_ID=pas.Gebruiker_Gebruiker_ID  ";
$query  .= "  JOIN abbonementen on abbonementen.Pas_Pas_ID=pas.Pas_ID ";
$query  .= " WHERE abbonementtype_abbonementtype = 1  ";
$query  .= "  AND `Gebruikersnaam` = '$username' ";

$result = mysqli_query($connection, $query);
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


                    <?php

                    //CODE BY JENS

                    echo '<li><a class="active" href="index.php">Home</a></li>';

                    if(!isset($_SESSION['username'])) { // Als je niet bent ingelogd wordt dit weergegeven
                        echo ' 	<li><a href="inloggen.php">Inloggen</a></li>
                    	<li><a href="aanmelden.php">Aanmelden</a></li>';
                    }
                    if(isset($_SESSION['username'])) {
                        echo '<li><a href="uitloggen.php">Uitloggen</a></li>';
                    }
                    ?>
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
            <h1>Menu</h1>


            <ul>

                <li><a href="profiel.php">Profiel weergeven</a></li>
                <li class="bdrBottom"><a href="pasnummers.php">Pasnummers</a></li>
                <li class="bdrBottom"><a href="verbruik.php">Verbruik bezoekerspas</a></li>
                <li class="bdrBottom"><a href="afrekening.php">Maandafrekening</a></li>
                <li class="bdrBottom"><a href="blokkeren.php">Pas blokkeren</a></li>
                <li class="bdrBottom"><a href="opzeggen.php">Abonnement opzeggen</a></li>
                <li class="bdrBottom"><a href="servicepagina.php">servicepagina</a></li>
            </ul>
        </div>
        <div class="col2">
            <h1>Verbruik</h1>
            <?php

            while($subject1 = mysqli_fetch_assoc($result)) {
                echo "U heeft deze week ";
                echo $subject1["Uren_Dezeweek"];
                echo " uren verbruikt van uw bezoekerspas</br>";
            }

            ?>
            </br>
            </br>
            <i>Bij geen resultaat heeft u geen bezoekerspas</i>
        </div>


        </br>
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