package com.sarahisweird.dbcreate.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sarahisweird.dbcreate.AppState

@Composable
private fun databaseInfoGroup(
    appState: AppState
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(200.dp),
        value = appState.databaseName.value,
        onValueChange = {
            appState.databaseName.value = it
            appState.databaseNameInvalid.value = it == ""
        },
        label = { Text("Database name") },
        isError = appState.databaseNameInvalid.value,
        singleLine = true
    )

    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(200.dp),
        value = appState.databaseUser.value,
        onValueChange = {
            appState.databaseUser.value = it
            appState.databaseUserInvalid.value = it == ""
        },
        label = { Text("Database user") },
        isError = appState.databaseUserInvalid.value,
        singleLine = true
    )

    databasePasswordGroup(appState)
}

@Composable
private fun databasePasswordGroup(
    appState: AppState
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(250.dp),
        value = appState.databasePassword.value,
        onValueChange = { appState.databasePassword.value = it },
        label = { Text("Database password") },
        visualTransformation = if (appState.showDatabasePassword.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        singleLine = true
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = appState.showDatabasePassword.value,
            onCheckedChange = { appState.showDatabasePassword.value = it },
            colors = CheckboxDefaults.colors(checkedColor = appState.colors.primary)
        )

        Text("Show password")
    }
}

@Composable
private fun databaseCreationButton(
    appState: AppState
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(10.dp),
        onClick = {
            appState.databaseCreator.createDatabase(
                appState.databaseName.value,
                appState.databaseUser.value,
                appState.databasePassword.value
            )

            appState.databaseCreated.value = true
        },
        colors = if (appState.databaseCreated.value)
            ButtonDefaults.buttonColors(backgroundColor = appState.colors.secondary)
        else
            ButtonDefaults.buttonColors(backgroundColor = appState.colors.primary)
    ) {
        Text("Create database")
    }
}

@Composable
fun databaseInformation(
    appState: AppState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            databaseInfoGroup(appState)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            databaseCreationButton(appState)
        }
    }
}