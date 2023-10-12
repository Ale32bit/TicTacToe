package me.alexdevs.tictactoe.core.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
    bottomBar: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            // Add top bar if needed
        },
        bottomBar = bottomBar,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                content(Modifier.weight(1f))
            }
        }
    )
}