<?php
include("connection.php");
$doctorname = $_POST['doctorname'];
$doctorphone = $_POST['doctorphone'];
$username = $_POST['username'];
$userphone = $_POST['userphone'];

$originalImgName = $_FILES['filename']['name'];
$tempName = $_FILES['filename']['tmp_name'];
$folder = "prescription/";

if(move_uploaded_file($tempName, $folder . $originalImgName)) {
    $currentDate = date('d-m-Y'); // Get the current date and time

    $query = "INSERT INTO prescription (doctorname, doctorphone, username, userphone, prescription, date) 
              VALUES ('$doctorname', '$doctorphone', '$username', '$userphone', '$originalImgName', '$currentDate')";

    if(mysqli_query($con, $query)) {
        $response['status'] = "1";
        $response['message'] = "File uploaded successfully";  
    } else {
        $response['status'] = "0";
        $response['message'] = "Data insertion failed";
    }
} else {
    $response['status'] = "0";
    $response['message'] = "File moving failed";
}

echo json_encode($response);
?>
