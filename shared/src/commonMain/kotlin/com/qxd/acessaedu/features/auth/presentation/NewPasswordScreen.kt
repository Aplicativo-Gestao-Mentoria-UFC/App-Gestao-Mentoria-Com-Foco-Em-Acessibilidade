package com.qxd.acessaedu.features.auth.presentation

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.eye
import acessaedu.shared.generated.resources.eye_slash
import acessaedu.shared.generated.resources.uil_lock
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

/**
 * Etapa final do fluxo de "esqueci minha senha": definição da nova senha.
 */
@Composable
fun NewPasswordScreen(
    onBack: () -> Unit,
    onContinue: (newPassword: String) -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    AuthLayout(
        headerType = AuthHeaderType.Title,
        title = "Esqueci minha senha",
        onBackClick = onBack,
        contentHeightFraction = 0.75f
    ) {
        Text(
            text = "Crie sua nova senha",
            color = DefaultColors.TextDark,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(38.dp))

        AuthTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (it.isBlank()) null else validatePassword(it)
            },
            label = "Senha",
            leadingIcon = Res.drawable.uil_lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = if (showPassword) Res.drawable.eye else Res.drawable.eye_slash,
            onTrailingClick = { showPassword = !showPassword },
            maxLength = 30,
            error = passwordError
        )

        Spacer(modifier = Modifier.height(14.dp))

        AuthTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = when {
                    it.isBlank() -> null
                    it != password -> "As senhas não coincidem."
                    else -> null
                }
            },
            label = "Confirmar senha",
            leadingIcon = Res.drawable.uil_lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showConfirmPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = if (showConfirmPassword) Res.drawable.eye else Res.drawable.eye_slash,
            onTrailingClick = { showConfirmPassword = !showConfirmPassword },
            maxLength = 30,
            error = confirmPasswordError
        )

        Spacer(modifier = Modifier.height(48.dp))

        AppPrimaryButton(
            text = "Continuar",
            onClick = {
                passwordError = when {
                    password.isBlank() -> "Digite uma senha."
                    else -> validatePassword(password)
                }

                confirmPasswordError = when {
                    confirmPassword.isBlank() -> "Confirme sua senha."
                    confirmPassword != password -> "As senhas não coincidem."
                    else -> null
                }

                if (passwordError == null && confirmPasswordError == null) {
                    onContinue(password)
                }
            }
        )
    }
}