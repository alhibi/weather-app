package com.dn0ne.weatherapp.presentation.components.weather

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.dn0ne.weatherapp.R
import kotlin.math.roundToInt

@Composable
fun Clouds(
    modifier: Modifier = Modifier,
    preferredHeight: Dp = 300.dp,
    alpha: Float = 1f
) {
    val cloudBitmap = ImageBitmap.imageResource(id = R.drawable.cloud)
    val localDensity = LocalDensity.current

    val scaledHeight = localDensity.run { preferredHeight.toPx() }.roundToInt()
    val scaledWidth = (scaledHeight.toFloat() * cloudBitmap.width / cloudBitmap.height).roundToInt()

    Canvas(modifier = modifier) {
        drawImage(
            image = cloudBitmap,
            dstSize = IntSize(
                width = scaledWidth,
                height = scaledHeight
            ),
            dstOffset = IntOffset(
                x = -(scaledWidth / 1.7f).roundToInt(),
                y = 0
            ),
            alpha = alpha,
            blendMode = BlendMode.Screen
        )

        drawImage(
            image = cloudBitmap,
            dstSize = IntSize(
                width = scaledWidth,
                height = scaledHeight
            ),
            dstOffset = IntOffset(
                x = size.width.roundToInt() - (scaledWidth / 2.3f).roundToInt() ,
                y = 0
            ),
            alpha = alpha,
            blendMode = BlendMode.Screen
        )
    }
}
