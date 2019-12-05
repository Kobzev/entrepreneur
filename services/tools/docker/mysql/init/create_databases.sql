# create databases
CREATE DATABASE IF NOT EXISTS `db_master`;
CREATE DATABASE IF NOT EXISTS `db_currency`;

CREATE USER 'root'@'%' IDENTIFIED BY 'root';
GRANT ALL ON *.* TO 'root'@'%';

# create root user and grant rights
CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'user'@'%';