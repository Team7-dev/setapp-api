CREATE DATABASE  IF NOT EXISTS `setapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `setapp`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: setapp
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `area_reserva`
--

DROP TABLE IF EXISTS `area_reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area_reserva` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AREA` varchar(45) NOT NULL,
  `DESCRICAO` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assembleia`
--

DROP TABLE IF EXISTS `assembleia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assembleia` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `MOTIVO` varchar(150) NOT NULL,
  `DATA_HORA_AGENDAMENTO` datetime NOT NULL,
  `DATA_HORA_CONCLUSAO` datetime DEFAULT NULL,
  `SITUACAO` enum('PENDENTE','CONCLUIDA') NOT NULL DEFAULT 'PENDENTE',
  `USUARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ASSEMBLEIA_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FK8y7hd12k4216glo87j15ry6ib` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `fk_ASSEMBLEIA_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boleto` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `ARQUIVO` text NOT NULL,
  `SITUACAO` enum('ATIVO','INATIVO') NOT NULL DEFAULT 'ATIVO',
  `USUARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_BOLETO_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FKt7x0djycde3lawh2tosx9p2c5` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `fk_BOLETO_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `correspondencia`
--

DROP TABLE IF EXISTS `correspondencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `correspondencia` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `DESCRICAO` text NOT NULL,
  `DATA_HORA_RECEBIDA` datetime NOT NULL,
  `DATA_HORA_RETIRADA` datetime DEFAULT NULL,
  `SITUACAO` enum('PENDENTE','RETIRADA') NOT NULL,
  `USUARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_CORRESPONDENCIA_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FKdg5l2pdtn55c0kbohq2h71clo` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `fk_CORRESPONDENCIA_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocorrencia`
--

DROP TABLE IF EXISTS `ocorrencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocorrencia` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `OCORRENCIA` varchar(100) NOT NULL,
  `DESCRICAO` text NOT NULL,
  `DATA_HORA_OCORRENCIA` datetime NOT NULL,
  `DATA_HORA_CONCLUSAO` datetime DEFAULT NULL,
  `SITUACAO` enum('PENDENTE','CONCLUIDO') NOT NULL,
  `USUARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_OCORRENCIA_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FK8vyx5i6two7max2jhlodwew57` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `fk_OCORRENCIA_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERFIL` varchar(50) NOT NULL,
  `DESCRICAO` text,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PERFIL_UNIQUE` (`PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `DATA_HORA_INICIO` datetime NOT NULL,
  `DATA_HORA_FIM` datetime NOT NULL,
  `SITUACAO` enum('ATIVO','CANCELADO','ENCERRADO') NOT NULL DEFAULT 'ATIVO',
  `AREA_RESERVA_ID` int(11) NOT NULL,
  `USUARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_RESERVA_AreaReserva1_idx` (`AREA_RESERVA_ID`),
  KEY `fk_RESERVA_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FKiad9w96t12u3ms2ul93l97mel` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `FKtoq9mllv514c5ah4uiyiy635r` FOREIGN KEY (`AREA_RESERVA_ID`) REFERENCES `area_reserva` (`ID`),
  CONSTRAINT `fk_RESERVA_AreaReserva1` FOREIGN KEY (`AREA_RESERVA_ID`) REFERENCES `area_reserva` (`ID`),
  CONSTRAINT `fk_RESERVA_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidade`
--

DROP TABLE IF EXISTS `unidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `BLOCO` varchar(1) NOT NULL,
  `NUMERO` int(11) NOT NULL,
  `SITUACAO` enum('VAGO','OCUPADO') NOT NULL DEFAULT 'VAGO',
  `USUARIO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_UNIDADE_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FK2xrkx2xf0w2ei8ag2ylqguesv` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `fk_UNIDADE_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `USUARIO` varchar(50) NOT NULL,
  `SENHA` varchar(50) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `SITUACAO` enum('ATIVO','INATIVO') NOT NULL DEFAULT 'ATIVO',
  `PERFIL_ID` int(11) NOT NULL,
  `id_unidade` bigint(20) DEFAULT NULL,
  `unidade_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USUARIO_UNIQUE` (`USUARIO`),
  KEY `fk_USUARIO_PERFIL1_idx` (`PERFIL_ID`),
  CONSTRAINT `FK9po12ytp6krwvwht1kmd0qgxf` FOREIGN KEY (`PERFIL_ID`) REFERENCES `perfil` (`ID`),
  CONSTRAINT `fk_USUARIO_PERFIL1` FOREIGN KEY (`PERFIL_ID`) REFERENCES `perfil` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `PLACA` varchar(8) DEFAULT NULL,
  `SITUACAO` enum('VAGA','OCUPADA') NOT NULL,
  `UNIDADE_ID` int(11) NOT NULL,
  `USUARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIDADE_ID_UNIQUE` (`UNIDADE_ID`),
  UNIQUE KEY `USUARIO_ID_UNIQUE` (`USUARIO_ID`),
  KEY `fk_VEICULO_UNIDADE_idx` (`UNIDADE_ID`),
  KEY `fk_VEICULO_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `FK43c218hduixvcpij7c2p3mjxs` FOREIGN KEY (`UNIDADE_ID`) REFERENCES `unidade` (`ID`),
  CONSTRAINT `FKsie6nqqb4puis3yyxcih5qpr7` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `fk_VEICULO_UNIDADE` FOREIGN KEY (`UNIDADE_ID`) REFERENCES `unidade` (`ID`),
  CONSTRAINT `fk_VEICULO_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `visitante`
--

DROP TABLE IF EXISTS `visitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitante` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_HORA_CADASTRO` datetime NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `SITUACAO` enum('ATIVO','INATIVO') NOT NULL DEFAULT 'ATIVO',
  `UNIDADE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_VISITANTE_UNIDADE1_idx` (`UNIDADE_ID`),
  CONSTRAINT `FKj6aomj4fsoc47umjnqnuu8kpu` FOREIGN KEY (`UNIDADE_ID`) REFERENCES `unidade` (`ID`),
  CONSTRAINT `fk_VISITANTE_UNIDADE1` FOREIGN KEY (`UNIDADE_ID`) REFERENCES `unidade` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-27 18:32:13
