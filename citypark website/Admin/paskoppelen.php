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
			############################################################
			// Gebruiker opvragen waar pas aangekoppeld wordt
			if(isset($_POST['gebruiker'])) { $gebruiker = $_POST['gebruiker']; }
			if(!isset($gebruiker)){
			echo '<form action="/admin/paskoppelen.php" method="post">
			Gebruikersnaam: <input type="text" name="gebruiker">
                	<input type="submit" value="Opvragen">
				
			</form>';
			}
			
			// Tweede deel
			if(isset($gebruiker) && !isset($_POST['abbtype'])) {

			//query
			$query = "SELECT Gebruikersnaam, Gebruiker_ID					  
				  FROM Gebruiker
			  	  WHERE Gebruiker.Gebruikersnaam LIKE '%$gebruiker%'"; 
				
				  $result = mysqli_query($connection, $query);

					if (mysqli_num_rows($result) > 1 || mysqli_num_rows($result) < 1) {
					echo "Niet juist ingevuld, probeer opnieuw!";
				 	echo '<form action="/admin/paskoppelen.php" method="post">
						Gebruikersnaam: <input type="text" name="gebruiker">
                				<input type="submit" value="Opvragen">
						</form>';
					}
				else {

				$query2 = "SELECT Beschrijving FROM Abbonementtype";
				$result2 = mysqli_query($connection, $query2);
			echo '<form action="/admin/paskoppelen.php" method="post">';
			echo "<input type=hidden name=gebruiker value=".$gebruiker.">";
			echo '<SELECT name="abbtype">';	
			while($subject = mysqli_fetch_assoc($result2)) {
			echo '<option>', $subject['Beschrijving'], '</option>';
			}
			
                	echo '</select><input type="submit" value="Verstuur">
				
			</form>';
				} 
				
			}

			// derde deel
			if(isset($gebruiker) && isset($_POST['abbtype']) && !isset($_POST['CardID'])) {
			$abbtype = $_POST['abbtype'];

			echo '<form action="/admin/paskoppelen.php" method="post">';
			echo "<input type=hidden name=gebruiker value=".$gebruiker.">";
			echo "<input type=hidden name=a value=".$abbtype.">";
                	echo '</select><input type="submit" value="Verstuur">
	CardID: <input type="text" name="CardID">				
			</form>';
			}
	
	if(isset($gebruiker) && isset($_POST['abbtype']) && isset($_POST['CardID'])) {
	$abbtype = $_POST['abbtype'];	
	$cardid = $_POST['CardID'];
	if ($abbtype = "Bezoekerspas"){
	$abbtype = 1;
	$pastype = 4;

	}
	else {
	$abbtype = 2;
	$pastype = 2;

	}
	$query4 = "SELECT Gebruiker_ID FROM Gebruiker WHERE Gebruikersnaam = '$gebruiker'";
	$result = mysqli_query($connection, $query4);
	$row = mysqli_fetch_assoc($result);
	$userid = $row['Gebruiker_ID'];

	$query3 = "INSERT INTO Pas (Pastype_Pastype_ID, Actief, Gebruiker_Gebruiker_ID, Cardid) VALUES ($pastype, 1, $userid, $cardid);";
	$query5 = "INSERT INTO Abbonementen (Betaald, `Begin Contract`, Abbonementtype_Abbonementtype, Pas_Pas_ID, Actief) VALUES (1, NOW(), $abbtype, (SELECT MAX(Pas_ID) FROM Pas) , 1)";
	 echo $query5;
	mysqli_query($connection, $query3);
	mysqli_query($connection, $query5);
	
	}
			#############################################################	
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
	
	
	
	
	
	
	
	
	
	
	