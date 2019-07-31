#!/bin/bash
set -e

mysql --protocol=socket -uroot -p$MYSQL_ROOT_PASSWORD <<EOSQL
PURGE BINARY LOGS BEFORE NOW();
PURGE MASTER LOGS BEFORE NOW();
EOSQL