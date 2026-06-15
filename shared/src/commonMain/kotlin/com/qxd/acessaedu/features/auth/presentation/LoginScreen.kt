package com.qxd.acessaedu.features.auth.presentation

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.eye
import acessaedu.shared.generated.resources.eye_slash
import acessaedu.shared.generated.resources.mail
import acessaedu.shared.generated.resources.uil_lock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.features.auth.layout.AuthHeaderType
import com.qxd.acessaedu.features.auth.layout.AuthLayout
import com.qxd.acessaedu.features.auth.presentation.components.AuthTextField
import com.qxd.acessaedu.features.auth.presentation.components.validatePassword
import com.qxd.acessaedu.ui.components.AppPrimaryButton
import com.qxd.acessaedu.ui.theme.DefaultColors

@Composable
fun LoginScreen(
    onCreateAccountClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    AuthLayout(
        headerType = AuthHeaderType.Logo,
        contentHeightFraction = 0.70f
    ) {
        Text(
            text = "Vamos começar!",
            color = DefaultColors.TextDark,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Entre na sua conta",
            color = DefaultColors.TextDark,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(38.dp))

        AuthTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = "Email",
            leadingIcon = Res.drawable.mail,
            keyboardType = KeyboardType.Email,
            error = emailError
        )

        Spacer(modifier = Modifier.height(14.dp))

        AuthTextField(
            value = password,
            onValueChange = {
                password = it

                passwordError = if (it.isBlank()) {
                    null
                } else {
                    validatePassword(it)
                }
            },
            label = "Senha",
            leadingIcon = Res.drawable.uil_lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = if (showPassword) {
                Res.drawable.eye
            } else {
                Res.drawable.eye_slash
            },
            onTrailingClick = {
                showPassword = !showPassword
            },
            maxLength = 30,
            error = passwordError
        )

        Spacer(modifier = Modifier.height(48.dp))

        AppPrimaryButton(
            text = "Entrar",
            onClick = {
                emailError = null
                passwordError = null

                var hasError = false

                if (email.isBlank() || !email.contains("@")) {
                    emailError = "E-mail inválido ou não cadastrado."
                    hasError = true
                }

                if (password.isBlank()) {
                    passwordError = "Senha incorreta. Tente novamente."
                    hasError = true
                }

                if (!hasError) {
                    println("Login can be requested now")
                }
            }
        )

        Spacer(modifier = Modifier.height(64.dp))

        Row {
            Text(
                text = "Esqueceu sua senha? ",
                color = DefaultColors.TextMuted,
                fontSize = 12.sp
            )

            Text(
                text = "Recuperar",
                color = DefaultColors.PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    println("Recover password")
                }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row {
            Text(
                text = "Ainda não tem uma conta? ",
                color = DefaultColors.TextMuted,
                fontSize = 12.sp
            )

            Text(
                text = "Criar conta",
                color = DefaultColors.PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onCreateAccountClick()
                }
            )
        }
    }
}