-- MySQL dump 10.16  Distrib 10.1.10-MariaDB, for Win32 (AMD64)
--
-- Host: estebanisla.ddns.net    Database: acme_db
-- ------------------------------------------------------
-- Server version	10.1.23-MariaDB-9+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `acme_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `acme_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `acme_db`;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `ClienteID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DescuentoID` int(6) unsigned NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido_Paterno` varchar(20) NOT NULL,
  `Apellido_Materno` varchar(20) NOT NULL,
  `Telefono` char(10) NOT NULL,
  `RFC` char(16) NOT NULL,
  `Calle` varchar(25) NOT NULL,
  `Colonia` varchar(25) NOT NULL,
  `Numero_Interior` varchar(3) NOT NULL,
  `Numero_Exterior` varchar(4) NOT NULL,
  `Codigo_Postal` char(5) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Ciudad` varchar(20) NOT NULL,
  `Estado` varchar(20) NOT NULL,
  `Fecha_Creacion` date NOT NULL,
  `Total_Acumulado` double NOT NULL,
  PRIMARY KEY (`ClienteID`),
  UNIQUE KEY `RFC` (`RFC`),
  KEY `DescuentoID` (`DescuentoID`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`DescuentoID`) REFERENCES `descuentos` (`DescuentoID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'Brenda','Miranda','Guarneros','7717020560','GMA971220CD7','Climas','Industrial','150','150','23615','adri@adri.com','Itztapalapa','CDMX','2018-11-30',150.5);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuentos`
--

DROP TABLE IF EXISTS `descuentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descuentos` (
  `DescuentoID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Porcentaje` double NOT NULL,
  `Tipo` varchar(20) NOT NULL,
  PRIMARY KEY (`DescuentoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuentos`
--

LOCK TABLES `descuentos` WRITE;
/*!40000 ALTER TABLE `descuentos` DISABLE KEYS */;
INSERT INTO `descuentos` VALUES (1,'Salvajes Hoy',0.22,'Temporada'),(2,'Tocho Morocho',0.22,'Acumulado');
/*!40000 ALTER TABLE `descuentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `ProductoID` int(9) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) NOT NULL,
  `Tipo` varchar(30) NOT NULL,
  `Marca` varchar(20) NOT NULL,
  `Precio_Venta` double NOT NULL,
  `Unidad_Medida` varchar(2) NOT NULL,
  PRIMARY KEY (`ProductoID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Cemento','Construccion','Cruz Azul',160,'PZ'),(2,'Pintura','Liquidos','Comex',180.5,'LT'),(3,'Castillos','Construccion','Juarez',65,'MT');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores` (
  `id_proveedor` int(5) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido_paterno` varchar(20) NOT NULL,
  `apellido_materno` varchar(20) NOT NULL,
  `empresa` varchar(20) NOT NULL,
  `telefono` char(13) NOT NULL,
  `calle` varchar(25) NOT NULL,
  `numero` int(3) NOT NULL,
  `colonia` varchar(25) NOT NULL,
  `codigo_postal` varchar(5) NOT NULL,
  `email` varchar(30) NOT NULL,
  `ciudad` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (3,'Karla Andrea','Camacho','Barraza','Trupper','7757986321','Granada',105,'Rojo Gomez','43645','wicho@wicho.com','Tulancingo','Hidalgo'),(4,'Cecilia','Amado','Carrillo','Apasco','7757578912','Alfombras',109,'El pedregal','45632','cecy@cecy.com','Acapulco','Guerrero');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `sucursal_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `calle` varchar(60) DEFAULT NULL,
  `numero` varchar(3) DEFAULT NULL,
  `colonia` varchar(60) DEFAULT NULL,
  `codigo_postal` varchar(5) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `ciudad` varchar(60) DEFAULT NULL,
  `estado` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`sucursal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (2,'Revolucion','49','La cañada','43615','steph@steph.com','7757578912','Tulancingo','Hidalgo'),(3,'Venustiano','126','Ignacio Allende','43584','andy@andy.com','7757565321','Santiago','Hidalgo'),(5,'Independencia','126','Ignacio Allende','43584','andy@andy.com','7757565321','Santiago','Hidalgo'),(6,'Javier Rojo Gomez','102','El paraiso','43915','six@nine.com','775798651','Tulancingo','Hidalgo'),(7,'Benito Juarez','135','Santiago Tulantepec','46321','andy@nine.com','7758536145','Huamuchil','Sinaloa'),(8,'Adolfo Lopez Mateos','46','Peñas','75532','hola@mundo','7757578912','Huamuchil','Oaxaca');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `Usuario_id` int(7) unsigned NOT NULL AUTO_INCREMENT,
  `Sucursal_id` int(10) unsigned NOT NULL,
  `Tipo` varchar(13) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido_Paterno` varchar(20) NOT NULL,
  `Apellido_Materno` varchar(20) NOT NULL,
  `Telefono` varchar(13) NOT NULL,
  `RFC` char(16) NOT NULL,
  `Calle` varchar(25) NOT NULL,
  `Colonia` varchar(25) NOT NULL,
  `Num_Interior` varchar(3) NOT NULL,
  `Num_Exterior` varchar(4) NOT NULL,
  `Codigo_Postal` char(5) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Ciudad` varchar(20) NOT NULL,
  `Estado` varchar(20) NOT NULL,
  `Numero_Seguro` char(11) NOT NULL,
  `CURP` char(18) NOT NULL,
  `Password` varchar(25) NOT NULL,
  PRIMARY KEY (`Usuario_id`),
  UNIQUE KEY `RFC` (`RFC`),
  UNIQUE KEY `Numero_Seguro` (`Numero_Seguro`),
  UNIQUE KEY `CURP` (`CURP`),
  KEY `Sucursal_id` (`Sucursal_id`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`Sucursal_id`) REFERENCES `sucursal` (`sucursal_id`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`Sucursal_id`) REFERENCES `sucursal` (`sucursal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,7,'Vendedor','Armando','Cortez','Servantes','7757578912','MELM830528H0','Pedral','La Cañada','145','128','43625','army@arm','Huamuchil','Hidalgo','123456789','IASE990909HHGSN14','hola_mundo'),(2,5,'Vendedor','Esteban','Islas','Santos','7757578913','IASE990909HHGS05','10 de mayo','La cañada','70','49','43615','steph@steph','Tulancingo','Hidalgo','12345678912','IASE990909HHGSNS04','hola');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-06 17:24:50
