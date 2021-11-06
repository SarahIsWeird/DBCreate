package com.sarahisweird.dbcreate.composables

import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sarahisweird.dbcreate.AppState
import com.sarahisweird.dbcreate.DatabaseType
import com.sarahisweird.dbcreate.joinToStringWithAnd

@Composable
private fun databaseInfo(
    appState: AppState
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(200.dp),
        value = appState.dbAddress.value,
        onValueChange = {
            appState.dbAddress.value = it
            appState.dbAddressInvalid.value = it == ""
        },
        label = { Text("Database address") },
        isError = appState.dbAddressInvalid.value,
        singleLine = true
    )

    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(150.dp),
        value = appState.dbPort.value,
        onValueChange = {
            appState.dbPort.value = it
            val intVal = it.toIntOrNull()
            appState.dbPortInvalid.value = intVal == null || intVal <= 0 || intVal > 65535
        },
        label = { Text("Database port") },
        isError = appState.dbPortInvalid.value,
        singleLine = true
    )
}

@Composable
private fun rootUserInfo(
    appState: AppState
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(180.dp),
        value = appState.rootUser.value,
        onValueChange = {
            appState.rootUser.value = it
            appState.rootUserInvalid.value = it == ""
        },
        label = { Text("Root username") },
        isError = appState.rootUserInvalid.value,
        singleLine = true
    )

    OutlinedTextField(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(200.dp),
        value = appState.rootPassword.value,
        onValueChange = { appState.rootPassword.value = it },
        label = { Text("Root password") },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true
    )
}

@Composable
private fun loginButton(
    appState: AppState
) {
    TooltipArea(
        modifier = Modifier.fillMaxSize(),
        tooltip = {
            val invalidFields = mutableListOf<String>()

            if (appState.dbAddressInvalid.value) invalidFields += "database address"
            if (appState.dbPortInvalid.value) invalidFields += "database port"
            if (appState.rootUserInvalid.value) invalidFields += "root username"

            if (invalidFields.isEmpty()) return@TooltipArea

            Surface(
                modifier = Modifier.shadow(4.dp),
                color = appState.colors.error,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Invalid ${invalidFields.joinToStringWithAnd()} " +
                            "value${if (invalidFields.count() > 1) "s" else ""}!",
                    color = Color.White
                )
            }
        },
        delayMillis = 200
    ) {
        Button(
            modifier = Modifier.fillMaxSize(),
            onClick = {
                appState.databaseCreator.connect(
                    appState.dbAddress.value,
                    appState.rootUser.value,
                    appState.rootPassword.value,
                    appState.databaseType.value
                )
            },
            enabled = !appState.dbAddressInvalid.value
                    && !appState.dbPortInvalid.value
                    && !appState.rootUserInvalid.value
        ) {
            Text("Login")
        }
    }
}

@Composable
fun databaseTypeSelection(
    appState: AppState
) {
    for (type in DatabaseType.values()) {
        Row(
            modifier = Modifier.clickable {
                appState.databaseType.value = type
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = appState.databaseType.value == type,
                onClick = { appState.databaseType.value = type },
                colors = RadioButtonDefaults.colors(selectedColor = appState.colors.primary)
            )

            Text(type.text)
        }
    }
}

@Composable
fun connectionBar(
    appState: AppState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                databaseInfo(appState)
                rootUserInfo(appState)
            }

            Row(
                modifier = Modifier.padding(top = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                databaseTypeSelection(appState)
            }
        }

        Box(
            modifier = Modifier.height(75.dp)
        ) {
            loginButton(appState)
        }
    }
}