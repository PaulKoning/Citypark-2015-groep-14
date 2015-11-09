<?php
include ("include/dbconnect.php");
?>
<head>
    <title>Registratie</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
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

                <li><a href="#"></a></li>
                <li class="bdrBottom"><a href="#"></a></li>
            </ul>
        </div>
        <div id = "registratie">
            <form method = "post">
                <table>
<td>Uw voornaam: </td><td><input type = "text", name = "naam" <?php if (isset($_POST['naam'])) echo 'value="'.$_POST['naam'].'"';?>></td><tr>
<td>Uw achternaam: </td><td><input type = "text", name = "achternaam" <?php if (isset($_POST['achternaam'])) echo 'value="'.$_POST['achternaam'].'"';?>></td><tr>
<td>Uw gebruikersnaam: </td><td><input type = "text", name = "gebruikersnaam" <?php if (isset($_POST['gebruikersnaam'])) echo 'value="'.$_POST['gebruikersnaam'].'"';?>></td><tr>
<td>Uw rekeningsnummer: </td><td><input type = "text", name = "reknummer" <?php if (isset($_POST['reknummer'])) echo 'value="'.$_POST['reknummer'].'"';?>></td><tr>
<td>Uw wachtwoord: </td><td><input type = "password" name = "wachtwoord"><i>Wachtwoord minimaal 6 tekens lang</i></td><tr>
<td>Bevestig wachtwoord: </td><td><input type = "password" name = "bevestig_wachtwoord"<?php if (isset($_POST['password'])) echo 'value="'.$_POST['password'].'"';?>></td><tr>
<td>Adres: </td><td><input type = "text" name = "straat"<?php if (isset($_POST['straat'])) echo 'value="'.$_POST['straat'].'"';?>></td><tr>
<td>Postcode: </td><td><input type = "text" name = "postcode"<?php if (isset($_POST['postcode'])) echo 'value="'.$_POST['postcode'].'"';?>></td><tr>
<td>Woonplaats: </td><td><input type = "text" name = "woonplaats"<?php if (isset($_POST['woonplaats'])) echo 'value="'.$_POST['woonplaats'].'"';?>></td><tr>
<td>Telefoonnummer: </td><td><input type = "text" name = "tel_nr"<?php if (isset($_POST['tel_nr'])) echo 'value="'.$_POST['tel_nr'].'"';?>></td><tr>
<td>Email: </td><td><input type = "text" name = "email"<?php if (isset($_POST['email'])) echo 'value="'.$_POST['email'].'"';?>></td><tr>
<td><button type = "submit" name = "submit">Registreer</button></td>
                </table>
            </form>
        </div>    
    </body>
<?php // Controle op invoer van het registratie formulier
if (isset($_POST["submit"])) {
    if (empty($_POST["naam"]) || is_numeric($_POST["naam"])) {
        echo "Uw naam is niet juist ingevuld" . "<br/>";
        } else { 
        $naam = $_POST["naam"];
	$achternaam = $_POST["achternaam"];
	$gebruikersnaam = $_POST["gebruikersnaam"];
	$rekeningsnummer = $_POST["reknummer"];
        }
    if ($_POST["wachtwoord"] == $_POST ["bevestig_wachtwoord"]) {   
        if (empty($_POST["wachtwoord"]) || strlen($_POST["wachtwoord"]) < 6 && strlen($_POST["wachtwoord"]) > 21) {
            echo "Uw heeft uw wachtwoord niet juist ingevuld" . "<br/>";
            } else { 
            $wachtwoord = $_POST["wachtwoord"];
            } 
    } else {
    echo "U heeft uw wachtwoord niet bevestigd" . "<br/>";
    }
    if (empty($_POST["straat"])) {
        echo "Uw adres is niet juist ingevuld" . "<br/>";
        } else { 
        $straat = $_POST["straat"];
        }
     
    if (empty($_POST["postcode"]) || !preg_match("/^[1-9]{1}[0-9]{3}[A-Z]{2}$/", $_POST["postcode"])) {
        echo "Uw postcode is niet juist ingevuld" . "<br/>";
        } else { 
        $postcode = $_POST["postcode"];
        }
    if (empty($_POST["woonplaats"])) {
        echo "Uw woonplaats is niet juist ingevuld" . "<br/>";
        } else { 
        $woonplaats = $_POST["woonplaats"];
        }

        $telefoonnummer = $_POST["tel_nr"];
    if (!filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)) {  
        echo "Email is niet juist ingevuld" . "<br/>"; 
        } else { 
        $email = $_POST["email"];
        }
 
}
 
if (isset($naam, $wachtwoord, $straat, $postcode, $woonplaats, $telefoonnummer, $email)) {

     $soapclient = new SoapClient('192.168.1.100');
     if($soapclient->doCreditCheck($rekeningsnummer, 1800) {
    // SQL Query aanmaken voor gebruiker
    $reg_klant = "INSERT INTO ";	
    $reg_klant .= "Gebruiker ";
    $reg_klant .= "(Voornaam, Achternaam, Rekeningsnummer, Adres, Woonplaats, Postcode, Telefoonnummer, Email, Wachtwoord, Gebruikersnaam, Gebruiker_niveau) ";
    $reg_klant .= "VALUES ";
    $reg_klant .= "('$naam', '$achternaam', '$rekeningsnummer', '$straat', '$woonplaats', '$postcode', '$telefoonnummer', '$email', '$wachtwoord', '$gebruikersnaam', 1)";
     
    $registratie = mysqli_query($connection,$reg_klant);
        echo "Uw registratie is gelukt." . "<br/>";
	}
	else { echo "U bent niet kredietwaardig" }
}

?>
<br>
<div class="clearing"></div>
<div class="footer-wrapper">




</div>
<!--- footer-wrapper div end -->
<div class="footer-strip-wrapper">
    <div class="footer-strip">
        <p>Copyright (c) 2015 CityPark. All rights reserved.</p>
    </div>
</div>
















