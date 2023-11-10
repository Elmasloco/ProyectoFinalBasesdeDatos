-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 10-11-2023 a las 04:53:38
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `genero` varchar(30) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `ejemplares` int(11) NOT NULL,
  `seccion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id`, `titulo`, `autor`, `isbn`, `genero`, `fecha_publicacion`, `ejemplares`, `seccion_id`) VALUES
(1, 'Cien años de soledad', 'Gabriel Garcia Marquez', '12585874', 'Romance', '1960-09-30', 10, 3),
(3, 'Doce cuentos peregrinos', 'Gabriel Garcia Marquez', '569201478', 'Ficcion', '1992-05-17', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `direccion` varchar(25) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `contraseña` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `apellido`, `direccion`, `telefono`, `correo`, `contraseña`) VALUES
(1, 'Samuel', 'Arciniegas', 'null', 'null', 'slarci@gmail.com', '8s4d84a'),
(2, 'Nicolas', 'Tarazona', 'null', 'null', 'nico.tarazona@gmail.com', 'n123*/t'),
(3, 'Daniel', 'Tarazona', 'null', 'null', 'dani.tarazona@gmail.com', 'dan!%$909'),
(4, 'Jorge', 'Tarazona', 'null', 'null', 'je.tarazona@gmail.com', 'sobelo!'),
(5, 'Sandra', 'Arciniegas', 'null', 'null', 'sandrita@gmail.com', '2525'),
(6, 'Romoncio', 'Patiño', 'null', 'null', 'romon@gmail.com', '4s7de'),
(7, 'JJ', 'Mantilla', 'null', 'null', 'jjmantilla@gmail.com', '5df8a'),
(8, 'Pablo', 'Reyes', 'null', 'null', 'pablito@gmail.com', '6ad4a'),
(9, 'Laura', 'Muñoz', 'null', 'null', 'laurita@gmail.com', '8f6af'),
(10, 'Santiago', 'Muñoz', 'Calle 123', '2586574104', 'santi@gmail.com', 'null'),
(11, 'Michelle', 'Ramirez', 'Calle 79', '981819', 'michiram@gmail.com', 'null'),
(12, 'Felipe', 'Pardo', 'Calle 79 b#123', '1222331', 'pipepa@gmail.com', 'null'),
(13, 'Natasha', 'Gonzales', 'Calle 79 b#123', '1222331', 'natashina@gmail.com', 'null'),
(14, 'Andres', 'Diaz', 'calle 88 este 1', '12221', 'buenosdiaz@gmail.com', 'null'),
(15, 'Ximena', 'Acosta', '', 'null', 'null', 'laprofe@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `id` int(11) NOT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date NOT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `libro_id` int(11) NOT NULL,
  `persona_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`id`, `fecha_prestamo`, `fecha_devolucion`, `estado`, `libro_id`, `persona_id`) VALUES
(2, '2023-10-04', '2023-10-30', 'Bueno', 3, 1),
(3, '2023-10-04', '2023-11-10', 'bueno', 1, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(25) NOT NULL,
  `id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`, `id_persona`) VALUES
(2, 'Administrador', 8),
(3, 'Administrador', 9),
(4, 'Usuario', 10),
(5, 'Usuario', 11),
(6, 'Usuario', 12),
(7, 'Usuario', 13),
(8, 'Usuario', 14),
(9, 'Administrador', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `ubicacion` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`id`, `nombre`, `ubicacion`) VALUES
(1, 'Ficción', 'Estante 1'),
(2, 'No ficción', 'Estante 2'),
(3, 'Romance', 'Estante 3'),
(4, 'Comedia', 'Estante 4'),
(5, 'Poesia', 'Estante 5'),
(6, 'Drama', 'Estante 6'),
(8, 'Misterio', 'Estante 8'),
(9, 'Terror', 'Estante 9'),
(10, 'Aventura', 'Estante 10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seccion_id` (`seccion_id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `libro_id` (`libro_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `seccion`
--
ALTER TABLE `seccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`seccion_id`) REFERENCES `seccion` (`id`);

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`),
  ADD CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
