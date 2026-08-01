package com.qxd.acessaedu.features.auth.presentation

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.mail
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.features.auth.layout.AuthHeaderType
import com.qxd.acessaedu.features.auth.layout.AuthLayout
import com.qxd.acessaedu.ui.components.AppPrimaryButton
import com.qxd.acessaedu.ui.theme.DefaultColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class RecoveryMethod { EMAIL, SMS }

@Composable
fun ForgotPasswordScreen(
    emailAddress: String,
    phoneNumber: String,
    onBack: () -> Unit,
    onContinue: (RecoveryMethod) -> Unit,
    smsIcon: DrawableResource = Res.drawable.mail
) {
    var selectedMethod by remember { mutableStateOf(RecoveryMethod.EMAIL) }

    AuthLayout(
        headerType = AuthHeaderType.Logo,
        onBackClick = onBack,
        contentHeightFraction = 0.78f
    ) {
        Text(
            text = "Esqueci minha senha",
            color = DefaultColors.TextDark,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Recuperar senha",
            color = DefaultColors.TextMuted,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Escolha como deseja receber\no código de recuperação.",
            color = DefaultColors.TextDark,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        RecoveryOptionCard(
            icon = Res.drawable.mail,
            label = "Via Email",
            value = emailAddress,
            isSelected = selectedMethod == RecoveryMethod.EMAIL,
            onClick = { selectedMethod = RecoveryMethod.EMAIL }
        )

        Spacer(modifier = Modifier.height(14.dp))

        RecoveryOptionCard(
            icon = smsIcon,
            label = "Via SMS",
            value = phoneNumber,
            isSelected = selectedMethod == RecoveryMethod.SMS,
            onClick = { selectedMethod = RecoveryMethod.SMS }
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppPrimaryButton(
            text = "Continuar",
            onClick = { onContinue(selectedMethod) }
        )
    }
}

@Composable
private fun RecoveryOptionCard(
    icon: DrawableResource,
    label: String,
    value: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) DefaultColors.PrimaryBlue else DefaultColors.LineGray,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(DefaultColors.PrimaryBlue.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = label,
                tint = DefaultColors.PrimaryBlue,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                color = DefaultColors.TextDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = value,
                color = DefaultColors.TextMuted,
                fontSize = 11.sp
            )
        }
    }
}