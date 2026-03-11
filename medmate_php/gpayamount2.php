<?php
include("connection.php");

// Capture POST data and sanitize
$userid = isset($_POST["usernumber"]) ? trim($_POST["usernumber"]) : null;
$paymentDate = isset($_POST["feedate"]) ? trim($_POST["feedate"]) : null;
$amount = isset($_POST["fee"]) ? floatval($_POST["fee"]) : null;

$response = array(); // Initialize response array

// Validate input
if (!$userid || !$paymentDate || !$amount) {
    // Missing input
    $response["status"] = "0";
    $response["message"] = "Invalid input. Missing one or more fields.";
} else {
    // Prepare SQL query
    $query = "UPDATE doctorbooking SET feedate = ?, fee = ? WHERE usernumber = ?";
    $stmt = $con->prepare($query);

    if ($stmt) {
        // Bind parameters (paymentDate as string, amount as float, userid as string)
        $stmt->bind_param("sds", $paymentDate, $amount, $userid);

        // Execute query
        $result = $stmt->execute();

        if ($result && $stmt->affected_rows > 0) {
            // If the query was successful and at least one row was updated
            $response["status"] = "1";
            $response["message"] = "Transaction successfully recorded.";
        } else {
            // No record found or no rows affected
            $response["status"] = "0";
            $response["message"] = "Transaction failed. Record not found or no changes made.";
        }

        $stmt->close(); // Close the statement
    } else {
        // Error with the prepared statement
        $response["status"] = "0";
        $response["message"] = "Database error: " . $con->error;
    }
}

// Log errors for debugging
if ($response["status"] === "0") {
    error_log("Error: " . $response["message"]);
}

// Output response as JSON
echo json_encode($response);

$con->close(); // Close the database connection
?>

