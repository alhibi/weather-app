package com.dn0ne.weatherapp.presentation.components.weather

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun Stars(
    modifier: Modifier = Modifier,
    maxStarSize: Float = 5f,
    randomizeSize: Boolean = true,
    starColor: Color = Color.White
) {

    Canvas(modifier = modifier) {
        (0..(size.height.roundToInt() / 10)).forEach { y ->
            (0..(size.width.roundToInt() / 10)).forEach { x ->
                if ((x + y) % Random.nextInt(1, 100) == Random.nextInt(1, 100)) {
                    val starSize = if (randomizeSize) {
                        Random.nextInt(1, maxStarSize.roundToInt()).toFloat()
                    } else maxStarSize

                    drawLine(
                        color = starColor,
                        start = Offset((10 * x).toFloat(), (10 * y).toFloat()),
                        end = Offset((10 * x).toFloat(), (10 * y).toFloat() + starSize),
                        strokeWidth = starSize
                    )
                }
            }
        }
    }
}