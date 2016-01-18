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
                echo '     <li><a href="../inloggen.php">Inloggen</a></li>
            <li><a href="pas_blokkeren.php">Pas Blokkeren</a></li>
            <li><a href="paskoppelen.php">Pas Koppelen </a></li>
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
            $query1 = "SELECT COUNT(Abbonoment_ID) AS teller FROM Abbonementen WHERE Betaald = 1";    
            $result1 = mysqli_query($connection, $query1);
            $row1 = mysqli_fetch_assoc($result1);
            $teller = $row1['teller'];

            $query2 = "SELECT AVG(Bedrag) AS bedrag FROM Factuur";
            $result2 = mysqli_query($connection, $query2);
            $row2 = mysqli_fetch_assoc($result2);
            if ($row2['bedrag'] != NULL) {
            $bedrag = $row2['bedrag'];
            } ELSE { $bedrag = "Nog geen mensen ingereden"; }
            ############################################################
            echo "Welkom op de admin-homepage, hier staan een aantal statistieken en kunt u passen blokkeren en of wijzigen";
            echo "</br>";
            echo "</br>";
            echo "Gemiddeld bedrag betaald voor inrijden: $bedrag ";
            echo "</br>";
            echo "</br>";
            echo "Aantal actieve abbonementen: $teller ";
            

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
    
    
    
    
    
    
    
    
    
    
    

