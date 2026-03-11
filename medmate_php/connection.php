<?php 
$con=new mysqli("localhost","root","");
$db=mysqli_select_db($con,"medmate") or die(mysqli_error($con));
?>