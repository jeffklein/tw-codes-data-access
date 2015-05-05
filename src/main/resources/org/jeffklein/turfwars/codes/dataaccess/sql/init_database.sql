SET PASSWORD FOR 'root'@'localhost' = PASSWORD('killMeN0w##');
SET PASSWORD FOR 'root'@'127.0.0.1' = PASSWORD('killMeN0w##');
SET PASSWORD FOR 'root'@'::11' = PASSWORD('killMeN0w##');

CREATE DATABASE IF NOT EXISTS ${db.name};

CREATE USER 'twcodes'@'localhost' IDENTIFIED BY 'K1lg0r3';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON ${db.name}.* TO 'twcodes'@'localhost';

SET @@global.time_zone='+00:00';
