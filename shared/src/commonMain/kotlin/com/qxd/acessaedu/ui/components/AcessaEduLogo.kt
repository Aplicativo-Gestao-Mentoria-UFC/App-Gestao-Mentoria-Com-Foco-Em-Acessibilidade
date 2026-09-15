package com.qxd.acessaedu.ui.components

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.logo_acessaedu
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun AcessaEduLogo() {
    Image(
        painter = painterResource(Res.drawable.logo_acessaedu),
        contentDescription = "AcessaEdu logo",
        modifier = Modifier
            .width(170.dp)
            .height(120.dp)
    )
}
