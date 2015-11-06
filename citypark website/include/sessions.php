<?php
// Session starten als je bent ingelogd
session_start();
if(isset($_SESSION['username'])) {
echo "U bent ingelogd als: ","<b>", $_SESSION['username'], "</b>";

//Variabelen toekennen
$username = $_SESSION['username'];
$userlevel = $_SESSION['userlevel'];
$userid = $_SESSION['userid'];
}





// end of file
?>
