<?php // Databaseconnectie opzetten

$dbhost = "localhost"; // Bij oplevering wijzigen naar 192.168.2.100 (Ip-adres v Citypark server)
$dbuser = "root";  
$dbpass = "";  
$dbname = "Citypark";  
$connection = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname);  

// Testen of de verbinding werkt

 if(mysqli_connect_errno()) {    
	die("Database connection failed: " .          
		mysqli_connect_error() .         
	 	" (" . mysqli_connect_errno() . ")"    
	);  
}
?>
