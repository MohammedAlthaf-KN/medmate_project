<?php
include("connection.php");
$id=$_POST["id"];
$name =$_POST["name"];
$speciality=$_POST["speciality"];
$dob=$_POST["dob"];
$gender=$_POST["gender"];
$experience=$_POST["experience"];
$email =$_POST["email"];
$hospital =$_POST["hospital"];
$mobilenumber=$_POST["mobilenumber"];
$password=$_POST["password"];


$query="update doctor_register set name='$name',speciality='$speciality',dob='$dob',gender='$gender',experience='$experience',email='$email',hospital='$hospital',mobilenumber='$mobilenumber',password='$password' where id='$id'";
$result=mysqli_query($con,$query);
if($result)
{
$response["status"]="1";
$response["message"]="updation successfull";
}
else
{
    $response["status"]="0";
    $response["message"]="updation faild";
}
echo json_encode($response);
?>

