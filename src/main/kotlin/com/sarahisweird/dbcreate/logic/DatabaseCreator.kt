package com.sarahisweird.dbcreate.logic

import com.sarahisweird.dbcreate.DatabaseType
import com.sarahisweird.dbcreate.logic.impl.DatabaseCreatorImpl

interface DatabaseCreator {
    companion object {
        fun new() = DatabaseCreatorImpl()
    }

    fun connect(databaseUrl: String, username: String, password: String, databaseType: DatabaseType)
    fun createDatabase(databaseName: String, databaseUsername: String, databaseUserPassword: String)
    fun disconnect()
}