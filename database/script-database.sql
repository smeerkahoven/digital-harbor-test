CREATE DATABASE  IF NOT EXISTS `digitalharbor_hospital` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `digitalharbor_hospital`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: digitalharbor_hospital
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `apellido` varchar(32) DEFAULT NULL,
  `direccion` varchar(128) DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_nacimiento` datetime(6) DEFAULT NULL,
  `foto_perfil` longblob,
  `nombre` varchar(32) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `actualizado_por` varchar(16) DEFAULT NULL,
  `creado_por` varchar(16) DEFAULT NULL,
  `hospital_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp7p1jbdrr42v04wlhuafk3m9j` (`actualizado_por`),
  KEY `FKl6bg9ige93vvbsuceax7a9tsf` (`creado_por`),
  KEY `FKds7ws3yyj4c5wj35fpefpeny0` (`hospital_id`),
  CONSTRAINT `FKds7ws3yyj4c5wj35fpefpeny0` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FKl6bg9ige93vvbsuceax7a9tsf` FOREIGN KEY (`creado_por`) REFERENCES `usuario` (`public_id`),
  CONSTRAINT `FKp7p1jbdrr42v04wlhuafk3m9j` FOREIGN KEY (`actualizado_por`) REFERENCES `usuario` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (2,'Coronado','Av. Pirai',NULL,'2020-07-01 12:20:30.128000','1980-10-09 04:00:00.000000',NULL,'Miriam',NULL,NULL,'7DDoIjbP0DBLz93z',2),(4,'Calvimonte','Av. Pirai',NULL,'2020-07-01 22:40:59.410000','1980-10-09 04:00:00.000000',NULL,'Lucas',NULL,NULL,'7DDoIjbP0DBLz93z',2),(6,'Perez','Av. Pirai',NULL,'2020-07-01 22:45:14.356000','1980-10-09 04:00:00.000000',NULL,'Jose',NULL,NULL,'7DDoIjbP0DBLz93z',2),(8,'Arandia','Adfasdf',NULL,'2020-07-02 03:09:26.986000','2020-07-04 04:00:00.000000',NULL,'Luzbelo',NULL,NULL,'7DDoIjbP0DBLz93z',4),(9,'Lanza','Av El Palomar',NULL,'2020-07-02 03:12:06.863000','2012-07-05 04:00:00.000000',NULL,'Alejandro',NULL,NULL,'7DDoIjbP0DBLz93z',4);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_especialidad`
--

DROP TABLE IF EXISTS `doctor_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_especialidad` (
  `doctor_id` int(11) NOT NULL,
  `especialidad_id` int(11) NOT NULL,
  KEY `FK5axxofc3qwy5lskd8eimvnaxn` (`especialidad_id`),
  KEY `FKesyqh6291nh8j3hu4wrof2f7g` (`doctor_id`),
  CONSTRAINT `FK5axxofc3qwy5lskd8eimvnaxn` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidad` (`id`),
  CONSTRAINT `FKesyqh6291nh8j3hu4wrof2f7g` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_especialidad`
--

LOCK TABLES `doctor_especialidad` WRITE;
/*!40000 ALTER TABLE `doctor_especialidad` DISABLE KEYS */;
INSERT INTO `doctor_especialidad` VALUES (2,5),(2,6),(4,5),(4,6),(6,5),(6,6);
/*!40000 ALTER TABLE `doctor_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_id_seq`
--

DROP TABLE IF EXISTS `doctor_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_id_seq`
--

LOCK TABLES `doctor_id_seq` WRITE;
/*!40000 ALTER TABLE `doctor_id_seq` DISABLE KEYS */;
INSERT INTO `doctor_id_seq` VALUES (10);
/*!40000 ALTER TABLE `doctor_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad` (
  `id` int(11) NOT NULL,
  `avatar` longblob,
  `descripcion` varchar(128) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `nombre` varchar(32) NOT NULL,
  `actualizado_por` varchar(16) DEFAULT NULL,
  `creado_por` varchar(16) DEFAULT NULL,
  `hospital_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKduk7668sv7qjgp9vwvyqjdaak` (`actualizado_por`),
  KEY `FK3blsodlomdhujjbwqxf7q61jc` (`creado_por`),
  KEY `FKj777my2p6lmft2okblbb444mf` (`hospital_id`),
  CONSTRAINT `FK3blsodlomdhujjbwqxf7q61jc` FOREIGN KEY (`creado_por`) REFERENCES `usuario` (`public_id`),
  CONSTRAINT `FKduk7668sv7qjgp9vwvyqjdaak` FOREIGN KEY (`actualizado_por`) REFERENCES `usuario` (`public_id`),
  CONSTRAINT `FKj777my2p6lmft2okblbb444mf` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad`
