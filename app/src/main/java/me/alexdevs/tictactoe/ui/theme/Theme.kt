package me.alexdevs.tictactoe.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    secondary = LightBlue,
    tertiary = White,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = LightBlue,
    tertiary = White,

    /*Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TicTacToeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * Composable function to create a styled text with customizable color, font size, and other attributes.
 *
 * @param customText The text to be displayed.
 * @param customColor The color of the text.
 * @param fontSize The font size of the text.
 */
@Composable
fun CreateText(customText: String, customColor: Color, fontSize: TextUnit) {
    Text(
        text = customText,
        style = LocalTextStyle.current.merge(
            TextStyle(
                color = customColor,
                fontSize = fontSize,
                lineHeight = 104.5.sp,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp,
            )
        )
    )

    Text(
        text = customText,
        style = LocalTextStyle.current.merge(
            TextStyle(
                color = White,
                fontSize = fontSize,
                lineHeight = 104.5.sp,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp,
                drawStyle = Stroke(width = 6f, join = StrokeJoin.Miter)
            )
        )
    )
}

/**
 * Composable function to create a customizable button with text.
 *
 * @param customText The text to be displayed on the button.
 * @param fontSize The font size of the text.
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
    yPosition: Dp = 0.dp,
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