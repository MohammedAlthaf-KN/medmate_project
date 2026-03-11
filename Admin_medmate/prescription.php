<?php
include 'conn.php';



// Delete  query
if (isset($_POST['delete_doctor'])) {
    $doctor_id = $_POST['doctor_id'];
    
    // Delete query
    $delete_query = "DELETE FROM prescription WHERE id = $doctor_id";
    if ($conn->query($delete_query) === TRUE) {
        echo "<script type='text/javascript'>
        alert(' deleted successfully');
        window.location.href = window.location.href; // Refresh page
      </script>";
    } else {
        echo "Error: " . $conn->error;
    }
}

// Fetch doctors data
$sql = "SELECT * FROM prescription";
$result = $conn->query($sql);

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prescription</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
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
                <h3><i class="bi bi-file-earmark-medical-fill"></i> Prescription List</h3>
            </div>
            <div class="card-body">
                <!-- Making the table responsive on smaller screens -->
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th><i class="bi bi-hash"></i> ID</th>
                                <th><i class="bi bi-person-fill"></i> Doctor Name</th>
                                <th><i class="bi bi-phone-fill"></i> Doctor Phone</th>
                                <th><i class="bi bi-person-circle"></i> Username</th>
                                <th><i class="bi bi-phone-fill"></i> User Phone</th>
                                <th><i class="bi bi-file-earmark-medical-fill"></i> Prescription</th>
                                <th><i class="bi bi-calendar-check-fill"></i> Date</th>
                                <th><i class="bi bi-trash-fill"></i> Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            // Display prescription data
                            if ($result->num_rows > 0) {
                                while ($row = $result->fetch_assoc()) {
                                    echo "<tr>";
                                    echo "<td>" . $row['id'] . "</td>";
                                    echo "<td>" . $row['doctorname'] . "</td>";
                                    echo "<td>" . $row['doctorphone'] . "</td>";
                                    echo "<td>" . $row['username'] . "</td>";
                                    echo "<td>" . $row['userphone'] . "</td>";
                                    echo "<td>" . $row['prescription'] . "</td>";
                                    echo "<td>" . $row['date'] . "</td>";
                                    echo "<td>
                                            <form method='POST' action='' style='display:inline;' class='d-inline'>
                                                <input type='hidden' name='doctor_id' value='" . $row['id'] . "' />
                                                <button type='submit' name='delete_doctor' class='btn btn-danger action-btn' onclick='return confirm(\"Are you sure you want to delete this prescription?\");'>
                                                    <i class='bi bi-trash-fill'></i> Delete
                                                </button>
                                            </form>
                                          </td>";
                                    echo "</tr>";
                                }
                            } else {
                                echo "<tr><td colspan='8'>No records found</td></tr>";
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