--

LOCK TABLES `especialidad` WRITE;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` VALUES (5,NULL,'Otorrinolaringologia',NULL,'2020-07-01 12:16:02.329000','Otorrinolaringologia',NULL,'7DDoIjbP0DBLz93z',1),(6,NULL,'Medicina General',NULL,'2020-07-01 12:16:03.226000','Medicina General',NULL,'7DDoIjbP0DBLz93z',1),(7,NULL,'Neonatologia',NULL,'2020-07-01 20:44:16.300000','Neonatologia',NULL,'7DDoIjbP0DBLz93z',2),(8,NULL,'asdfsadf',NULL,'2020-07-01 20:46:42.171000','Virologia',NULL,'7DDoIjbP0DBLz93z',1);
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad_doctores`
--

DROP TABLE IF EXISTS `especialidad_doctores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad_doctores` (
  `especialidad_id` int(11) NOT NULL,
  `doctores_id` int(11) NOT NULL,
  PRIMARY KEY (`especialidad_id`,`doctores_id`),
  KEY `FKq5fpnw1phv9odagqytlu3akhf` (`doctores_id`),
  CONSTRAINT `FKmpy4ub78rer19yr3hjpuknxlp` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidad` (`id`),
  CONSTRAINT `FKq5fpnw1phv9odagqytlu3akhf` FOREIGN KEY (`doctores_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad_doctores`
--

LOCK TABLES `especialidad_doctores` WRITE;
/*!40000 ALTER TABLE `especialidad_doctores` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialidad_doctores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad_id_seq`
--

DROP TABLE IF EXISTS `especialidad_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad_id_seq`
--

LOCK TABLES `especialidad_id_seq` WRITE;
/*!40000 ALTER TABLE `especialidad_id_seq` DISABLE KEYS */;
INSERT INTO `especialidad_id_seq` VALUES (9);
/*!40000 ALTER TABLE `especialidad_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `id` int(11) NOT NULL,
  `direccion` varchar(128) DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `nombre` varchar(32) DEFAULT NULL,
  `actualizado_por` varchar(16) DEFAULT NULL,
  `creado_por` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdcyx09nofpxs28egufxsi76d` (`actualizado_por`),
  KEY `FKgxhmk0yhho9iriuidex4u1mna` (`creado_por`),
  CONSTRAINT `FKdcyx09nofpxs28egufxsi76d` FOREIGN KEY (`actualizado_por`) REFERENCES `usuario` (`public_id`),
  CONSTRAINT `FKgxhmk0yhho9iriuidex4u1mna` FOREIGN KEY (`creado_por`) REFERENCES `usuario` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (1,'Av Los Andes',NULL,'2020-06-01 12:14:30.685000','Hospital Incor',NULL,'7DDoIjbP0DBLz93z'),(2,'2do Anillo.',NULL,'2020-07-01 12:14:30.443000','Hospital Un Paso a la Muerte',NULL,'7DDoIjbP0DBLz93z'),(4,'adsfasdfsd',NULL,'2020-05-01 17:55:01.659000','Hospital de la Sangre',NULL,'7DDoIjbP0DBLz93z'),(5,'Av. Susaasa',NULL,'2020-07-01 17:56:51.477000','Hospital del Cerebro',NULL,'7DDoIjbP0DBLz93z'),(7,'fasdfsadf',NULL,'2020-07-01 17:59:20.971000','dsfsdafsad',NULL,'7DDoIjbP0DBLz93z'),(8,'asdf',NULL,'2020-07-01 18:03:39.770000','asdf',NULL,'7DDoIjbP0DBLz93z'),(9,'asdfasdf',NULL,'2020-07-01 18:15:18.780000','Hospital A',NULL,'7DDoIjbP0DBLz93z'),(10,'B. Heroes del Chaco',NULL,'2020-07-01 19:48:50.535000','Mario ',NULL,'7DDoIjbP0DBLz93z');
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital_id_seq`
--

DROP TABLE IF EXISTS `hospital_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital_id_seq`
--

LOCK TABLES `hospital_id_seq` WRITE;
/*!40000 ALTER TABLE `hospital_id_seq` DISABLE KEYS */;
INSERT INTO `hospital_id_seq` VALUES (13);
/*!40000 ALTER TABLE `hospital_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(512) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_atencion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `actualizado_por` varchar(16) DEFAULT NULL,
  `creado_por` varchar(16) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `paciente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdngt15ii0bdyd5rg6tgnxp1tf` (`actualizado_por`),
  KEY `FKtm9owwibvqeoyiuyrxx7jm0g6` (`creado_por`),
  KEY `FKnq11kal1eq3co0qhai54lwvcg` (`doctor_id`),
  KEY `FK4e01y0l5f4lpw0uwjkutwyso0` (`paciente_id`),
  CONSTRAINT `FK4e01y0l5f4lpw0uwjkutwyso0` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`id`),
  CONSTRAINT `FKdngt15ii0bdyd5rg6tgnxp1tf` FOREIGN KEY (`actualizado_por`) REFERENCES `usuario` (`public_id`),
  CONSTRAINT `FKnq11kal1eq3co0qhai54lwvcg` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `FKtm9owwibvqeoyiuyrxx7jm0g6` FOREIGN KEY (`creado_por`) REFERENCES `usuario` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_id_seq`
--

DROP TABLE IF EXISTS `nota_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_id_seq`
--

LOCK TABLES `nota_id_seq` WRITE;
/*!40000 ALTER TABLE `nota_id_seq` DISABLE KEYS */;
INSERT INTO `nota_id_seq` VALUES (1);
/*!40000 ALTER TABLE `nota_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `id` int(11) NOT NULL,
  `apellido` varchar(32) DEFAULT NULL,
  `direccion` varchar(128) DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_nacimiento` datetime(6) DEFAULT NULL,
  `foto_perfil` longblob,
  `nombre` varchar(32) DEFAULT NULL,
  `actualizado_por` varchar(16) DEFAULT NULL,
  `creado_por` varchar(16) DEFAULT NULL,
  `hospital_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr5864bkipfvbn83xgmiip7j65` (`actualizado_por`),
  KEY `FKk73bslrktvovap2bdsihn7jpi` (`creado_por`),
  KEY `FKf8ru5nk12k84w8ob1pod9boj0` (`hospital_id`),
  CONSTRAINT `FKf8ru5nk12k84w8ob1pod9boj0` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FKk73bslrktvovap2bdsihn7jpi` FOREIGN KEY (`creado_por`) REFERENCES `usuario` (`public_id`),
  CONSTRAINT `FKr5864bkipfvbn83xgmiip7j65` FOREIGN KEY (`actualizado_por`) REFERENCES `usuario` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,'Lanza','Av Heroes del Chaco',NULL,'2020-07-01 12:23:13.275000','1984-10-09 04:00:00.000000',NULL,'Jose',NULL,'7DDoIjbP0DBLz93z',1),(2,'Azurduy','Av Heroes del Chaco','2020-07-01 12:28:25.942000','2020-07-01 12:28:25.569000','2010-01-01 04:00:00.000000',NULL,'Marcelo',NULL,'7DDoIjbP0DBLz93z',1),(3,'asdfasdf','asdfasdf',NULL,'2020-07-01 19:57:47.498000','2020-01-07 04:31:00.000000',NULL,'dasdf',NULL,'7DDoIjbP0DBLz93z',2),(4,'Calvimonte','Av. Pirai','2020-07-02 01:39:20.861000','2020-07-01 20:01:15.243000','1980-10-09 04:00:00.000000',NULL,'Alejandro','7DDoIjbP0DBLz93z','7DDoIjbP0DBLz93z',2),(7,'Araujo','asdfasdf',NULL,'2020-07-01 20:08:38.668000','2020-01-07 04:30:00.000000',NULL,'Juan',NULL,'7DDoIjbP0DBLz93z',4);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente_id_seq`
--

DROP TABLE IF EXISTS `paciente_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente_id_seq`
--

LOCK TABLES `paciente_id_seq` WRITE;
/*!40000 ALTER TABLE `paciente_id_seq` DISABLE KEYS */;
INSERT INTO `paciente_id_seq` VALUES (10);
/*!40000 ALTER TABLE `paciente_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `public_id` varchar(16) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `password` varchar(64) NOT NULL,
  `username` varchar(12) NOT NULL,
  PRIMARY KEY (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('7DDoIjbP0DBLz93z','2020-07-01 11:47:25.233000','$2a$10$9BcQoNCu7N5R9eennTLYJOtC/qrIs8GwB6ge9e6LZ5St.7fT6DEGS','josemiguel');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'digitalharbor_hospital'
--

--
-- Dumping routines for database 'digitalharbor_hospital'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-01 23:16:21
