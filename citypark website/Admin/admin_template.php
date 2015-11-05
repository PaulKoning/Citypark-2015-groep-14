<?php
// Include files

include ("../include/dbconnect.php"); // DB Connectie
include ("../include/sessions.php");  // Sessions
echo '<link rel="stylesheet" type="text/css" href="css/styles.css"/>'; // CSS File


// Als je geen admin bent wordt je naar de homepage gestuurd

if(!isset($username) || $userlevel != 2 ) { // Level 2 = Admin
    	echo '<meta http-equiv="refresh" content="0; url=../index.php" />'
}	
?> 

// Template
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
                    <?php 
                    <li><a href="../inloggen.php">Inloggen</a></li>
                    <li><a href="../aanmelden.php">Aanmelden</a></li>
                    <li><a href="../inloggen.php">Uitloggen</a></li>
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
	
				// HIER CODE PLAATSEN
	
	
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
	
	
	
	
	
	
	
	
	
	
	