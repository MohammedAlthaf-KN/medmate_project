<?php
include("connection.php");

//creating a query

$userphone = $_POST['userphone'];


$stmt = $con->prepare( "SELECT id,doctorname,doctorphone, username,userphone,prescription,date FROM prescription  WHERE userphone='$userphone'");

//Executting the query

$stmt->execute();

//binding result to the query

$stmt->bind_result($id,$doctorname,$doctorphone,$username,$userphone,$prescription,$date,);

$p=array();

while($stmt->fetch()){
    $temp=array();
    $temp['id']=$id;
    $temp['doctorname']=$doctorname;
    $temp['doctorphone']=$doctorphone;
    $temp['username']=$username;
    $temp['userphone']=$userphone;
    $temp['prescription']=$prescription;
    $temp['date']=$date;
   
  
    array_push($p,$temp);

}
echo json_encode($p);