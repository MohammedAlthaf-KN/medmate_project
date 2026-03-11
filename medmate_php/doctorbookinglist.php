<?php
include("connection.php");

//creating a query




$stmt = $con->prepare( "SELECT id,docname,docemail,username,usernumber,useremail,userage,reason,date,time FROM doctorbooking");

//Executting the query

$stmt->execute();

//binding result to the query

$stmt->bind_result($id,$docname,$docemail,$username,$usernumber,$useremail,$userage,$reason,$date,$time);

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
    $temp['date']=$date;
    $temp['time']=$time;
    
  
    array_push($p,$temp);

}
echo json_encode($p);