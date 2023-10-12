package me.alexdevs.tictactoe.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
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
import kotlin.random.Random

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    secondary = LightBlue,
    tertiary = White,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = LightBlue,
    tertiary = White,
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

@Composable
private fun CreateTextItems(
    customText: String,
    customColor: Color,
    fontSize: TextUnit,
    padding: PaddingValues
) {
    Text(
        text = customText,
        modifier = Modifier.padding(padding),
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
        modifier = Modifier.padding(padding),
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

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun TableScreen(padding: PaddingValues? = null) {
    val currentPadding = padding ?: PaddingValues(0.dp, 0.dp);

    // Just a fake data... a Pair of Int and String
    val tableData = (1..3).mapIndexed { index, item ->
        index to "Item $index"
    }
    // Each cell of a column must have the same weight.
    val column1Weight = .25f // 25%
    val column2Weight = .25f // 25%
    val column3Weight = .50f; // 50%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(Modifier.fillMaxSize()
        .padding(currentPadding)) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "2023/10/10", weight = column1Weight)
            }
        }
        // Here are all the lines of your table.
        items(tableData) {
            val (id, text) = it
            Row(Modifier.fillMaxWidth()
                .background(White)) {
                TableCell(text = id.toString(), weight = column1Weight)
                TableCell(text = text, weight = column2Weight)
                TableCell(text = Random.nextInt().toString(), weight = column3Weight)
            }
        }
    }
}