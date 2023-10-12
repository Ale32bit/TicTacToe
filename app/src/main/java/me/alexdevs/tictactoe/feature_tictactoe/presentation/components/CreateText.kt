package me.alexdevs.tictactoe.feature_tictactoe.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * Composable function to create a styled text with customizable color, font size, and other attributes.
 *
 * @param customText The text to be displayed.
 * @param customColor The color of the text.
 * @param fontSize The font size of the text.
 */
@Composable
fun CreateText(
    customText: String,
    customColor: Color,
    fontSize: TextUnit,
    alignment: Alignment? = null,
    padding: PaddingValues? = null
) {
    val currentPadding: PaddingValues = padding ?: PaddingValues(0.dp, 0.dp);

    if (alignment == null) {
        CreateTextItems(customText = customText, customColor = customColor, fontSize = fontSize, currentPadding)
        return;
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = alignment,
    ) {
        CreateTextItems(customText = customText, customColor = customColor, fontSize = fontSize, currentPadding)
    }
}