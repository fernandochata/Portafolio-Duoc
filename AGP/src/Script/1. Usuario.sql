CREATE USER BD_SGP IDENTIFIED BY s_gestion
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";
GRANT "RESOURCE" TO BD_SGP;
GRANT "CONNECT" TO BD_SGP;
