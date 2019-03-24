delete from `legoDB`.`valid_brick_types`;

INSERT INTO `legoDB`.`valid_brick_types` (`width`, `length`) VALUES ('2', '4');
INSERT INTO `legoDB`.`valid_brick_types` (`width`, `length`) VALUES ('2', '2');
INSERT INTO `legoDB`.`valid_brick_types` (`width`, `length`) VALUES ('1', '2');

delete from `legoDB`.`users` where `username`='admin';
INSERT INTO `legoDB`.`users` (username, `password`, email, `admin`) VALUES ('admin', '66654321', 'admin@lego.dk', 1);