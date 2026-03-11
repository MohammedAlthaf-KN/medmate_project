<?php 
include("connection.php");

// Get the POST data safely
$docname = mysqli_real_escape_string($con, $_POST["docname"]);
$docemail = mysqli_real_escape_string($con, $_POST["docemail"]);
$username = mysqli_real_escape_string($con, $_POST["username"]);
$usernumber = mysqli_real_escape_string($con, $_POST["usernumber"]);
$useremail = mysqli_real_escape_string($con, $_POST["useremail"]);
$userage = mysqli_real_escape_string($con, $_POST["userage"]);
$reason = mysqli_real_escape_string($con, $_POST["reason"]);
$time = mysqli_real_escape_string($con, $_POST["time"]);
$date = mysqli_real_escape_string($con, $_POST["date"]);

// Check if the time slot is already taken
$checkQuery = "SELECT * FROM doctorbooking WHERE docname = '$docname' AND date = '$date' AND time = '$time'";
$checkResult = mysqli_query($con, $checkQuery);

// Initialize the response array
$response = [];

if (mysqli_num_rows($checkResult) > 0) {
    // Time slot is already taken
    $response["status"] = "0";
    $response["message"] = "Time slot is already taken";
} else {
    // Proceed with the booking (without payment)
    $insertQuery = "INSERT INTO doctorbooking (docname, docemail, username, usernumber, useremail, userage,reason, time, date) 
                    VALUES ('$docname', '$docemail', '$username', '$usernumber', '$useremail', '$userage','$reason', '$time', '$date')";
    $insertResult = mysqli_query($con, $insertQuery);

    if ($insertResult) {
        $response["status"] = "1";
        $response["message"] = "Registration successful";
    } else {
        $response["status"] = "0";
        $response["message"] = "Registration failed";
    }
}

// Return the JSON response
echo json_encode($response);
?>
