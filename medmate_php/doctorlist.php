<?php
include("connection.php");

//creating a query


$speciality=$_POST['speciality'];


$stmt = $con->prepare( "SELECT id,name,speciality,dob,gender,experience,email,hospital,mobilenumber,password,document FROM doctor_register   WHERE  speciality='$speciality'");

//Executting the query

$stmt->execute();

//binding result to the query

$stmt->bind_result($id,$name,$speciality,$dob,$gender,$experience,$email,$hospital,$mobilenumber,$password,$document);

$p=array();

while($stmt->fetch()){
    $temp=array();
    $temp['id']=$id;
    $temp['name']=$name;
    $temp['speciality']=$speciality;
    $temp['dob']=$dob;
    $temp['gender']=$gender;
    $temp['experience']=$experience;
    $temp['email']=$email;
    $temp['hospital']=$hospital;
    $temp['mobilenumber']=$mobilenumber;
    $temp['password']=$password;
    $temp['document']=$document;
  
    array_push($p,$temp);

}
echo json_encode($p);