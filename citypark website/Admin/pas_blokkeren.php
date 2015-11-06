<?php
// Include files

include ("../include/dbconnect.php"); // DB Connectie
include ("../include/sessions.php");  // Sessions
echo '<link rel="stylesheet" type="text/css" href="../css/styles.css"/>'; // CSS File


// Als je geen admin bent wordt je naar de homepage gestuurd

if(!isset($username) || $userlevel != 2 ) { // Level 2 = Admin
    	echo '<meta http-equiv="refresh" content="0; url=../index.php" />';
}

//hier begint html ok	
?> 


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

                // Menu Code
                <?php
		###############################################################################
                
                //CODE BY JENS
                
                echo '<li><a class="active" href="index.php">Home</a></li>';
            	
            	if(!isset($_SESSION['username'])) { // Als je niet bent ingelogd wordt dit weergegeven
            	echo ' 	<li><a href="../inloggen.php">Inloggen</a></li>
                    	<li><a href="../aanmelden.php">Aanmelden</a></li>';
                }
                if(isset($_SESSION['username'])) {
                		echo '<li><a href="../uitloggen.php">Uitloggen</a></li>';
                }
		################################################################################
				?>
				
                </ul>
            </div>
        </div>
    </div>
</div>

	<div class="col2">

			<?PHP

			if(isset($_POST['gebruiker'])) { $gebruiker = $_POST['gebruiker']; }
			
			//Form voor opvragen gebruikers
			if(!isset($gebruiker)){
			echo '<form action="/admin/pas_blokkeren.php" method="post">
			Gebruikersnaam: <input type="text" name="gebruiker">
                	<input type="submit" value="Opvragen">
				
			</form>';
			}
			if(isset($gebruiker)) {
			if(isset($_POST['blokkere'])) {
				if($_POST['actief'] == 1) { $blokkatie = 0; }
				if($_POST['actief'] == 0) { $blokkatie = 1; }
			
	$query2 = "UPDATE Pas SET Actief= $blokkatie WHERE Pas_ID =";
	$query2 .= $_POST['pas_id'];

				mysqli_query($connection, $query2);
				}
		
			//query
			$query = "SELECT Pas.Pas_ID, Pas.Pastype_Pastype_ID, Pas.Actief, Gebruiker.Voornaam,
				  Gebruiker.Achternaam, Gebruiker.Adres, Gebruiker.Woonplaats, 					  Gebruiker.Gebruikersnaam  
				  FROM Pas
				  JOIN Gebruiker on Pas.Gebruiker_Gebruiker_ID = 					  Gebruiker.Gebruiker_ID
			  	  WHERE Gebruiker.Achternaam LIKE '%$gebruiker%'"; 

			$result = mysqli_query($connection, $query);
				//Tabel Maken
echo "<center><table border=1> 
  <tr>
    <th>Pas ID</th>
    <th>Pastype</th>
    <th>Actief</th>
    <th>Voornaam</th>
    <th>Achternaam</th>
    <th>Adres</th>
    <th>Woonplaats</th>
    <th>Gebruikersnaam</th>
    </tr>";
			//Tabel vullen
			while($subject = mysqli_fetch_assoc($result)) {
			echo "<tr>", 
			"<td>"; 			
			echo $subject['Pas_ID'];
			echo "</td>";
			
		// Pastype bepalen
		if ($subject['Pastype_Pastype_ID'] == 1) { echo "<td>", "Abbonoment", "</td>"; }
		if ($subject['Pastype_Pastype_ID'] == 2) { echo "<td>", "Ad-Hoc", "</td>"; }
		if ($subject['Pastype_Pastype_ID'] == 3) { echo "<td>", "Lege Pas", "</td>"; }
		if ($subject['Pastype_Pastype_ID'] == 4) { echo "<td>", "Bezoekerspas", "</td>"; }

		// Actief bepalen
		if ($subject['Actief'] == false) { echo "<td>", "Inactief", "</td>"; }
		if ($subject['Actief'] == true) { echo "<td>", "Actief", "</td>"; }	
		
		// Verder met echoen van gegevens
		echo "<td>", $subject['Voornaam'], "</td>";
		echo "<td>", $subject['Achternaam'], "</td>";
		echo "<td>", $subject['Adres'], "</td>";
		echo "<td>", $subject['Woonplaats'], "</td>";
		echo "<td>", $subject['Gebruikersnaam'], "</td>";	
		
		// Pas blokkeren
		echo "<td>", '<form action="/admin/pas_blokkeren.php" method="post">' ;
echo '<input type="submit" name="blokkere" value="(de)Blokkeren"> ';
echo "<input type=hidden name=gebruiker value=".$gebruiker.">";
echo "<input type=hidden name=actief value=".$subject['Actief'].">";
echo "<input type=hidden name=pas_id value=".$subject['Pas_ID']."></form></td></tr>";
		
			}
echo "</table></center>";
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
	
	
	
	
	
	