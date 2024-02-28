package com.dn0ne.weatherapp.presentation.components.weathercard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureGraphDisplay(
    temperatures: List<Float>,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
    graphColor: Color = Color.Black,
    graphPointsOuterColor: Color = Color.White,
    graphPointsInnerColor: Color = Color.White,
    textStyle: TextStyle = LocalTextStyle.current,
    temperatureTextStyle: TextStyle = LocalTextStyle.current
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = .2f))
    ) {
        Text(
            text = "Temperature",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = contentColor,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        TemperatureGraph(
            temperatures = temperatures,
            contentColor = contentColor,
            graphColors = listOf(
                graphColor.copy(alpha = .1f),
                graphColor,
                graphColor.copy(alpha = .1f),
            ),
            graphDrawStyle = Stroke(width = 3f),
            graphPointsOuterRadius = 15f,
            graphPointsOuterColor = graphPointsOuterColor,
            graphPointsInnerRadius = 10f,
            graphPointsInnerColor = graphPointsInnerColor,
            textStyle = textStyle,
            temperatureTextStyle = temperatureTextStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(130.dp)
        )
    }
}