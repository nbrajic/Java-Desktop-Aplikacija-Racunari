-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2021 at 10:37 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `racunari`
--

-- --------------------------------------------------------

--
-- Table structure for table `racunari`
--

CREATE TABLE `racunari` (
  `vrsta` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `cena` double NOT NULL,
  `nov` tinyint(1) NOT NULL,
  `id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `racunari`
--

INSERT INTO `racunari` (`vrsta`, `cena`, `nov`, `id`) VALUES
('Desktop', 150000, 1, 1),
('Desktop', 100000, 0, 2),
('Laptop', 123000, 1, 10),
('Desktop', 78000, 0, 11),
('Laptop', 154000, 1, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `racunari`
--
ALTER TABLE `racunari`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `racunari`
--
ALTER TABLE `racunari`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
