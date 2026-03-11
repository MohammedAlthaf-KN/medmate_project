<?php
include("connection.php");

// Capture POST data and sanitize
$userid = isset($_POST["usernumber"]) ? trim($_POST["usernumber"]) : null;
$amount = isset($_POST["fee"]) ? floatval($_POST["fee"]) : null;

$response = array(); // Initialize response array

// Validate input
if (empty($userid) || empty($amount)) {
    // Missing or invalid input
    $response["status"] = "0";
    $response["message"] = "Invalid input. Missing one or more fields.";
} elseif ($amount <= 0) {
    // Validate fee amount (should be greater than zero)
    $response["status"] = "0";
    $response["message"] = "Invalid fee amount. Fee should be greater than zero.";
} else {
    // Prepare SQL query for UPDATE
    $query = "UPDATE doctorbooking SET fee = ? WHERE usernumber = ?";
    $stmt = $con->prepare($query);

    if ($stmt) {
        // Bind parameters (amount as float, userid as string)
        $stmt->bind_param("ds", $amount, $userid);

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

// Log errors for debugging (ensure error logging is enabled on the server)
if ($response["status"] === "0") {
    error_log("Error: " . $response["message"]);
}

// Output response as JSON
echo json_encode($response);

$con->close(); // Close the database connection
?>
