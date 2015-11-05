<?php
// Session starten als je bent ingelogd
session_start();
if(isset($_SESSION['username'])) {
echo "U bent ingelogd als: ", $_SESSION['username'];


//Variabelen toekennen

$username = $_SESSION['username'];
$userlevel = $_SESSION['userlevel'];
}
?>
