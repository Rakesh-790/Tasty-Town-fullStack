-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: tasty-town
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('9676137f-b54c-41d7-b518-daae1e883d1f','002ca978-ccda-4d3d-a724-8605bb31d12a'),('ee3d1c89-3c85-4063-8c9f-99e4a418d7fa','17bba44f-f7b3-4f15-b5b6-09c47f6103f7'),('2773c97b-e11c-433e-8ab9-da3ae821afca','48d4eb8e-9462-4ebc-aff5-e37367fb6f62'),('2f7ba5ab-f3ed-4fc1-ac9a-2c65c56faef5','65b6f8f3-2d41-4f8c-972d-d20b2f51c53d'),('6bb87c57-34d0-4553-8ccc-e0938d38c1f1','915e40d8-e533-480f-b1a7-cfd054d27339'),('00bb0716-2c9d-43f1-a3ed-ba72f7c92fa4','f089788c-bb01-46c9-b590-5c584c62f4ee');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `cart_item_id` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `cart_id` varchar(255) NOT NULL,
  `food_id` varchar(255) NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKcro8349ry4i72h81en8iw202g` (`food_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKcro8349ry4i72h81en8iw202g` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES ('6ca0034f-fed6-40dd-8e61-1a54642acb2c',3,'00bb0716-2c9d-43f1-a3ed-ba72f7c92fa4','904abb15-7db3-407b-9822-db8dee9d3286'),('9d5d309f-48a3-4da9-a12b-5540cbe34381',2,'00bb0716-2c9d-43f1-a3ed-ba72f7c92fa4','9b1a61d0-2c3f-43ca-82c2-e57b769f0d20'),('b1092f85-ec40-4f5a-abac-735ec89999dd',3,'2f7ba5ab-f3ed-4fc1-ac9a-2c65c56faef5','904abb15-7db3-407b-9822-db8dee9d3286');
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catagory`
--

