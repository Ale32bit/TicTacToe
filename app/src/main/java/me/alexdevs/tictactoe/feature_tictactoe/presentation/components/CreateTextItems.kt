package me.alexdevs.tictactoe.feature_tictactoe.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import me.alexdevs.tictactoe.ui.theme.White

@Composable
fun CreateTextItems(
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