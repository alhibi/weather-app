package com.dn0ne.weatherapp.presentation.components.weathercard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.dn0ne.weatherapp.presentation.Point
import kotlin.math.roundToInt

@Composable
fun TemperatureGraph(
    temperatures: List<Float>,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
    graphDrawStyle: DrawStyle = Stroke(width = 5f),
    graphColors: List<Color> = listOf(Color.Black),
    graphPointsOuterRadius: Float = 20f,
    graphPointsOuterColor: Color = Color.Black,
    graphPointsInnerRadius: Float = 15f,
    graphPointsInnerColor: Color = Color.Black,
    textStyle: TextStyle = LocalTextStyle.current,
    temperatureTextStyle: TextStyle = LocalTextStyle.current
) {
    val min = temperatures.min()
    val max = temperatures.max() - min

    val textMeasurer = rememberTextMeasurer()
    val textsToDraw = listOf(
        "Morning",
        "Afternoon",
        "Evening",
        "Night"
    )

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {


            val graphOverdraw = 200f
            val xDiff = (size.width - graphOverdraw) / (temperatures.size - 1)

            val points = mutableListOf<Point>()
            val conPoints1 = mutableListOf<Point>()
            val conPoints2 = mutableListOf<Point>()

            val verticalPadding = 40f
            val graphHeight = size.height / 3f

            for (i in temperatures.indices) {
                val y = graphHeight - ((temperatures[i] - min) / max * graphHeight) + verticalPadding

                points.add(
                    Point(
                        x = graphOverdraw / 2 + xDiff * i,
                        y = y
                    )
                )
            }

            for (i in 1..temperatures.lastIndex) {
                conPoints1.add(
                    Point(
                        x = (points[i].x + points[i - 1].x) / 2,
                        y = points[i - 1].y
                    )
                )

                conPoints2.add(
                    Point(
                        x = (points[i].x + points[i - 1].x) / 2,
                        y = points[i].y
                    )
                )
            }

            val path = Path().apply {
                moveTo(x = 0f, y = points.first().y)
                lineTo(points.first().x, points.first().y)

                for (i in 1..points.lastIndex) {
                    cubicTo(
                        x1 = conPoints1[i - 1].x,
                        y1 = conPoints1[i - 1].y,
                        x2 = conPoints2[i - 1].x,
                        y2 = conPoints2[i - 1].y,
                        x3 = points[i].x,
                        y3 = points[i].y
                    )
                }

                lineTo(size.width, points.last().y)
            }


            drawPath(
                path = path,
                style = graphDrawStyle,
                brush = Brush.horizontalGradient(
                    colors = graphColors,
                    endX = size.width
                )
            )

            for (point in points) {
                drawLine(
                    color = Color.White.copy(alpha = 0.3f),
                    start = Offset(
                        x = point.x,
                        y = 0f
                    ),
                    end = Offset(
                        x = point.x,
                        y = size.height
                    )
                )

                drawCircle(
                    color = graphPointsOuterColor,
                    radius = graphPointsOuterRadius,
                    center = Offset(point.x, point.y)
                )

                drawCircle(
                    color = graphPointsInnerColor,
                    radius = graphPointsInnerRadius,
                    center = Offset(point.x, point.y)
                )
            }

            for (i in points.indices) {
                val textLayoutResult = textMeasurer.measure(
                    text = textsToDraw[i],
                    style = textStyle
                )
                drawText(
                    textLayoutResult = textLayoutResult,
                    color = contentColor,
                    topLeft = Offset(
                        x = points[i].x - textLayoutResult.size.width / 2,
                        y = size.height / 2 + textLayoutResult.size.height / 1.7f
                    )
                )


                val temperatureLayoutResult = textMeasurer.measure(
                    text = "${temperatures[i].roundToInt()}°",
                    style = temperatureTextStyle
                )
                drawText(
                    textLayoutResult = temperatureLayoutResult,
                    color = contentColor,
                    topLeft = Offset(
                        x = points[i].x - temperatureLayoutResult.size.width / 2,
                        y = size.height - verticalPadding - temperatureLayoutResult.size.height / 1.7f
                    )
                )
            }
        }
    }
}