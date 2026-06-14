package com.qxd.acessaedu.features.auth.presentation

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.uil_lock
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.qxd.acessaedu.features.auth.layout.AuthHeaderType
import com.qxd.acessaedu.features.auth.layout.AuthLayout
import com.qxd.acessaedu.features.auth.presentation.components.AuthTextField
import com.qxd.acessaedu.ui.components.AppPrimaryButton
import com.qxd.acessaedu.ui.theme.DefaultColors

data class VerifyCodeScreen(
    val email: String
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        VerifyCodeContent(
            email = email,
            onBack = {
                navigator.pop()
            }
        )
    }
}

@Composable
fun VerifyCodeContent(
    email: String,
    onBack: () -> Unit
) {
    var code by remember { mutableStateOf("") }
    var codeError by remember { mutableStateOf<String?>(null) }

    AuthLayout(
        headerType = AuthHeaderType.Title,
        title = "Confirmar cadastro",
        onBackClick = onBack,
        contentHeightFraction = 0.83f
    ) {
        Text(
            text = "Verificar e-mail",
            color = DefaultColors.TextDark,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Digite o código enviado para:",
            color = DefaultColors.TextDark,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = email,
            color = DefaultColors.PrimaryBlue,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(38.dp))

        AuthTextField(
            value = code,
            onValueChange = {
                code = it
                codeError = null
            },
            label = "Código de verificação",
            leadingIcon = Res.drawable.uil_lock,
            keyboardType = KeyboardType.Number,
            maxLength = 6,
            error = codeError
        )

        Spacer(modifier = Modifier.height(18.dp))

        AppPrimaryButton(
            text = "Confirmar",
            onClick = {
                if (code.length < 6) {
                    codeError = "Digite o código completo."
                } else {
                    println("Verify code request can be sent now")
                }
            }
        )

        Spacer(modifier = Modifier.height(18.dp))

        TextButton(onClick = onBack) {
            Text(
                text = "Voltar",
                color = DefaultColors.PrimaryBlue
            )
        }
    }
}
