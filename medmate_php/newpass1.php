<?php
include ("connection.php");
$mobilenumber = $_POST['mobilenumber'];
$password = $_POST['password'];

$q = "update doctor_register set password = '$password' where mobilenumber ='$mobilenumber'";
$result = mysqli_query($con,$q) or die(mysqli_errror($con));
if($result)
{
    $response['status'] ="1";
    $response['message'] = "Password changed successfully";
}
else
{
    $response['status'] ="0";
    $response['message'] = "Failed";
}
echo json_encode($response);
?>