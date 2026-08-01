//package com.qxd.acessaedu.features.auth.presentation
//
//import acessaedu.shared.generated.resources.Res
//import acessaedu.shared.generated.resources.uil_lock
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.qxd.acessaedu.features.auth.layout.AuthHeaderType
//import com.qxd.acessaedu.features.auth.layout.AuthLayout
//import com.qxd.acessaedu.features.auth.presentation.components.AuthTextField
//import com.qxd.acessaedu.ui.components.AppPrimaryButton
//import com.qxd.acessaedu.ui.theme.DefaultColors
//
//@Composable
//fun VerifyCodeScreen(
//    email: String,
//    onBack: () -> Unit
//) {
//    var code by remember { mutableStateOf("") }
//    var codeError by remember { mutableStateOf<String?>(null) }
//
//    AuthLayout(
//        headerType = AuthHeaderType.Title,
//        title = "Confirmar cadastro",
//        onBackClick = onBack,
//        contentHeightFraction = 0.83f
//    ) {
//        Text(
//            text = "Verificar e-mail",
//            color = DefaultColors.TextDark,
//            fontSize = 21.sp,
//            fontWeight = FontWeight.Bold
//        )
//
//        Text(
//            text = "Digite o código enviado para:",
//            color = DefaultColors.TextDark,
//            fontSize = 12.sp
//        )
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            text = email,
//            color = DefaultColors.PrimaryBlue,
//            fontSize = 12.sp,
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.height(38.dp))
//
//        AuthTextField(
//            value = code,
//            onValueChange = {
//                code = it
//                codeError = null
//            },
//            label = "Código de verificação",
//            leadingIcon = Res.drawable.uil_lock,
//            keyboardType = KeyboardType.Number,
//            maxLength = 6,
//            error = codeError
//        )
//
//        Spacer(modifier = Modifier.height(18.dp))
//
//        AppPrimaryButton(
//            text = "Confirmar",
//            onClick = {
//                if (code.length < 6) {
//                    codeError = "Digite o código completo."
//                } else {
//                    println("Verify code request can be sent now")
//                }
//            }
//        )
//
//        Spacer(modifier = Modifier.height(18.dp))
//
//        TextButton(onClick = onBack) {
//            Text(
//                text = "Voltar",
//                color = DefaultColors.PrimaryBlue
//            )
//        }
//    }
//}

package com.qxd.acessaedu.features.auth.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.features.auth.layout.AuthHeaderType
import com.qxd.acessaedu.features.auth.layout.AuthLayout
import com.qxd.acessaedu.features.auth.presentation.components.OtpCodeField
import com.qxd.acessaedu.ui.components.AppPrimaryButton
import com.qxd.acessaedu.ui.theme.DefaultColors
import kotlinx.coroutines.delay

@Composable
fun VerifyCodeScreen(
    onBack: () -> Unit,
    title: String = "Confirmar cadastro",
    description: String = "Digite o código enviado para seu e-mail cadastrado.",
    codeLength: Int = 4,
    resendCooldownSeconds: Int = 60,
    onVerify: (code: String, onError: (String) -> Unit) -> Unit = { _, _ -> },
    onResendCode: () -> Unit = {}
) {
    var code by remember { mutableStateOf("") }
    var codeError by remember { mutableStateOf<String?>(null) }
    var resendMessage by remember { mutableStateOf<String?>(null) }
    var secondsLeft by remember { mutableStateOf(resendCooldownSeconds) }

    LaunchedEffect(secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }

    AuthLayout(
        headerType = AuthHeaderType.Title,
        title = title,
        onBackClick = onBack,
        contentHeightFraction = 0.78f
    ) {
        Text(
            text = description,
            color = DefaultColors.TextDark,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        OtpCodeField(
            value = code,
            onValueChange = {
                code = it
                codeError = null
            },
            length = codeLength,
            isError = codeError != null
        )

        Spacer(modifier = Modifier.height(10.dp))

        codeError?.let { error ->
            Text(
                text = error,
                color = DefaultColors.ErrorRed,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
        }

        resendMessage?.let { message ->
            Text(
                text = "✓ $message",
                color = DefaultColors.EduGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))
        }

        if (secondsLeft > 0) {
            Row {
                Text(
                    text = "Reenviar código em ",
                    color = DefaultColors.TextMuted,
                    fontSize = 12.sp
                )
                Text(
                    text = "${secondsLeft}s",
                    color = DefaultColors.PrimaryBlue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Text(
                text = "Reenviar código",
                color = DefaultColors.PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    code = ""
                    codeError = null
                    resendMessage = "Novo código enviado para seu e-mail."
                    secondsLeft = resendCooldownSeconds
                    onResendCode()
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        AppPrimaryButton(
            text = "Verificar",
            onClick = {
                if (code.length < codeLength) {
                    codeError = "Digite o código completo."
                } else {
                    resendMessage = null
                    onVerify(code) { errorMessage -> codeError = errorMessage }
                }
            }
        )
    }
}