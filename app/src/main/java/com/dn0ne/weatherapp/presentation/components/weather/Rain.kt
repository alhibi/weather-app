package com.dn0ne.weatherapp.presentation.components.weather

import androidx.compose.animation.core.LinearEasing
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
fun Rain(
    modifier: Modifier = Modifier,
    durationMillis: Int = 5000,
    dropColor: Color = Color.White,
    dropsCount: Int = 500,
    dropLength: Float = 50f,
    dropWidth: Float = 3f,
    rotationAngle: Float = 10f
) {
    val animateY by rememberInfiniteTransition(label = "RainFallAnimation").animateFloat(
        initialValue = 0f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing)
        ),
        label = ""
    )

    val offsets = remember {
        mutableStateListOf<Float>().apply {
            repeat(dropsCount) {
                add(cos(it.toFloat()) + Random.nextDouble(-0.3, 0.3).toFloat())
            }
        }
    }

    Canvas(modifier = modifier) {
        for (i in offsets.indices) {
            val x = -size.width / 2 + i.toFloat() / dropsCount.toFloat() * size.width * 2f
            val y = (animateY + offsets[i]) * size.height * 5
            val y2 = (animateY - 1f + offsets[i]) * size.height * 5

            rotate(
                degrees = rotationAngle
            ) {
                drawLine(
                    color = dropColor,
                    start = Offset(x = x, y = y),
                    end = Offset(x = x, y = y + dropLength),
                    strokeWidth = dropWidth
                )

                drawLine(
                    color = dropColor,
                    start = Offset(x = x, y = y2),
                    end = Offset(x = x, y = y2 + dropLength),
                    strokeWidth = dropWidth
                )
            }
        }
    }
}