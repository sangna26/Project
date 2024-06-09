-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2024 at 05:16 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movieticketsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `book_id` int(11) NOT NULL,
  `theatre_id` varchar(50) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `shows` varchar(1000) NOT NULL,
  `prices` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`book_id`, `theatre_id`, `movie_id`, `shows`, `prices`) VALUES
(1, '11', 21, '11:00\r\n12:00\r\n3:00\r\n4:00', '720'),
(2, '12', 21, '12:00\r\n3:00\r\n4:00', '220'),
(3, '13', 23, '11:00\r\n12:00\r\n3:00\r\n4:00', '780'),
(4, '15', 24, '11:00\r\n3:00\r\n4:00', '330'),
(5, '123', 25, '11:00\r\n12:00\r\n3:00', '120');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `movie_id` int(11) NOT NULL,
  `movie_name` varchar(100) NOT NULL,
  `movie_length` int(11) NOT NULL,
  `movie_category` varchar(20) NOT NULL,
  `movie_description` varchar(1045) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movie_id`, `movie_name`, `movie_length`, `movie_category`, `movie_description`) VALUES
(21, 'Animal', 180, 'action', 'a action movie'),
(22, 'baagi', 120, 'real', 'a real movie'),
(23, 'javan', 90, 'real', ' real movie'),
(24, 'padhan', 150, 'action', 'a action movie'),
(25, 'kerala story', 80, 'action', 'a action movie'),
(26, 'the king', 96, 'action', 'the action movie');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `review` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`review_id`, `movie_id`, `review`) VALUES
(31, 21, 'good'),
(32, 22, 'nice'),
(33, 23, 'bad'),
(34, 24, 'good'),
(35, 25, 'good');

-- --------------------------------------------------------

--
-- Table structure for table `theatre`
--

CREATE TABLE `theatre` (
  `theatre_id` int(11) NOT NULL,
  `theatre_name` varchar(50) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `seats` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `theatre`
--

INSERT INTO `theatre` (`theatre_id`, `theatre_name`, `address`, `seats`) VALUES
(11, 'PVR', 'sola cross road', 220),
(12, 'RAJHANSH', 'kalupur', 180),
(13, 'WIDE ANGEL', 'sanad', 100),
(14, 'CINEPOLICE', 'thaltej', 150),
(15, 'RR', 'iskon', 90);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_booking`
--

CREATE TABLE `ticket_booking` (
  `tk_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `theatre_id` int(11) NOT NULL,
  `seats` int(11) NOT NULL,
  `show_time` varchar(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ticket_booking`
--

INSERT INTO `ticket_booking` (`tk_id`, `user_id`, `theatre_id`, `seats`, `show_time`, `date`) VALUES
(51, 21, 11, 3, '11:00', '2024-04-03'),
(52, 22, 12, 10, '3:00', '2024-04-12'),
(53, 23, 13, 15, '12:00', '2024-04-14'),
(54, 24, 14, 2, '6:00', '2024-04-17'),
(55, 1, 3, 2, '3:00', '0000-00-00'),
(56, 8, 1, 2, '11:00', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_type` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(1000) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone_number` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_type`, `name`, `email`, `password`, `phone_number`) VALUES
(1, 'customer', 'ruchita', 'ruchita21@gmail.com', '12345678', '7894561230'),
(2, 'customer', 'muskan', 'muskan43@gmail.com', '12345678', '7894561230'),
(3, 'customer', 'riya', 'riya14@gmail.com', '12345678', '7894561230'),
(41, 'admin', 'ruchita', 'ruchita23@gmail.com', '12345678', '1234567890'),
(42, 'admin', 'mahenoor', 'patelr123@gmail.com', '12345678', '7894561230'),
(43, 'admin', 'kartik', 'patelr2893@gmail.com', '12345678', '7894561230'),
(44, 'admin', 'sangna', 'patelrf23@gmail.com', '12345678', '7894561230'),
(45, 'admin', 'janki', 'patelr8909@gmail.com', '12345678', '7894561230');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movie_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_id`);

--
-- Indexes for table `theatre`
--
ALTER TABLE `theatre`
  ADD PRIMARY KEY (`theatre_id`);

--
-- Indexes for table `ticket_booking`
--
ALTER TABLE `ticket_booking`
  ADD PRIMARY KEY (`tk_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `movie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `theatre`
--
ALTER TABLE `theatre`
  MODIFY `theatre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `ticket_booking`
--
ALTER TABLE `ticket_booking`
  MODIFY `tk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
