<?php 
include("connection.php");

// Get parameters from POST request
$docname = $_POST["docname"];
$docemail = $_POST["docemail"];
$username = $_POST["username"];
$usernumber = $_POST["usernumber"];
$useremail = $_POST["useremail"];
$userage = $_POST["userage"];
$reason = $_POST["reason"];
$date = $_POST["date"];
$time = $_POST["time"];
$status = $_POST["status"];

// // Prepare the response
$response = [];

// Check if the entry already has a status (approved/disapproved)
$check_query = "SELECT * FROM doctorbooking WHERE usernumber = '$usernumber' AND time = '$time' AND (status = 'Approve' OR status = 'Disapprove')";
$check_result = mysqli_query($con, $check_query);

if (mysqli_num_rows($check_result) > 0) {
    // Entry already approved or disapproved
    $response["status"] = "0";
    $response["message"] = "This action has already been taken for this entry.";
    echo json_encode($response);
    exit;
}

// Prepare the SQL query to update the record
$q = "UPDATE doctorbooking 
      SET docname = '$docname', 
          docemail = '$docemail', 
          username = '$username', 
          usernumber = '$usernumber', 
          useremail = '$useremail', 
          userage = '$userage', 
          reason = '$reason',  
          date = '$date', 
          time = '$time',  
          status = '$status'
      WHERE docname = '$docname' AND username = '$username'";

// Execute the query
$result = mysqli_query($con, $q);

if ($result) {
    $response["status"] = "1";
    $response["message"] = "Update successful";
} else {
    $response["status"] = "0";
    $response["message"] = "Update failed: " . mysqli_error($con);
}

// Return the response as JSON
echo json_encode($response);
?>