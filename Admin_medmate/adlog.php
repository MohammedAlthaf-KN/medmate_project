<?php

$admin_email = 'admin@gmail.com';
$admin_password = '123123'; 

// Check if form is submitted
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $email = $_POST['email'];
    $password = $_POST['password'];

    // Simple validation check
    if ($email === $admin_email && $password === $admin_password) {
        echo "<script>
                alert('Welcome, Admin!');
                window.location.href = 'demo.php';  
              </script>";
    } else {
        echo "<script>
                alert('Invalid credentials. Please try again.');
                window.location.href = 'adlog.php';  
              </script>";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>

    <!-- Bootstrap 5 CDN for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS for more advanced styling -->
    <style>
        body {
            background: linear-gradient(135deg, #4e73df, #1e3d58);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .login-card {
            background-color: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        .login-card h2 {
            text-align: center;
            font-size: 2rem;
            color: #333;
        }

        .form-label {
            font-size: 1.1rem;
            font-weight: bold;
        }

        .form-control {
            border-radius: 10px;
            padding: 15px;
            font-size: 1rem;
        }

        .btn-custom {
            background-color: #4e73df;
            color: white;
            border-radius: 10px;
            font-size: 1.1rem;
            padding: 12px;
            width: 100%;
            transition: background-color 0.3s;
        }

        .btn-custom:hover {
            background-color: #3559d4;
        }

        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <div class="login-card">
        <h2>Admin Login</h2>

        <form  method="POST">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required placeholder="Enter your email">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="Enter your password">
            </div>
            <button type="submit" class="btn btn-custom">Login</button>
        </form>
    </div>

    <!-- Bootstrap 5 JavaScript CDN -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
