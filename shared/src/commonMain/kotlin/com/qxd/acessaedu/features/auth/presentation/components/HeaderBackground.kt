package com.qxd.acessaedu.features.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.qxd.acessaedu.ui.theme.DefaultColors


@Composable
fun HeaderBackground() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(DefaultColors.DarkBlue)
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopEnd)
                .padding(top = 28.dp, end = 22.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.06f))
        )

        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.BottomStart)
                .padding(start = 18.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(DefaultColors.DarkBlue2.copy(alpha = 0.65f))
        )

        Box(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White.copy(alpha = 0.04f))
        )
    }
}