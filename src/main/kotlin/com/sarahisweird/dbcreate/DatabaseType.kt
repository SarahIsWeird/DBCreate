package com.sarahisweird.dbcreate

enum class DatabaseType(
    val text: String,
    val driverClasspath: String,
    val protocolId: String
) {
    MYSQL("MySQL", "com.mysql.jdbc.Driver", "mysql"),
    POSTGRESQL("PostgreSQL", "org.postgresql.Driver", "postgresql"),
    SQLITE("SQLite", "org.sqlite.JDBC", "sqlite"),
    MSSQL("Microsoft SQL Server", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "sqlserver")
}