<?php
include("connection.php");
$name=$_POST['name'];
$speciality=$_POST['speciality'];
$dob=$_POST['dob'];
$gender=$_POST['gender'];
$experience=$_POST['experience'];
$email=$_POST['email'];
$hospital=$_POST['hospital'];
$mobilenumber=$_POST['mobilenumber'];
$password=$_POST['password'];



$originalImgName=$_FILES['filename']['name'];
$tempName=$_FILES['filename']['tmp_name'];
$folder="doctordocument/";

if(move_uploaded_file($tempName,$folder.$originalImgName)){
   // $query="insert into uploadmedicalrecords set medicalreports='$originalImgName' where id='$userid',";

   $query="insert into doctor_register(name,speciality,dob,gender,experience,email,hospital,mobilenumber,password,document) 
values('$name','$speciality','$dob','$gender','$experience','$email','$hospital','$mobilenumber','$password','$originalImgName')";

     if(mysqli_query($con,$query)){
   $response['status']="1";
   $response['message']="file uploaded successfully";  

}
else{
    $response['status']="0";
    $response['message']="Data insertion failed";
}
}
else{
    $response['status']="0";
    $response['message']="File moving failed";
}
echo json_encode($response);
?>