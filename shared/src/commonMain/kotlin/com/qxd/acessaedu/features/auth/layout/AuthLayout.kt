package com.qxd.acessaedu.features.auth.layout
import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.arrow
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.ui.components.AcessaEduLogo
import com.qxd.acessaedu.ui.theme.DefaultColors
import org.jetbrains.compose.resources.painterResource

enum class AuthHeaderType {
    Logo,
    Title
}

@Composable
fun AuthLayout(
    headerType: AuthHeaderType,
    title: String? = null,
    onBackClick: (() -> Unit)? = null,
    contentHeightFraction: Float = 0.70f,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultColors.DarkBlue)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        HeaderDecorations()

        when (headerType) {
            AuthHeaderType.Logo -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 38.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AcessaEduLogo()
                }
            }

            AuthHeaderType.Title -> {
                AuthTitleHeader(
                    title = title.orEmpty(),
                    onBackClick = onBackClick
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(contentHeightFraction)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = 44.dp,
                        topEnd = 0.dp
                    )
                )
                .background(Color.White)
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 430.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}

@Composable
private fun AuthTitleHeader(
    title: String,
    onBackClick: (() -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow),
                    contentDescription = "Voltar",
                    tint = DefaultColors.TextDark,
                    modifier = Modifier.size(18.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(36.dp))
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.size(36.dp))
    }
}

@Composable
private fun HeaderDecorations() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(DefaultColors.DarkBlue)
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.TopEnd)
                .padding(top = 20.dp, end = 18.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.06f))
        )

        Box(
            modifier = Modifier
                .size(170.dp)
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(34.dp))
                .background(Color.White.copy(alpha = 0.04f))
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(34.dp))
                .background(Color.White.copy(alpha = 0.04f))
        )
    }
}