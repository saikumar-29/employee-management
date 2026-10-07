# Custom MySQL database image
FROM mysql:8.0

ENV MYSQL_DATABASE=employeedb
ENV MYSQL_USER=employee
ENV MYSQL_PASSWORD=employee123
ENV MYSQL_ROOT_PASSWORD=root123

COPY init.sql /docker-entrypoint-initdb.d/init.sql
