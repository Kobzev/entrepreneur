# create databases
CREATE DATABASE IF NOT EXISTS `db_master`;
CREATE DATABASE IF NOT EXISTS `db_currency`;

# create root user and grant rights
CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'user'@'%';