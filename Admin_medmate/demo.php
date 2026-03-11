<?php
include 'conn.php';
function countRows($tableName, $conn) {
    $result = $conn->query("SELECT COUNT(*) AS count FROM $tableName");
    $row = $result->fetch_assoc();
    return $row['count']; 
}
$rowCount = countRows("doctor_register", $conn);
$Bookings = countRows("doctorbooking", $conn);

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Professional Admin Dashboard</title>
  <!-- Bootstrap CSS -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* General Styles */
    body {
      font-family: 'Arial', sans-serif;
      background: linear-gradient(135deg, #4b5e6b, #f1f8e9);
      margin: 0;
      padding: 0;
    }

    /* Sidebar Styling */
    .sidebar {
      height: 100vh;
      width: 280px;
      position: fixed;
      top: 0;
      left: 0;
      background: linear-gradient(135deg, #758993, #2575fc);
      box-shadow: 5px 0 15px rgba(0, 0, 0, 0.3);
      padding-top: 30px;
    }

    .sidebar h4 {
      color: #fff;
      text-align: center;
      font-weight: bold;
    }

    .sidebar a {
      color: #f1f1f1;
      text-decoration: none;
      padding: 15px 20px;
      display: block;
      border-radius: 8px;
      margin: 10px 20px;
      transition: all 0.3s ease;
    }

    .sidebar a:hover {
      background: rgba(255, 255, 255, 0.2);
      transform: scale(1.05);
    }

    /* Content Area Styling */
    .content {
      margin-left: 280px;
      padding: 30px;
    }

    h1 {
      font-size: 2.5rem;
      font-weight: bold;
      color: #333;
    }

    .card {
      border: none;
      border-radius: 15px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
      transition: transform 0.3s ease;
    }

    .card:hover {
      transform: translateY(-10px);
    }

    .card-title {
      font-size: 1.25rem;
      font-weight: bold;
    }

    .btn {
      border-radius: 20px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
      transition: all 0.3s ease;
    }

    .btn:hover {
      transform: scale(1.1);
    }
  </style>
</head>
<body>
  <!-- Sidebar -->
  <div class="sidebar">
    <h4>Admin Dashboard</h4>
    <a href="demo.php"><i class="material-icons">dashboard</i> Dashboard</a>
    <a href="doc_book.php"><i class="material-icons">event_note</i> Doctor Booking List</a>
<a href="doc_reg.php"><i class="material-icons">person_add</i> Doctor Registration List</a>
<a href="register.php"><i class="material-icons">group</i> Users</a>
<a href="prescription.php"><i class="material-icons">receipt</i> Prescription</a>
<a href="healthcare.php"><i class="material-icons">local_hospital</i> Healthcare</a>
<a href="feedback.php"><i class="material-icons">feedback</i> Feedbacks</a>
  </div>

  <!-- Main Content -->
<div class="content">
  <h1>Welcome, Admin</h1>
  <div class="row mt-4">
    <!-- Card 1 -->
    <div class="col-md-4">
      <div class="card text-center text-white bg-danger">
        <div class="card-body">
          <h5 class="card-title">
            <i class="material-icons">assessment</i> Doctors Count
          </h5>
          <p class="card-text">Manage and view</p>
          <p class="card-text"><?php echo $rowCount; ?></p>
          <a href="doc_reg.php" class="btn btn-light">View</a>
        </div>
      </div>
    </div>

    <!-- Card 2 -->
    <div class="col-md-4">
      <div class="card text-center text-white bg-info 
">
        <div class="card-body">
          <h5 class="card-title">
            <i class="material-icons">track_changes</i> Doctor Bookings
          </h5>
          <p class="card-text">To Know Bookings</p>
          <p class="card-text"><?php echo $rowCount; ?></p>
          <a href="doc_book.php" class="btn btn-light">View</a>
        </div>
      </div>
    </div>

    <!-- Card 3 -->
    <div class="col-md-4">
      <div class="card text-center text-white bg-dark">
        <div class="card-body">
          <h5 class="card-title">
            <i class="material-icons">report</i> LOGOUT
          </h5>
          <p class="card-text">Securely logout from system</p>
          <a href="adlog.php" class="btn btn-light"><i class="material-icons">logout</i>logout </a>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
