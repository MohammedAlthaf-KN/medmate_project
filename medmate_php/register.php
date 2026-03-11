<?php
include("connection.php");
$name=$_POST['name'];
$email=$_POST['email'];
$mobilenumber=$_POST['mobilenumber'];
$password=$_POST['password'];




$q ="INSERT INTO register(name,email,mobilenumber,password) VALUES ('$name','$email','$mobilenumber','$password')";

$result=mysqli_query($con,$q);
if($result){
    $response["status"]="1";
    $response["message"]=" Registration successful";
}
else{
    $response["status"]="0";
    $response["message"]="Registration failed";
}
echo json_encode($response);
?>

