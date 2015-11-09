<?php
include ("include/dbconnect.php");
include ("include/sessions.php");
$query  = "SELECT * FROM `Gebruiker` WHERE `Gebruikersnaam` = '$username'";
$result = mysqli_query($connection, $query);

//Test if there was a query error
if (!$result) {
    die("Database query failed.");
}

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
            <h1>Gegevens</h1>

            <?php
            while($subject1 = mysqli_fetch_assoc($result)) {
                $voornaam = $subject1["Voornaam"];
                $achternaam=$subject1["Achternaam"];
                $gebruikersnaam=$subject1["Gebruikersnaam"];
                $rekening=$subject1["Rekeningsnummer"];
                $wachtwoord=$subject1["Wachtwoord"];
                $adres = $subject1["Adres"];
                $postcode=$subject1["Postcode"];
                $woonplaats=$subject1["Woonplaats"];
                $telefoon=$subject1["Telefoonnummer"];
                $email=$subject1["Email"];
            }
            ?>


            <form method="post">
                Voornaam:<br>
                <input type="text" name="naam" value="<?php echo $voornaam; ?>">
                <br>
                Achternaam:<br>
                <input type="text" name="achternaam" value="<?php echo $achternaam; ?>">
                <br>
                Gebruikersnaam:<br>
                <input type="text" name="gebruikersnaam" value="<?php echo $gebruikersnaam; ?>">
                <br>
                rekeningnummer:<br>
                <input type="text" name="reknummer" value="<?php echo $rekening; ?>">
                <br>
                Wachtwoord:<br>
                <input type="password" name="wachtwoord" value="">
                <br>
                bevestig wachtwoord:<br>
                <input type="password" name="bevestig_wachtwoord" value="">
                <br>
                straat:<br>
                <input type="text" name="straat" value= "<?php echo $adres; ?>">
                <br>
                Postcode:<br>
                <input type="text" name="postcode" value="<?php echo $postcode; ?>">
                <br>
                Woonplaats:<br>
                <input type="text" name="woonplaats" value="<?php echo $woonplaats; ?>">
                <br>
                Telefoonnummer:<br>
                <input type="text" name="tel_nr" value="<?php echo $telefoon; ?>">
                <br>
                E-mail:<br>
                <input type="text" name="email" value="<?php echo $email; ?>">
                <br>
                <br>
                <br>
                <td><button type = "submit" name = "submit">Wijzig</button></td>
                </br>
                </br>

            </form>



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
    echo "Uw registratie is gelukt." . "<br/>";

    // SQL Query aanmaken voor gebruiker
   $reg_klant = "update gebruiker  ";
    $reg_klant .= " SET ";
    $reg_klant .= " voornaam= '$naam', achternaam = '$achternaam', rekeningsnummer = '$rekeningsnummer', Adres = '$straat', ";
    $reg_klant .= " Woonplaats= '$woonplaats', Postcode= '$postcode', Telefoonnummer= '$telefoonnummer', Email= '$email', Wachtwoord= '$wachtwoord', gebruikersnaam = '$gebruikersnaam'  ";
    $reg_klant .= " where Gebruiker_ID = '$userid' ";

    $registratie = mysqli_query($connection,$reg_klant);

}

?>