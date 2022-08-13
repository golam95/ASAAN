-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2022 at 02:39 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `buyproduct`
--

CREATE TABLE `buyproduct` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `govt_price` double DEFAULT NULL,
  `productcategory` varchar(255) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buyproduct`
--

INSERT INTO `buyproduct` (`id`, `date`, `govt_price`, `productcategory`, `productname`, `price`) VALUES
(1, '2022-03-05 17:11:43', 10, '15', 'Carrot', 20),
(2, '2022-03-05 17:12:04', 6, '15', 'Daikon', 10),
(3, '2022-03-05 17:12:26', 40, '15', 'Onions', 35),
(4, '2022-03-05 17:12:47', 40, '15', 'Potato', 20);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `categoryname` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `categoryname`) VALUES
(17, 'Wheat'),
(18, 'Vegetables'),
(16, 'Rice');

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `comment`, `date`, `email`, `name`, `status`, `subject`) VALUES
(1, 'i need to know about your business policy', '2022-03-05 16:57:48', 'golamnobi277@gmail.com', 'Md Golam Nobi Sheikh', '0', 'Suggestion');

-- --------------------------------------------------------

--
-- Table structure for table `cusorder`
--

CREATE TABLE `cusorder` (
  `id` bigint(20) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `shiping_address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `farmerschedule`
--

CREATE TABLE `farmerschedule` (
  `id` bigint(20) NOT NULL,
  `schedule_time` varchar(255) DEFAULT NULL,
  `schudle_date` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farmerschedule`
--

INSERT INTO `farmerschedule` (`id`, `schedule_time`, `schudle_date`, `date`, `division`, `email`, `name`, `product_name`, `status`, `user_id`) VALUES
(1, '9AM-10AM', '13/08/2022', '2022-08-13 12:13:56', 'Dhaka', 'rayhan@gmail.com', 'rayhan', 'Carrot', '1', 3),
(2, '9AM-10AM', '12/08/2022', '2022-08-13 12:14:28', 'Dhaka', 'rayhan@gmail.com', 'rayhan', 'Carrot', '1', 3);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `sellingmsg`
--

CREATE TABLE `sellingmsg` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sellproduct`
--

CREATE TABLE `sellproduct` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `govt_price` double DEFAULT NULL,
  `productcategory` varchar(255) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sellproduct`
--

INSERT INTO `sellproduct` (`id`, `date`, `govt_price`, `productcategory`, `productname`, `quantity`, `price`) VALUES
(1, '2022-03-05 17:13:23', 60, '16', 'Rice', NULL, 65),
(2, '2022-08-13 12:09:15', 50, '18', 'Carrot', NULL, 50);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `block` varchar(255) DEFAULT NULL,
  `can_add` varchar(255) DEFAULT NULL,
  `can_delete` varchar(255) DEFAULT NULL,
  `can_update` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `block`, `can_add`, `can_delete`, `can_update`, `country`, `create_date`, `email`, `firstname`, `password`, `phone`, `rolename`, `username`) VALUES
(2, NULL, NULL, NULL, NULL, 'Bangladesh', '2022-08-02 17:27:24', 'mehedi@gmail.com', 'Mehedi Hasan', '$2a$10$Fj4k4J8v1YHFtlQhPzIVkef2bsmSKasF4RbKFWVTKrikycGRCbDGi', '01755719900', 'ROLE_ADMIN', 'mehedi'),
(3, NULL, NULL, NULL, NULL, 'Bangladesh', '2022-08-02 17:28:05', 'rayhan@gmail.com', 'Rayhan', '$2a$10$xcJfQ4itlxwb6Wfxh4YO2.bU/v5.Ix/ThndT7pbAQyRzwAEbsE8Ue', '01755719900', 'ROLE_USER', 'rayhan'),
(5, NULL, NULL, NULL, NULL, 'Dubai', '2022-08-02 17:37:59', 'khalid@gmail.com', 'Md Khalid Rahman', '$2a$10$wQVXbg7pIfG2piynLkOK4uk13T1oLqZ8uBHcligPCz20Hk9devx0q', '01755617700', 'ROLE_USER', 'khalid');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2),
(3, 1),
(4, 1),
(5, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buyproduct`
--
ALTER TABLE `buyproduct`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cusorder`
--
ALTER TABLE `cusorder`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `farmerschedule`
--
ALTER TABLE `farmerschedule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sellingmsg`
--
ALTER TABLE `sellingmsg`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sellproduct`
--
ALTER TABLE `sellproduct`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buyproduct`
--
ALTER TABLE `buyproduct`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cusorder`
--
ALTER TABLE `cusorder`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `farmerschedule`
--
ALTER TABLE `farmerschedule`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sellingmsg`
--
ALTER TABLE `sellingmsg`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sellproduct`
--
ALTER TABLE `sellproduct`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
