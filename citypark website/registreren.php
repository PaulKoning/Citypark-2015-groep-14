<?php
include ("include/dbconnect.php");
?>
<head>
    <title>Registratie</title>
</head>
    <body>
        <div id = "registratie">
            <form method = "post">
                <table>
<td>Uw voornaam: </td><td><input type = "text", name = "naam"></td><tr>
<td>Uw achternaam: </td><td><input type = "text", name = "achternaam"></td><tr>
<td>Uw gebruikersnaam: </td><td><input type = "text", name = "gebruikersnaam"></td><tr>
<td>Uw rekeningsnummer: </td><td><input type = "text", name = "reknummer"></td><tr>
<td>Uw wachtwoord: </td><td><input type = "password" name = "wachtwoord"><i>Wachtwoord minimaal 6 tekens lang</i></td><tr>
<td>Bevestig wachtwoord: </td><td><input type = "password" name = "bevestig_wachtwoord"></td><tr>
<td>Adres: </td><td><input type = "text" name = "straat"></td><tr>
<td>Postcode: </td><td><input type = "text" name = "postcode"></td><tr>
<td>Woonplaats: </td><td><input type = "text" name = "woonplaats"></td><tr>
<td>Telefoonnummer: </td><td><input type = "text" name = "tel_nr"></td><tr>
<td>Email: </td><td><input type = "text" name = "email"></td><tr>
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
    echo "Uw registratie is gelukt." . "<br/>";
     
    // SQL Query aanmaken voor gebruiker
    $reg_klant = "INSERT INTO ";	
    $reg_klant .= "Gebruiker ";
    $reg_klant .= "(Voornaam, Achternaam, Rekeningsnummer, Adres, Woonplaats, Postcode, Telefoonnummer, Email, Wachtwoord, Gebruikersnaam, Gebruiker_niveau) ";
    $reg_klant .= "VALUES ";
    $reg_klant .= "('$naam', '$achternaam', '$rekeningsnummer', '$straat', '$woonplaats', '$postcode', '$telefoonnummer', '$email', '$wachtwoord', '$gebruikersnaam', 1)";
     
    $registratie = mysqli_query($connection,$reg_klant);

}

?>


















