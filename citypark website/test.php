<?php
/**
 * Created by PhpStorm.
 * User: Jelmer-ASUS
 * Date: 3-11-2015
 * Time: 13:55
 */

include ("include/dbconnect.php");

$query = "SELECT * FROM Abbonementen";
$result = mysqli_query($connection, $query);

while($subject = mysqli_fetch_assoc($result)){

    echo $subject['Pas_Pas_ID'];
    echo "</br>";
    echo "taart";
}

?>