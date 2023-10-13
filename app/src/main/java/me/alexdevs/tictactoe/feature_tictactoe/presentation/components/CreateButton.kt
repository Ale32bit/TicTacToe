package me.alexdevs.tictactoe.feature_tictactoe.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.alexdevs.tictactoe.ui.theme.ButtonBlueBorder

/**
 * Composable function to create a customizable button with text.
 *
 * @param customText The text to be displayed on the button.
 * @param fontSize The font size of the text (sp).
 * @param onClick The lambda to be executed when the button is clicked.
 * @param buttonShape The shape of the button.
 * @param borderStroke The border stroke of the button.
 * @param buttonColor The background color of the button.
 * @param xPosition The horizontal offset of the button.
 * @param yPosition The vertical offset of the button.
 */
@Composable
fun CreateButton(
    customText: String,
    fontSize: TextUnit,
    onClick: () -> Unit,
    buttonShape: Shape = RoundedCornerShape(25),
    borderStroke: BorderStroke = BorderStroke(3.dp, ButtonBlueBorder),
    buttonColor: Color = Color.White,
    xPosition: Dp = 0.dp,
    yPosition: Dp = 0.dp
) {
    Button(
        onClick = onClick,
        modifier = Modifier.offset(x = xPosition, y = yPosition),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        border = borderStroke,
        shape = buttonShape,
    )
    {
        Text(
            text = customText,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = Color.DarkGray,
                    fontSize = fontSize,
                    lineHeight = 104.5.sp,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                )
            )
        )
    }
}

/**
 * Composable function to create a back button with specified properties.
 *
 * @param fontSize The font size of the button text.
 * @param onClick The lambda function to be executed on button click.
 * @param xPosition The x-axis position of the button.
 * @param yPosition The y-axis position of the button.
 */
@Composable
fun CreateBackButton(
    fontSize: TextUnit = 20.sp,
    onClick: () -> Unit,
    xPosition: Dp = 15.dp,
    yPosition: Dp = 15.dp
) {
    CreateButton(
        customText = "<",
        fontSize = fontSize,
        onClick = onClick,
        buttonShape = RoundedCornerShape(25),
        borderStroke = BorderStroke(3.dp, ButtonBlueBorder),
        buttonColor = Color.White,
        xPosition = xPosition,
        yPosition = yPosition,
    )
}

/**
 * Composable function to create a boxed back button with specified properties.
 *
 * @param fontSize The font size of the button text.
 * @param onClick The lambda function to be executed on button click.
 * @param xPosition The x-axis position of the button.
 * @param yPosition The y-axis position of the button.
 */
@Composable
fun CreateBoxedBackButton(
    fontSize: TextUnit = 20.sp,
    onClick: () -> Unit,
    xPosition: Dp = 15.dp,
    yPosition: Dp = 15.dp,
    alignment: Alignment = Alignment.TopStart,
) {
    Box(
        contentAlignment = alignment,
    ) {
        CreateBackButton(
            fontSize = fontSize,
            onClick = onClick,
            xPosition = xPosition,
            yPosition = yPosition
        )
    }
}