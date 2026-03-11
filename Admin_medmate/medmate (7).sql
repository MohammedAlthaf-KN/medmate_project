-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2025 at 11:35 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medmate`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctorbooking`
--

CREATE TABLE `doctorbooking` (
  `id` int(11) NOT NULL,
  `docname` varchar(300) NOT NULL,
  `docemail` varchar(300) NOT NULL,
  `username` varchar(300) NOT NULL,
  `usernumber` varchar(300) NOT NULL,
  `useremail` varchar(300) NOT NULL,
  `userage` varchar(300) NOT NULL,
  `reason` varchar(300) NOT NULL,
  `date` varchar(300) NOT NULL,
  `time` varchar(300) NOT NULL,
  `status` varchar(300) NOT NULL,
  `fee` varchar(300) NOT NULL,
  `feedate` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctorbooking`
--

INSERT INTO `doctorbooking` (`id`, `docname`, `docemail`, `username`, `usernumber`, `useremail`, `userage`, `reason`, `date`, `time`, `status`, `fee`, `feedate`) VALUES
(15, 'Dr.Althaf', 'althaf@gmail.com', 'gopika', '0987654321', 'gopika@gmail.com', '25', 'nxnxnxnx', '19/1/2025', '10am-10.30am', 'Approve', '200', '15-01-2025 11:35:52'),
(19, 'Dr.Althaf', 'althaf@gmail.com', 'gopika', '0987654321', 'gopika@gmail.com', '25', 'nxnxnxnx', '19/1/2025', '10am-10.30am', 'Approve', '200', '15-01-2025 11:35:52'),
(20, 'Dr.Althaf', 'althaf@gmail.com', 'gopika', '0987654321', 'gopika@gmail.com', '25', 'nxnxnxnx', '19/1/2025', '10am-10.30am', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_register`
--

CREATE TABLE `doctor_register` (
  `id` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `speciality` varchar(300) NOT NULL,
  `dob` varchar(300) NOT NULL,
  `gender` varchar(300) NOT NULL,
  `experience` varchar(300) NOT NULL,
  `email` varchar(300) NOT NULL,
  `hospital` varchar(300) NOT NULL,
  `mobilenumber` varchar(300) NOT NULL,
  `password` varchar(300) NOT NULL,
  `document` varchar(2500) NOT NULL,
  `status` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctor_register`
--

INSERT INTO `doctor_register` (`id`, `name`, `speciality`, `dob`, `gender`, `experience`, `email`, `hospital`, `mobilenumber`, `password`, `document`, `status`) VALUES
(33, 'Dr.Althaf', 'Orthopedic', '11/12/1998', 'Male', '10year', 'althaf@gmail.com', 'Craft Hospital', '7994641609', '121213', '1000616113.jpg', 'approve'),
(34, 'Dr.Hashif', 'Endocrinologist', '11/12/1975', 'Male', '11', 'hashif@gmail.com', 'Modern', '9988776655', '123123', '1000616067.jpg', 'approve'),
(35, 'Dr.Hisham', 'Ophthalmologist', '11/12/1987', 'Male', '4years', 'hisham@gmail.com', 'AR Hospital', '9911223344', '12341234', '1000615974.jpg', 'approve'),
(36, 'hashif', 'Dermatologist', '16/12/1990', 'Male', '10', 'hash@gmail.com', 'modern ', '9846300227', '123456', '1000123653.jpg', 'approve'),
(37, 'Athul', 'Gynecologist', '9/9/1999', 'Male', '16', 'athulettayi@gmail.com', 'abc', '1234567891', '123123', '1000035897.jpg', ''),
(38, 'Dr Athul ettaayi', 'Gynecologist', '9/9/1999', 'Male', '9', 'ettaaayi@gmail.com', 'abc', '1212121212', '123123', '1000036286.jpg', '');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `mobile_no` varchar(300) NOT NULL,
  `feedback` varchar(300) NOT NULL,
  `rating` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `name`, `mobile_no`, `feedback`, `rating`) VALUES
(1, 'gopika', '0987654321', 'ncncnncjjdn', '4.0'),
(2, 'gopika', '0987654321', 'good doctor \n', '4.0');

-- --------------------------------------------------------

--
-- Table structure for table `healthcare`
--

CREATE TABLE `healthcare` (
  `id` int(11) NOT NULL,
  `title` varchar(300) NOT NULL,
  `tips` varchar(2500) NOT NULL,
  `description` varchar(3000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `healthcare`
--

INSERT INTO `healthcare` (`id`, `title`, `tips`, `description`) VALUES
(1, 'healthcare\r\n\r\n', 'Stay Hydrated', 'Drink plenty of water throughout the day. Aim for at least 8 glasses, or 2 liters, to keep your body hydrated, support digestion, and maintain skin health.'),
(2, 'healthcare', 'Eat a Balanced Diet', 'Include a variety of nutrient-rich foods in your diet, such as fruits, vegetables, whole grains, lean proteins, and healthy fats. This provides essential vitamins and minerals for overall health.');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `id` int(11) NOT NULL,
  `doctorname` varchar(300) NOT NULL,
  `doctorphone` varchar(300) NOT NULL,
  `username` varchar(300) NOT NULL,
  `userphone` varchar(300) NOT NULL,
  `prescription` varchar(2500) NOT NULL,
  `date` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`id`, `doctorname`, `doctorphone`, `username`, `userphone`, `prescription`, `date`) VALUES
(1, 'Dr.Althaf', '7994641609', 'gopika', '098765432', '1000036129.jpg', ''),
(2, 'Dr.Althaf', '7994641609', 'gopika', '0987654321', '1000036127.jpg', '2025-01-19 07:17:15'),
(3, 'Dr.Althaf', '7994641609', 'gopika', '0987654321', '1000036269.jpg', '19-01-2025'),
(4, 'Dr.Althaf', '7994641609', 'gopika', '0987654321', '1000036297.jpg', '19-01-2025');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `email` varchar(300) NOT NULL,
  `mobilenumber` varchar(300) NOT NULL,
  `password` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `name`, `email`, `mobilenumber`, `password`) VALUES
(47, 'name', 'email', '79941609', 'password'),
(48, 'Sanji', 'sanji@gmail.com', '8078004030', '123123'),
(50, 'Hashif pa', 'hashifpa1@gmail.com', '9846300227', '12345679'),
(53, 'tintu', 'tintu@gmail.com', '1234567890', '121212'),
(54, 'gopika', 'gopika@gmail.com', '0987654321', '123123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctorbooking`
--
ALTER TABLE `doctorbooking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor_register`
--
ALTER TABLE `doctor_register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `healthcare`
--
ALTER TABLE `healthcare`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctorbooking`
--
ALTER TABLE `doctorbooking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `doctor_register`
--
ALTER TABLE `doctor_register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `healthcare`
--
ALTER TABLE `healthcare`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
