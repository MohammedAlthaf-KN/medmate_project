<?php 
include("connection.php");

$email=$_POST["email"];
$password=$_POST["password"];
$query="SELECT * FROM `doctor_register` WHERE email='$email' && password='$password' && status='approved'";
$result=mysqli_query($con,$query);
$row=mysqli_fetch_row($result);
if(mysqli_num_rows($result)>0)
{
    $response["status"]="1";
    $response["message"]="Login Successful";
    
    $response["id"]=$row["0"];
    $response["name"]=$row["1"];
    $response["speciality"]=$row["2"];
    $response["dob"]=$row["3"];
    $response["gender"]=$row["4"];
    $response["experience"]=$row["5"];
    $response["email"]=$row["6"];
    $response["hospital"]=$row["7"];
    $response["mobilenumber"]=$row["8"];
    $response["password"]=$row["9"];
  

  

}
else
{
    $response["status"]="0";
    $response["message"]="Login failed";

    $response["id"]="";
    $response["name"]="";
    $response["speciality"]="";
    $response["dob"]="";
    $response["gender"]="";
    $response["experience"]="";
    $response["email"]="";
    $response["hospital"]="";
    $response["mobilenumber"]="";
    $response["password"]="";
}
echo json_encode($response);
?>
