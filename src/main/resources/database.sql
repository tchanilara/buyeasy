DROP TABLE IF EXISTS `customers`;
--
-- Table structure for table `customers`
--
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) COLLATE utf8mb3_bin NOT NULL,
  `lastname` varchar(45) COLLATE utf8mb3_bin NOT NULL,
  `phone` varchar(45) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` VALUES (1,'Larissa', 'Tchani','222-222-22222'),(2,'Dann', 'Tchani',''),(3,'Nathan', 'Njienang','');



