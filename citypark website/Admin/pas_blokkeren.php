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
			//query
			$query = "SELECT Pas.Pas_ID, Pas.Pastype_Pastype_ID, Pas.Actief, Gebruiker.Voornaam,
				  Gebruiker.Achternaam, Gebruiker.Adres, Gebruiker.Woonplaats, 					  Gebruiker.Gebruikersnaam  
				  FROM Pas
				  JOIN Gebruiker on Pas.Gebruiker_Gebruiker_ID = 					  Gebruiker.Gebruiker_ID
			  	  WHERE Gebruiker.Achternaam LIKE '%$gebruiker%'"; 
			echo $query;
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
			echo "$subject['Pas.Pas_ID]";
			echo "</td>";
			
		// Pastype bepalen
		if ($subject['Pas.Pastype_Pastype_ID'] == 1) { echo "<td>", "Abbonoment", "</td>"; }
		if ($subject['Pas.Pastype_Pastype_ID'] == 2) { echo "<td>", "Ad-Hoc", "</td>"; }
		if ($subject['Pas.Pastype_Pastype_ID'] == 3) { echo "<td>", "Lege Pas", "</td>"; }
		if ($subject['Pas.Pastype_Pastype_ID'] == 4) { echo "<td>", "Bezoekerspas", "</td>"; }

		// Actief bepalen
		if ($subject['Pas.Actief'] == false) { echo "<td>", "Inactief", "</td>"; }
		if ($subject['Pas.Actief'] == true) { echo "<td>", "Actief", "</td>"; }	
		
		// Verder met echoen van gegevens
		echo "<td>", "$subject['Gebruiker.Voornaam']", "</td>";
		echo "<td>", "$subject['Gebruiker.Achternaam']", "</td>";
		echo "<td>", "$subject['Gebruiker.Adres']", "</td>";
		echo "<td>", "$subject['Gebruiker.Woonplaats']", "</td>";
		echo "<td>", "$subject['Gebruiker.Gebruikersnaam']", "</td>";	
			}
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
	
	
	
	
	
	
	
	
	
	
	