<?php
include("connection.php");

// Get the phone number sent from the POST request
$usernumber = $_POST['usernumber'];

// Check if the phone number is provided
if (empty($usernumber)) {
    echo json_encode(["error" => "Phone number is required"]);
    exit();
}

// Creating a query to select the fields as per your structure
// Use a placeholder for the phone number and bind it later
$stmt = $con->prepare("SELECT id, docname, docemail, username, 
                   usernumber, useremail, userage, reason, date, 
                   time, status
                       FROM doctorbooking 
                       WHERE  usernumber = ?");

// Bind the phone number to the placeholder
$stmt->bind_param("s", $usernumber); // 's' denotes that the parameter is a string

// Execute the query
$stmt->execute();

// Bind result to variables
$stmt->bind_result($id, $docname, $docemail, $username, 
                   $usernumber, $useremail, $userage, $reason, $date, 
                   $time,$status);

// Initialize an array to hold the result
$p = array();

// Fetch each row and add it to the result array
while ($stmt->fetch()) {
    $temp = array();
    $temp['id'] = $id;
    $temp['docname'] = $docname;
    $temp['docemail'] = $docemail;
    $temp['username'] = $username;
    $temp['usernumber'] = $usernumber;
    $temp['useremail'] = $useremail;
    $temp['userage'] = $userage;
    $temp['reason'] = $reason;
    $temp['date'] = $date;
    $temp['time'] = $time;
    $temp['status'] = $status;
    // $temp['docfee'] = $docfee;
    // $temp['docfeedate'] = $docfeedate;

    array_push($p, $temp);
}

// Return the result as a JSON-encoded array
echo json_encode($p);

// Close the statement
$stmt->close();
?>
