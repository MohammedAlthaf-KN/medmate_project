<?php
include("connection.php");

// Check if the connection was successful
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Preparing the SQL query
$stmt = $con->prepare("SELECT id, title, tips, description FROM healthcare");

// Execute the query
if (!$stmt) {
    die("Statement preparation failed: " . $con->error);
}

$stmt->execute();

// Bind result to variables
$stmt->bind_result($id, $title, $tips, $description);

// Initialize an empty array to hold the results
$p = array();

while ($stmt->fetch()) {
    $temp = array();
    $temp['id'] = $id;
    $temp['title'] = $title;
    $temp['tips'] = $tips;
    $temp['description'] = $description;
    
    // Push the result to the array
    array_push($p, $temp);
}

// Close the statement and connection
$stmt->close();
$con->close();

// Return the results as a JSON response
echo json_encode($p);
?>
