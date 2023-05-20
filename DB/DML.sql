use `vote_demo`;
INSERT INTO `vote_demo`.`items`(`id`, `name`) VALUES ( 1, '電腦' );
INSERT INTO `vote_demo`.`items`(`id`, `name`) VALUES ( 2, '滑鼠' );

INSERT INTO `vote_demo`.`users` (`name`, `password`) VALUES ('Leo', '123');
INSERT INTO `vote_demo`.`users` (`name`, `password`) VALUES ('Sandy', '234');
INSERT INTO `vote_demo`.`users` (`name`, `password`) VALUES ('Randy', '345');
INSERT INTO `vote_demo`.`users` (`name`, `password`) VALUES ('RSY', '456');

INSERT INTO `vote_demo`.`records` (`name`, `itemId`) VALUES ('Leo', '1');
INSERT INTO `vote_demo`.`records` (`name`, `itemId`) VALUES ('Sandy', '1');
INSERT INTO `vote_demo`.`records` (`name`, `itemId`) VALUES ('Sandy', '2');
INSERT INTO `vote_demo`.`records` (`name`, `itemId`) VALUES ('Randy', '2');
INSERT INTO `vote_demo`.`records` (`name`, `itemId`) VALUES ('RSY', '2');