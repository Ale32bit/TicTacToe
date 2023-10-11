package me.alexdevs.tictactoe.core.presentation.components

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {},
        floatingActionButton = {},
        modifier = modifier,
    ) {
        content()
    }
}