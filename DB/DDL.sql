CREATE SCHEMA `vote_demo` ;

use `vote_demo`;
CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `itemId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`itemId`),
  CONSTRAINT `id` FOREIGN KEY (`itemId`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `addItem`(in itemName varchar(45))
BEGIN
	INSERT INTO `items`(`id`, `name`) VALUES ( NULL, itemName );
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `addRecord`(in username varchar(45), in itemId int)
BEGIN
	INSERT INTO `records`  (`name`, `itemId`) VALUES ( username, itemId );
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getItems`()
BEGIN
SELECT * FROM items;
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getItemsVotes`()
BEGIN
	SELECT items.id, items.name, COUNT(*) vote_count FROM items, records WHERE records.itemId = items.id GROUP BY records.itemId;
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserDetail`(in username varchar(45))
BEGIN
	SELECT records.itemId FROM records WHERE records.name = username;
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `login`( in username varchar(45), in userPassword varchar(45))
BEGIN
	SELECT * FROM `users` WHERE users.name = username AND users.password = userPassword;
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeItem`(in itemId int)
BEGIN
	DELETE FROM `items` WHERE items.id = itemId;
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeRecord`(in username varchar(45) , in itemId int)
BEGIN
	DELETE FROM `records` WHERE records.name = username AND records.itemId = itemId;
END//

DELIMITER ;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateItem`(in newName varchar(45), in itemId int)
BEGIN
	UPDATE `items` SET `name` = newName WHERE items.id = itemId;
END//

DELIMITER ;