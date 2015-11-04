<?php // Databaseconnectie opzetten


$host = "localhost";
$db = "citypark";  //database name
$user = "root"; // user
$pass = ""; // pass

// my php5.5 method of connecting to database
$mysqli = new mysqli("$host", "$user", "$pass", "$db");
if ($mysqli->connect_errno) {
    echo "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
    exit();
}
    ?>