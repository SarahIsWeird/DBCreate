package com.sarahisweird.dbcreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.sarahisweird.dbcreate.composables.connectionBar
import com.sarahisweird.dbcreate.composables.databaseInformation

fun main() = application {
    val appState = initAppState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "DBCreate",
        state = rememberWindowState(
            position = WindowPosition.PlatformDefault,
            width = 900.dp,
            height = 325.dp
        )
    ) {
        MaterialTheme(
            colors = appState.colors
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    connectionBar(appState)

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 2.dp,
                                vertical = 10.dp
                            ),
                        color = appState.colors.primary
                    )

                    databaseInformation(appState)
                }
            }
        }
    }
}