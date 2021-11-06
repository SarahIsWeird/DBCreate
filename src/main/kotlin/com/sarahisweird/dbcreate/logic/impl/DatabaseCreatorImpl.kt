package com.sarahisweird.dbcreate.logic.impl

import com.sarahisweird.dbcreate.DatabaseType
import com.sarahisweird.dbcreate.logic.DatabaseCreator
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseCreatorImpl : DatabaseCreator {
    private var connection: Database? = null

    override fun connect(databaseUrl: String, username: String, password: String, databaseType: DatabaseType) {
        disconnect()

        connection = Database.connect(
            url = if (databaseType == DatabaseType.SQLITE)
                    "jdbc:sqlite:$databaseUrl"
                else
                    "jdbc:${databaseType.protocolId}://$databaseUrl",
            user = username,
            password = password,
            driver = databaseType.driverClasspath
        )

        println("Connected to ${connection?.url}.")
    }

    override fun createDatabase(databaseName: String, databaseUsername: String, databaseUserPassword: String) {
        transaction {
            execInBatch(
                listOf(
                    "CREATE DATABASE `$databaseName`;",
                    "CREATE USER '$databaseUsername' IDENTIFIED BY '$databaseUserPassword';",
                    "GRANT USAGE ON `$databaseName`.* TO '$databaseUsername'@localhost IDENTIFIED BY '$databaseUserPassword';",
                    "GRANT ALL PRIVILEGES ON `$databaseName` .* TO '$databaseUsername'@localhost;",
                    "FLUSH PRIVILEGES;"
                )
            )

            println("Created database.")
        }
    }

    override fun disconnect() {
        connection = null

        println("Disconnected.")
    }
}