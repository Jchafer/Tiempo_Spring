-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-02-2021 a las 02:24:54
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `predicciondb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotanieve`
--

CREATE TABLE `cotanieve` (
  `idCotaNieve` int(8) NOT NULL,
  `periodo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `idDia` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cotanieve`
--

INSERT INTO `cotanieve` (`idCotaNieve`, `periodo`, `idDia`) VALUES
(1, '12-24', 1),
(2, '00-06', 1),
(3, '12-18', 1),
(4, '18-24', 1),
(5, '06-12', 1),
(6, '00-24', 1),
(7, '00-12', 1),
(8, '12-24', 2),
(9, '06-12', 2),
(10, '12-18', 2),
(11, '00-24', 2),
(12, '18-24', 2),
(13, '00-06', 2),
(14, '00-12', 2),
(15, '12-24', 3),
(16, '00-12', 3),
(17, '00-24', 3),
(18, '00-24', 4),
(19, '00-12', 4),
(20, '12-24', 4),
(21, '', 5),
(22, '', 6),
(23, '', 7),
(24, '00-24', 8),
(25, '18-24', 8),
(26, '06-12', 8),
(27, '00-06', 8),
(28, '00-12', 8),
(29, '12-18', 8),
(30, '12-24', 8),
(31, '18-24', 9),
(32, '06-12', 9),
(33, '12-24', 9),
(34, '00-24', 9),
(35, '12-18', 9),
(36, '00-06', 9),
(37, '00-12', 9),
(38, '00-24', 10),
(39, '00-12', 10),
(40, '12-24', 10),
(41, '00-24', 11),
(42, '00-12', 11),
(43, '12-24', 11),
(44, '', 12),
(45, '', 13),
(46, '', 14),
(47, '12-24', 15),
(48, '00-06', 15),
(49, '12-18', 15),
(50, '00-12', 15),
(51, '06-12', 15),
(52, '18-24', 15),
(53, '00-24', 15),
(54, '06-12', 16),
(55, '12-24', 16),
(56, '12-18', 16),
(57, '00-06', 16),
(58, '00-24', 16),
(59, '00-12', 16),
(60, '18-24', 16),
(61, '00-24', 17),
(62, '12-24', 17),
(63, '00-12', 17),
(64, '12-24', 18),
(65, '00-12', 18),
(66, '00-24', 18),
(67, '', 19),
(68, '', 20),
(69, '', 21),
(70, '00-12', 22),
(71, '00-24', 22),
(72, '12-18', 22),
(73, '00-06', 22),
(74, '18-24', 22),
(75, '12-24', 22),
(76, '06-12', 22),
(77, '06-12', 23),
(78, '00-12', 23),
(79, '12-24', 23),
(80, '00-06', 23),
(81, '18-24', 23),
(82, '12-18', 23),
(83, '00-24', 23),
(84, '00-12', 24),
(85, '12-24', 24),
(86, '00-24', 24),
(87, '00-24', 25),
(88, '12-24', 25),
(89, '00-12', 25),
(90, '', 26),
(91, '', 27),
(92, '', 28),
(93, '00-06', 29),
(94, '06-12', 29),
(95, '00-24', 29),
(96, '00-12', 29),
(97, '12-18', 29),
(98, '12-24', 29),
(99, '18-24', 29),
(100, '06-12', 30),
(101, '00-12', 30),
(102, '12-18', 30),
(103, '12-24', 30),
(104, '18-24', 30),
(105, '00-06', 30),
(106, '00-24', 30),
(107, '12-24', 31),
(108, '00-12', 31),
(109, '00-24', 31),
(110, '12-24', 32),
(111, '00-12', 32),
(112, '00-24', 32),
(113, '', 33),
(114, '', 34),
(115, '', 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dia`
--

CREATE TABLE `dia` (
  `idDia` int(8) NOT NULL,
  `poblacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `tempMaxima` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `tempMinima` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `dia`
--

INSERT INTO `dia` (`idDia`, `poblacion`, `fecha`, `tempMaxima`, `tempMinima`) VALUES
(1, 'Alzira', '2021-02-06', '19', '8'),
(2, 'Alzira', '2021-02-07', '19', '8'),
(3, 'Alzira', '2021-02-08', '19', '11'),
(4, 'Alzira', '2021-02-09', '21', '10'),
(5, 'Alzira', '2021-02-10', '18', '9'),
(6, 'Alzira', '2021-02-11', '21', '8'),
(7, 'Alzira', '2021-02-12', '22', '11'),
(8, 'Benigànim', '2021-02-06', '19', '7'),
(9, 'Benigànim', '2021-02-07', '18', '8'),
(10, 'Benigànim', '2021-02-08', '19', '10'),
(11, 'Benigànim', '2021-02-09', '20', '9'),
(12, 'Benigànim', '2021-02-10', '17', '8'),
(13, 'Benigànim', '2021-02-11', '20', '7'),
(14, 'Benigànim', '2021-02-12', '22', '10'),
(15, 'Carcaixent', '2021-02-06', '20', '8'),
(16, 'Carcaixent', '2021-02-07', '19', '8'),
(17, 'Carcaixent', '2021-02-08', '20', '11'),
(18, 'Carcaixent', '2021-02-09', '21', '10'),
(19, 'Carcaixent', '2021-02-10', '18', '8'),
(20, 'Carcaixent', '2021-02-11', '21', '7'),
(21, 'Carcaixent', '2021-02-12', '22', '10'),
(22, 'Genovés', '2021-02-06', '19', '7'),
(23, 'Genovés', '2021-02-07', '18', '8'),
(24, 'Genovés', '2021-02-08', '19', '10'),
(25, 'Genovés', '2021-02-09', '20', '9'),
(26, 'Genovés', '2021-02-10', '17', '8'),
(27, 'Genovés', '2021-02-11', '20', '7'),
(28, 'Genovés', '2021-02-12', '22', '10'),
(29, 'Xàtiva', '2021-02-06', '19', '8'),
(30, 'Xàtiva', '2021-02-07', '18', '8'),
(31, 'Xàtiva', '2021-02-08', '19', '10'),
(32, 'Xàtiva', '2021-02-09', '20', '9'),
(33, 'Xàtiva', '2021-02-10', '17', '8'),
(34, 'Xàtiva', '2021-02-11', '20', '7'),
(35, 'Xàtiva', '2021-02-12', '22', '10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Id` int(11) NOT NULL,
  `Nombres` varchar(255) NOT NULL,
  `Correo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Id`, `Nombres`, `Correo`) VALUES
(1, 'Antonio', 'antonio@gmail.com'),
(2, 'Maria', 'maria@yahoo.com'),
(3, 'Pedro', 'pedro@hotmail.com'),
(4, 'Sofia', 'sofia@gmail.com'),
(9, 'Carlos', 'carlos@gmail.com'),
(10, 'admin', 'admin@admin.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cotanieve`
--
ALTER TABLE `cotanieve`
  ADD PRIMARY KEY (`idCotaNieve`),
  ADD KEY `FK_IDDIA` (`idDia`);

--
-- Indices de la tabla `dia`
--
ALTER TABLE `dia`
  ADD PRIMARY KEY (`idDia`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cotanieve`
--
ALTER TABLE `cotanieve`
  MODIFY `idCotaNieve` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT de la tabla `dia`
--
ALTER TABLE `dia`
  MODIFY `idDia` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cotanieve`
--
ALTER TABLE `cotanieve`
  ADD CONSTRAINT `FK_IDDIA` FOREIGN KEY (`idDia`) REFERENCES `dia` (`idDia`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
