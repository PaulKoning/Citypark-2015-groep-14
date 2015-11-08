<?php
include ("include/sessions.php");
// Als je al ingelogd bent wordt je naar de homepage gestuurd
if(isset($_SESSION['username'])) {
    	echo '<meta http-equiv="refresh" content="0; url=/index.php" />';
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
	<h1>Sub Menu</h1>
	
	<ul>

	<li><a href="#">Quod maxime placeat facere possimus</a></li>
	<li class="bdrBottom"><a href="#">Itaque earum rerum hic teneturenime</a></li>
	</ul>
	</div>
	<div class="col2">
	<h1>Inloggen</h1>
		</div>
        <h1>Login to Web App</h1>


 <center><table border=0>
<form method=POST ACTION=ingelogd.php> 
   <tr>
<td>Uw gebruikersnaam: </td> <td><INPUT TYPE="text" NAME="gebruiker" SIZE="34"></td><br></tr>
<tr><td>Wachtwoord:  </td><td><INPUT TYPE="password" NAME="wachtwoord" SIZE="34"></td><br> <br></tr>
<tr><td><button type = "submit">Inloggen</button></td></tr></form></table></center>



        </br>
        </br>
	</div>



</div>
<div class="clearing"></div>
<div class="footer-wrapper">