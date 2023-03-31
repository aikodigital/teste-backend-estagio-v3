FROM postgres
EXPOSE 5432

COPY data.sql /docker-entrypoint-initdb.d/
COPY data.backup /docker-entrypoint-initdb.d/

CMD ["postgres", "psql -U postgres -c CREATE DATABASE test;", "CREATE SCHEMA operation; SET SCHEMA 'operation';"]
