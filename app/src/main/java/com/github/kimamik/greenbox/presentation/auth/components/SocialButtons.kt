package com.github.kimamik.greenbox.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kimamik.greenbox.R
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.ui.theme.SocialColors

@Composable
fun VkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(containerColor = SocialColors.VK)
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_vk),
            contentDescription = null,
        )
    }
}

@Composable
fun OkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified),
        contentPadding = PaddingValues()
    ) {
        val gradient = Brush.linearGradient(
            listOf(
                SocialColors.OK_GRADIENT_START,
                SocialColors.OK_GRADIENT_FINISH
            )
        )
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_ok),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun VkButtonPreview() = GBPreview {
    VkButton(
        onClick = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun OkButtonPreview() = GBPreview {
    OkButton(
        onClick = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}