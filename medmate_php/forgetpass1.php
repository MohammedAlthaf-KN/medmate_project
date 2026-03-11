<?php
include ("connection.php");
$mobilenumber = $_POST['mobilenumber'];
$q = "select * from doctor_register where mobilenumber ='$mobilenumber'";
$result = mysqli_query($con,$q) or die(mysqli_errror($con));
if($result)
{
    $response['status'] ="1";
    $response['message'] = "data fetched";
}
else
{
    $response['status'] ="0";
    $response['message'] = "enterd mobile number is not registered";
}
echo json_encode($response);
?>