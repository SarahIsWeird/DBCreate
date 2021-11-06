package com.sarahisweird.dbcreate

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.sarahisweird.dbcreate.logic.DatabaseCreator

@Composable
fun initAppState() = AppState(
        colors = darkColors(),
        databaseCreator = DatabaseCreator.new(),
        dbAddress = remember { mutableStateOf("localhost") },
        dbAddressInvalid = remember { mutableStateOf(false) },
        dbPort = remember { mutableStateOf("3306") },
        dbPortInvalid = remember { mutableStateOf(false) },
        rootUser = remember { mutableStateOf("root") },
        rootUserInvalid = remember { mutableStateOf(false) },
        rootPassword = remember { mutableStateOf("") },
        databaseType = remember { mutableStateOf(DatabaseType.MYSQL) },
        databaseName = remember { mutableStateOf("") },
        databaseNameInvalid = remember { mutableStateOf(false) },
        databaseUser = remember { mutableStateOf("") },
        databaseUserInvalid = remember { mutableStateOf(false) },
        databasePassword = remember { mutableStateOf("") },
        showDatabasePassword = remember { mutableStateOf(false) },
        databaseCreated = remember { mutableStateOf(false) }
    )

data class AppState(
    val colors: Colors,
    val databaseCreator: DatabaseCreator,
    val dbAddress: MutableState<String>,
    val dbAddressInvalid: MutableState<Boolean>,
    val dbPort: MutableState<String>,
    val dbPortInvalid: MutableState<Boolean>,
    val rootUser: MutableState<String>,
    val rootUserInvalid: MutableState<Boolean>,
    val rootPassword: MutableState<String>,
    val databaseType: MutableState<DatabaseType>,
    val databaseName: MutableState<String>,
    val databaseNameInvalid: MutableState<Boolean>,
    val databaseUser: MutableState<String>,
    val databaseUserInvalid: MutableState<Boolean>,
    val databasePassword: MutableState<String>,
    val showDatabasePassword: MutableState<Boolean>,
    val databaseCreated: MutableState<Boolean>
)