DROP TABLE IF EXISTS `catagory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catagory` (
  `catagory_id` varchar(255) NOT NULL,
  `catagory_name` varchar(255) NOT NULL,
  PRIMARY KEY (`catagory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catagory`
--

LOCK TABLES `catagory` WRITE;
/*!40000 ALTER TABLE `catagory` DISABLE KEYS */;
INSERT INTO `catagory` VALUES ('ac976524-9a8f-409e-a9cf-4ceb7843c05e','Biryani'),('b4dfae8f-1042-42aa-acda-d9e380cb3a6f','bread'),('c3bf70ec-4d53-4ee6-bd41-030cfa3ae9eb','Desserts'),('f87aa6dd-a1d1-458c-b857-1eadb8d7e166','Paneer Item');
/*!40000 ALTER TABLE `catagory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `food_id` varchar(255) NOT NULL,
  `food_description` varchar(255) DEFAULT NULL,
  `food_image` varchar(255) DEFAULT NULL,
  `food_name` varchar(255) DEFAULT NULL,
  `food_price` double NOT NULL,
  `catagory_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  KEY `FK3ol8trqebw9b9dmwpbfc9ba0v` (`catagory_id`),
  CONSTRAINT `FK3ol8trqebw9b9dmwpbfc9ba0v` FOREIGN KEY (`catagory_id`) REFERENCES `catagory` (`catagory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES ('0c54a6fa-38e0-43bc-a6ce-b973e5032d00','Chilli Paneer is a popular Indo-Chinese appetizer featuring crispy fried paneer (Indian cottage cheese) tossed in a spicy, tangy chilli sauce','47e52b9c-c090-43bc-8b97-98c9e0182b00.jpg','Chilli Paneer',190.99,'f87aa6dd-a1d1-458c-b857-1eadb8d7e166'),('4bac7c7b-107f-49d6-8e95-0c8203d27c4f','Roti is an unleavened, South Asian flatbread made primarily from whole wheat flour, water, and salt, often cooked on a flat griddle','74844711-9638-4973-9fe5-0a907404d6d0.png','plain roti',3,'b4dfae8f-1042-42aa-acda-d9e380cb3a6f'),('719c7966-0214-46ca-bb12-1d7a1dd661ed','It is a wide variety of treats centered around frozen dairy or non-dairy bases, often combined with various flavors, textures, and toppings','21e759b7-0164-4335-b178-40823fb75d20.jpg','Ice-Cream',100.99,'c3bf70ec-4d53-4ee6-bd41-030cfa3ae9eb'),('8783eb17-d86c-49dc-97e7-9f8f3c6de279','it is a rich and flavorful dish originating from Hyderabad','321b15f1-2e1e-45a2-9e9d-1929f3f1ca53.jpg','Hyderabadi Chicken Briyani',140.99,'ac976524-9a8f-409e-a9cf-4ceb7843c05e'),('904abb15-7db3-407b-9822-db8dee9d3286','butter paneer is a rich & creamy curry made with paneer, spices, onions, tomatoes, cashews and butter','fd1d2b73-4e5d-481a-8e1a-1eb0f3529b3b.jpg','Panner Butter Masala',220.99,'f87aa6dd-a1d1-458c-b857-1eadb8d7e166'),('9b1a61d0-2c3f-43ca-82c2-e57b769f0d20','it is traditionally a dish where meat and patatos are cooked in clarified butter','73158c35-0172-4c47-82bf-84acf56bf566.jpg','Kolkata Chicken Briyani',150.99,'ac976524-9a8f-409e-a9cf-4ceb7843c05e'),('ce332fe9-2822-42c9-a62d-77373cd63590','it made by cooking rice along with masalas, vegetables, mint, coriander leaves and spices','03e70e0a-d01a-4f86-b74a-ef2e6c301914.jpg','Veg hyderabadi Briyani',125.3,'ac976524-9a8f-409e-a9cf-4ceb7843c05e'),('eda3c6b6-91a7-4304-9c23-95b497f53311','A chocolate brownie, or simply a brownie, is a chocolate baked dessert bar','1683d952-662f-4f28-8033-4eea337a7d85.jpg','Brownies',90.99,'c3bf70ec-4d53-4ee6-bd41-030cfa3ae9eb'),('f6e594c4-4a83-4363-a6fa-8bb1412cd976','Paneer tikka is a popular Indian appetizer featuring cubes of paneer (Indian cheese) marinated in a spiced yogurt mixture and then grilled or baked','a9c2fd5f-b4e8-42c7-99f3-cb466c18e831.jpg','Panner Tikka',170.99,'f87aa6dd-a1d1-458c-b857-1eadb8d7e166'),('f931c25c-a41a-41f1-a99a-0819a6f12aa8','Custard is a sweet, creamy dessert or sauce typically made from milk, eggs, sugar, and flavorings like vanilla','485b7d97-f443-445b-8bf4-0d40a8eef4b8.jpg','Custard',130.99,'c3bf70ec-4d53-4ee6-bd41-030cfa3ae9eb');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` varchar(255) NOT NULL,
  `food_name` varchar(255) DEFAULT NULL,
  `food_price` double NOT NULL,
  `quantity` int NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES ('3f602578-8206-4fda-a4ab-00e7e90c4f01','Ice-Cream',100.99,5,'c625a55f-5bda-449b-985e-88b61f2b347b'),('637fd62c-9806-485b-a628-30ec6d93a92e','Hyderabadi Chicken Briyani',140.99,2,'3b0b8ebe-691d-4f69-83bc-83383b531062');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` varchar(255) NOT NULL,
  `address_info` varchar(255) DEFAULT NULL,
  `contact_info` varchar(255) DEFAULT NULL,
  `order_code` varchar(255) NOT NULL,
  `order_date_time` datetime(6) DEFAULT NULL,
  `order_status` enum('DELIVERED','FOOD_PREPARING','OUT_FOR_DELIVERY') DEFAULT NULL,
  `total_amount` double NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `UKdhk2umg8ijjkg4njg6891trit` (`order_code`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('3b0b8ebe-691d-4f69-83bc-83383b531062','Badagada brit conlony\nEb-441, , Odisha, 751018','Rakesh Roshan Behera, brakeshroshan098@gmail.com, 09348207168','ORD-ZYQ1364','2025-11-20 13:47:11.361639','FOOD_PREPARING',359.178,'16bdb65e-cbd8-449d-b1b3-848853c7b704'),('c625a55f-5bda-449b-985e-88b61f2b347b','Badagada brit conlony\nEb-441, , Odisha, 751018','Rakesh Roshan Behera, test@gmail.com, 09348207168','ORD-HIZ1488','2025-11-20 13:52:17.743065','OUT_FOR_DELIVERY',604.445,'17bba44f-f7b3-4f15-b5b6-09c47f6103f7');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_USER') NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKj09k2v8lxofv2vecxu2hde9so` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('002ca978-ccda-4d3d-a724-8605bb31d12a','ROLE_ADMIN','admin@gmail.com','$2a$10$rSLVvB1CPfJYZ6z.xoot2.cUYXgUYcEiDj6olWO2zYniqfkVCJ5dC'),('16bdb65e-cbd8-449d-b1b3-848853c7b704','ROLE_USER','rocky@gmail.com','$2a$10$8FCJ1ycc5bcEvhQJJokNounRe4nn5jR1fomYzR3P2wDAXdZ1y/56.'),('17bba44f-f7b3-4f15-b5b6-09c47f6103f7','ROLE_USER','test@gmail.com','$2a$10$cOf5AiQlZk1Nxwe0R70jB.NTDomx/kNdxIOEnYCpZhRYt2/jtrX2K'),('3abaef27-b469-46ed-b06a-d6e1deb836f9','ROLE_USER','test3@gmail.com','$2a$10$/se5HPnvC0wp.AXua2nFKOvnjsYobC702zZFCklYOmRuHvYnOrALS'),('48d4eb8e-9462-4ebc-aff5-e37367fb6f62','ROLE_USER','rock@gmail.com','$2a$10$lGhWlKG5DS04LnZZkyAucuxM8kjjy1qbgcqE.ZGKZCHfhUEMhmLP2'),('65b6f8f3-2d41-4f8c-972d-d20b2f51c53d','ROLE_USER','rock1@gmail.com','$2a$10$hZLRn0P33zhj5GEUQVRc0.VtdflfllqcB01km21VTZc6Zbkx7M.fq'),('915e40d8-e533-480f-b1a7-cfd054d27339','ROLE_USER','rock4@gmail.com','$2a$10$Av46l8boK7lKPyjO3hKkC.pDENwfCW7URgyn7SYQ0UYejmMqy5FQ6'),('e5df92e6-70cf-4dd8-b9ef-3dfe915fd089','ROLE_USER','test1@gmail.com','$2a$10$fYhoheBduMgEyMdfHZhxoOQ8NOTduHjeHWFsliLG88E13sec4VIBW'),('f089788c-bb01-46c9-b590-5c584c62f4ee','ROLE_USER','rakeshroshan123@gmail.com','$2a$10$4lIGLVYvVc2zlERi3LFD1u0h2ByauTK05SmJxSXxlHcUaFYnU.3WK');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-22 11:23:52
