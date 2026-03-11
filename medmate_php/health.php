<?php
include("connection.php");

$age = $_POST['age'];
$height = $_POST['height'];
$gender = $_POST['gender'];
$weight = $_POST['weight'];
$conditions = $_POST['conditions'];


// Insert the data into the "health" table
$q = "INSERT INTO health (age, height, gender, weight, conditions) 
      VALUES ('$age', '$height', '$gender', '$weight', '$conditions')";

$result = mysqli_query($con, $q);

if ($result) {
    $response["status"] = "1";
    $response["message"] = "Registration successful";
} else {
    $response["status"] = "0";
    $response["message"] = "Registration failed";
}

echo json_encode($response);
?>