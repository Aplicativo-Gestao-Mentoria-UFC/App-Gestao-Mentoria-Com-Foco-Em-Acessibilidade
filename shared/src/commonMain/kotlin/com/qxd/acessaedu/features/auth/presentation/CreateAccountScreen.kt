package com.qxd.acessaedu.features.auth.presentation

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.doc_icon
import acessaedu.shared.generated.resources.eye
import acessaedu.shared.generated.resources.eye_slash
import acessaedu.shared.generated.resources.mail
import acessaedu.shared.generated.resources.person_icon
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
import com.qxd.acessaedu.ui.components.TermsRow
import com.qxd.acessaedu.ui.theme.DefaultColors

@Composable
fun CreateAccountScreen(
    onBackToLogin: () -> Unit,
    onCodeSent: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    var showPassword by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var matriculaError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var termsError by remember { mutableStateOf<String?>(null) }

    AuthLayout(
        headerType = AuthHeaderType.Title,
        title = "Criar conta",
        onBackClick = onBackToLogin,
        contentHeightFraction = 0.83f
    ) {
        AuthTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = null
            },
            label = "Nome completo",
            leadingIcon = Res.drawable.person_icon,
            error = nameError
        )

        Spacer(modifier = Modifier.height(14.dp))

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
            value = matricula,
            onValueChange = {
                matricula = it
                matriculaError = null
            },
            label = "Matrícula",
            leadingIcon = Res.drawable.doc_icon,
            keyboardType = KeyboardType.Number,
            error = matriculaError
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

                if (confirmPassword.isNotBlank()) {
                    confirmPasswordError = if (confirmPassword != it) {
                        "As senhas devem ser iguais."
                    } else {
                        null
                    }
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

        Spacer(modifier = Modifier.height(14.dp))

        AuthTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = if (it.isNotBlank() && it != password) {
                    "As senhas devem ser iguais."
                } else {
                    null
                }
            },
            label = "Confirmar senha",
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
            error = confirmPasswordError
        )

        Spacer(modifier = Modifier.height(12.dp))

        TermsRow(
            checked = acceptedTerms,
            onCheckedChange = {
                acceptedTerms = it
                termsError = null
            },
            error = termsError
        )

        Spacer(modifier = Modifier.height(28.dp))

        AppPrimaryButton(
            text = "Enviar código",
            onClick = {
                nameError = null
                emailError = null
                matriculaError = null
                passwordError = null
                confirmPasswordError = null
                termsError = null

                var hasError = false

                if (name.isBlank()) {
                    nameError = "Informe seu nome completo."
                    hasError = true
                }

                if (email.isBlank() || !email.contains("@")) {
                    emailError = "Informe um e-mail válido."
                    hasError = true
                }

                if (matricula.length < 6) {
                    matriculaError = "Informe uma matrícula válida."
                    hasError = true
                }

                val passwordValidationError = validatePassword(password)
                if (password.isBlank()) {
                    passwordError = "Informe uma senha."
                    hasError = true
                } else if (passwordValidationError != null) {
                    passwordError = passwordValidationError
                    hasError = true
                }

                if (password.length < 8) {
                    passwordError = "A senha deve ter pelo menos 8 caracteres."
                    hasError = true
                }

                if (confirmPassword.isBlank()) {
                    confirmPasswordError = "Confirme sua senha."
                    hasError = true
                } else if (confirmPassword != password) {
                    confirmPasswordError = "As senhas devem ser iguais."
                    hasError = true
                }

                if (!acceptedTerms) {
                    termsError = "Você precisa aceitar os termos para continuar."
                    hasError = true
                }

                if (!hasError) {
                    onCodeSent(email)
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text(
                text = "Já tem uma conta? ",
                color = DefaultColors.TextMuted,
                fontSize = 12.sp
            )

            Text(
                text = "Entrar",
                color = DefaultColors.PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onBackToLogin()
                }
            )
        }
    }
}