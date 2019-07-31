#!/bin/bash
set -e

mysql --protocol=socket -uroot -p$MYSQL_ROOT_PASSWORD <<EOSQL
CREATE USER titanic IDENTIFIED BY 'Pass1234';

# Initiates database `titanic` and grant access to `titanic` user
CREATE DATABASE titanic CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, CREATE VIEW, SHOW VIEW ON titanic.* TO 'titanic'@'%' WITH GRANT OPTION;

EOSQL