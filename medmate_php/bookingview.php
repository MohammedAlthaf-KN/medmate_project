<?php
include("connection.php");


$docemail=$_POST['docemail'];


$stmt = $con->prepare( "SELECT id,docname,docemail,username,usernumber,useremail,userage,reason,time,date FROM doctorbooking  WHERE docemail='$docemail'");

//Executting the query

$stmt->execute();

//binding result to the query

$stmt->bind_result($id,$docname,$docemail,$username,$usernumber,$useremail,$userage,$reason,$time,$date);

$p=array();

while($stmt->fetch()){
    $temp=array();
    $temp['id']=$id;
    $temp['docname']=$docname;
    $temp['docemail']=$docemail;
    $temp['username']=$username;
    $temp['usernumber']=$usernumber;
    $temp['useremail']=$useremail;
    $temp['userage']=$userage;
    $temp['reason']=$reason;
    $temp['time']=$time;
    $temp['date']=$date;

  
    array_push($p,$temp);

}
echo json_encode($p);