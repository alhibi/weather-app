package com.dn0ne.weatherapp.presentation.components.weather

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.cos
import kotlin.random.Random

@Composable
fun Snow(
    modifier: Modifier = Modifier,
    durationMillis: Int = 60000,
    snowColor: Color = Color.White,
    flakesCount: Int = 500,
    flakeSize: Float = 5f,
    rotationAngle: Float = 0f,
    shakeRadius: Float = 10f,
    shakeDurationMillis: Int = 1000
) {

    val animateX by rememberInfiniteTransition(label = "SnowShakeAnimation").animateFloat(
        initialValue = -shakeRadius,
        targetValue = shakeRadius,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = shakeDurationMillis, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val animateY by rememberInfiniteTransition(label = "SnowFallAnimation").animateFloat(
        initialValue = 0f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing)
        ),
        label = ""
    )

    val offsets = remember {
        mutableStateListOf<Float>().apply {
            repeat(flakesCount) {
                add(cos(it.toFloat()) + Random.nextDouble(-0.3, 0.3).toFloat())
            }
        }
    }

    Canvas(modifier = modifier) {
        for (i in offsets.indices) {
            val x = -size.width / 2 + i.toFloat() / flakesCount.toFloat() * size.width * 2f + animateX * offsets[i]
            val y = (animateY + offsets[i]) * size.height * 2
            val y2 = (animateY - 1f + offsets[i]) * size.height * 2

            rotate(
                degrees = rotationAngle
            ) {
                drawLine(
                    color = snowColor,
                    start = Offset(x = x, y = y),
                    end = Offset(x = x, y = y + flakeSize),
                    strokeWidth = flakeSize
                )

                drawLine(
                    color = snowColor,
                    start = Offset(x = x, y = y2),
                    end = Offset(x = x, y = y2 + flakeSize),
                    strokeWidth = flakeSize
                )
            }
        }
    }
}