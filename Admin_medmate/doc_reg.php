<?php
include 'conn.php';

// Update status query
if (isset($_POST['update_status'])) {
    $doctor_id = $_POST['doctor_id'];
    $new_status = 'approved';  
    
    // Update query
    $update_query = "UPDATE doctor_register SET status = '$new_status' WHERE id = $doctor_id";
    if ($conn->query($update_query) === TRUE) {
        echo "<script type='text/javascript'>
        alert('Status updated successfully');
        window.location.href = window.location.href; // Refresh page
      </script>";
    } else {
        echo "Error: " . $conn->error;
    }
}

// Delete doctor query
if (isset($_POST['delete_doctor'])) {
    $doctor_id = $_POST['doctor_id'];
    
    // Delete query
    $delete_query = "DELETE FROM doctor_register WHERE id = $doctor_id";
    if ($conn->query($delete_query) === TRUE) {
        echo "<script type='text/javascript'>
        alert('Doctor deleted successfully');
        window.location.href = window.location.href; // Refresh page
      </script>";
    } else {
        echo "Error: " . $conn->error;
    }
}

// Fetch doctors data
$sql = "SELECT * FROM doctor_register";
$result = $conn->query($sql);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Registration</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
        }
        .container {
            max-width: 1200px; /* Limiting the container width */
            margin-top: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .action-btn {
            padding: 6px 10px;
            font-size: 14px;
        }
        .table-responsive {
            overflow-x: auto; /* Enable horizontal scrolling for small screens */
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="card mt-4">
            <div class="card-header bg-primary text-white">
                <h3><i class="bi bi-person-lines-fill"></i> Doctor Registration List</h3>
            </div>
            <div class="card-body">
                <!-- Making the table responsive on smaller screens -->
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Speciality</th>
                                <th>Date of Birth</th>
                                <th>Gender</th>
                                <th>Experience</th>
                                <th>Email</th>
                                <th>Hospital</th>
                                <th>Mobile Number</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            // Display doctor data
                            if ($result->num_rows > 0) {
                                while ($row = $result->fetch_assoc()) {
                                    echo "<tr>";
                                    echo "<td>" . $row['id'] . "</td>";
                                    echo "<td>" . $row['name'] . "</td>";
                                    echo "<td>" . $row['speciality'] . "</td>";
                                    echo "<td>" . $row['dob'] . "</td>";
                                    echo "<td>" . $row['gender'] . "</td>";
                                    echo "<td>" . $row['experience'] . "</td>";
                                    echo "<td>" . $row['email'] . "</td>";
                                    echo "<td>" . $row['hospital'] . "</td>";
                                    echo "<td>" . $row['mobilenumber'] . "</td>";
                                    echo "<td>" . $row['status'] . "</td>";
                                    echo "<td>
                                            <form method='POST' action='' style='display:inline;' class='d-inline'>
                                                <input type='hidden' name='doctor_id' value='" . $row['id'] . "' />
                                                <button type='submit' name='update_status' class='btn btn-success action-btn'>Approve</button>
                                            </form>
                                            <form method='POST' action='' style='display:inline;' class='d-inline'>
                                                <input type='hidden' name='doctor_id' value='" . $row['id'] . "' />
                                                <button type='submit' name='delete_doctor' class='btn btn-danger action-btn' onclick='return confirm(\"Are you sure you want to delete this doctor?\");'>Delete</button>
                                            </form>
                                          </td>";
                                    echo "</tr>";
                                }
                            } else {
                                echo "<tr><td colspan='11'>No records found</td></tr>";
                            }
                            ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<?php
// Close the database connection
$conn->close();
?>
