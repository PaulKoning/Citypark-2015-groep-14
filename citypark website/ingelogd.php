<?php
// Connectie DB opzetten
include ("include/dbconnect.php");

// Variables
$gebruikerpost = $_POST["gebruiker"];
$passpost = $_POST["wachtwoord"];
// Script die het inlogproces in werking stelt.
$SQL = "SELECT Gebruikersnaam, Wachtwoord, Gebruiker_niveau, Gebruiker_ID FROM Gebruiker WHERE Gebruikersnaam = '$gebruikerpost'"; 

echo $SQL;
$result = mysqli_query($connection, $SQL);
	if (mysqli_num_rows($result) > 0) {
		$row = mysqli_fetch_assoc($result);
		$passindb = $row['Wachtwoord'];
		$userlevel = $row['Gebruiker_niveau'];
		if ($passindb != $passpost) {
			echo "U bent nog niet geregistreerd, of je hebt een foutief gebruikersnaam/wachtwoord ingevoerd" ;
			echo "<br>", "Ga terug naar de inlogpage";
			echo '<form action="inloggen.php"><INPUT TYPE="submit" VALUE="Inlogpagina" return false;"></form>';
		}
		Else {
			echo '<meta http-equiv="refresh" content="0; url=index.php" />';
			session_start();
			$_SESSION['username'] = $gebruikerpost;
			$_SESSION['userlevel'] = $userlevel;
		}
	} 
	Else {
		Echo "U bent nog niet geregistreerd, of je hebt een foutief gebruikersnaam/wachtwoord ingevoerd" ;
		echo "<br>", "Ga terug naar de inlogpage";
		echo '<form action="inloggen.php"><INPUT TYPE="submit" VALUE="Inlogpagina" return false;"></form>';
	}
?> 