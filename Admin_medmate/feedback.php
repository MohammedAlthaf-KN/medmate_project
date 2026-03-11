<?php
include 'conn.php';
// Delete  query
if (isset($_POST['delete_doctor'])) {
    $doctor_id = $_POST['doctor_id'];
    
    // Delete query
    $delete_query = "DELETE FROM feedback WHERE id = $doctor_id";
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
$sql = "SELECT * FROM feedback";
$result = $conn->query($sql);

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback</title>
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
                <h3><i class="bi bi-chat-left-dots-fill"></i> Feedback</h3>
            </div>
            <div class="card-body">
                <!-- Making the table responsive on smaller screens -->
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th><i class="bi bi-hash"></i> ID</th>
                                <th><i class="bi bi-person-fill"></i> Name</th>
                                <th><i class="bi bi-phone-fill"></i> Mobile Number</th>
                                <th><i class="bi bi-pencil-fill"></i> Feedback</th>
                                <th><i class="bi bi-star-fill"></i> Rating</th>
                                <th><i class="bi bi-trash-fill"></i> Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            // Display feedback data
                            if ($result->num_rows > 0) {
                                while ($row = $result->fetch_assoc()) {
                                    echo "<tr>";
                                    echo "<td>" . $row['id'] . "</td>";
                                    echo "<td>" . $row['name'] . "</td>";
                                    echo "<td>" . $row['mobile_no'] . "</td>";
                                    echo "<td>" . $row['feedback'] . "</td>";
                                    echo "<td>" . $row['rating'] . "</td>";
                                    echo "<td>
                                            <form method='POST' action='' style='display:inline;' class='d-inline'>
                                                <input type='hidden' name='doctor_id' value='" . $row['id'] . "' />
                                                <button type='submit' name='delete_feedback' class='btn btn-danger action-btn' onclick='return confirm(\"Are you sure you want to delete this feedback?\");'>
                                                    <i class='bi bi-trash-fill'></i> Delete
                                                </button>
                                            </form>
                                          </td>";
                                    echo "</tr>";
                                }
                            } else {
                                echo "<tr><td colspan='6'>No records found</td></tr>";
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
