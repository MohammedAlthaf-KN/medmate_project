<?php
include("connection.php");

//creating a query

$useremail=$_POST["useremail"];


$stmt = $con->prepare( "SELECT id,docname,docemail,username,usernumber,useremail,userage,reason,date,time,status,fee,feedate FROM doctorbooking WHERE useremail='$useremail'");

//Executting the query

$stmt->execute();

//binding result to the query

$stmt->bind_result($id,$docname,$docemail,$username,$usernumber,$useremail,$userage,$reason,$date,$time,$status,$fee,$feedate);

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
    $temp['status']=$status;
    $temp['fee']=$fee;
    $temp['feedate']=$feedate;
  
    array_push($p,$temp);

}
echo json_encode($p);