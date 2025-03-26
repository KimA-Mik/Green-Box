package com.github.kimamik.greenbox.ui.theme.components

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.ui.theme.Glass

@Composable
fun BlurredButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    blurRadius: Dp = 10.dp,
    edgeTreatment: BlurredEdgeTreatment = BlurredEdgeTreatment.Unbounded,
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(enabled = enabled, onClick = onClick)
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .semantics { role = Role.Button },
        contentAlignment = Alignment.Center
    ) {
        val mergedStyle = LocalTextStyle.current.merge(MaterialTheme.typography.labelLarge)
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onPrimary,
            LocalTextStyle provides mergedStyle,
        ) {
            Row(
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = ButtonDefaults.MinHeight
                    )
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }

        val blurredModifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Modifier.graphicsLayer(
                renderEffect =
                    RenderEffect.createBlurEffect(
                        21f, 21f,
                        Shader.TileMode.MIRROR
                    ).asComposeRenderEffect()
            )
        } else {
            Modifier
        }
        Surface(
            modifier = blurredModifier
                .fillMaxSize()
                .graphicsLayer(),
            shape = shape,
            color = Glass
        ) { }
    }
}

@Preview
@Composable
private fun BlurredButtonPreview() = GBPreview {
    BlurredButton(onClick = {}, modifier = Modifier.padding(16.dp), blurRadius = 21.dp) {
        Text("Blurred button")
    }
}