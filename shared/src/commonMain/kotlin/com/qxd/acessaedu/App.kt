package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.acessaEduLogo
import acessaedu.shared.generated.resources.apple_icon
import acessaedu.shared.generated.resources.google_icon
import acessaedu.shared.generated.resources.iconamoon_eye_light
import acessaedu.shared.generated.resources.mail
import acessaedu.shared.generated.resources.person_icon
import acessaedu.shared.generated.resources.uil_lock
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.qxd.acessaedu.ui.theme.AcessaEduTheme
import org.jetbrains.compose.resources.DrawableResource

private val DarkBlue = Color(0xFF061A3B)
private val DarkBlue2 = Color(0xFF082148)
private val PrimaryBlue = Color(0xFF0E55D8)
private val EduGreen = Color(0xFF7CC242)
private val TextDark = Color(0xFF1C2144)
private val TextMuted = Color(0xFF777A86)
private val ErrorRed = Color(0xFFFF2D45)
private val LineGray = Color(0xFFD4D4D4)

sealed interface AuthScreen {
    data object Login : AuthScreen
    data object CreateAccount : AuthScreen
    data class VerifyCode(val email: String) : AuthScreen
}

@Composable
fun App() {
    var screen by remember { mutableStateOf<AuthScreen>(AuthScreen.Login) }

    AcessaEduTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            when (val currentScreen = screen) {
                AuthScreen.Login -> LoginScreen(
                    onCreateAccountClick = {
                        screen = AuthScreen.CreateAccount
                    }
                )

                AuthScreen.CreateAccount -> CreateAccountScreen(
                    onBackToLogin = {
                        screen = AuthScreen.Login
                    },
                    onCodeSent = { email ->
                        screen = AuthScreen.VerifyCode(email)
                    }
                )

                is AuthScreen.VerifyCode -> VerifyCodeScreen(
                    email = currentScreen.email,
                    onBack = {
                        screen = AuthScreen.CreateAccount
                    }
                )
            }
        }
    }
}

@Composable
fun AuthLayout(
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        HeaderBackground()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 52.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AcessaEduLogo()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = 44.dp,
                        topEnd = 0.dp
                    )
                )
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 22.dp)
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 430.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}

@Composable
fun HeaderBackground() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(DarkBlue)
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
                .background(DarkBlue2.copy(alpha = 0.65f))
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

@Composable
fun AcessaEduLogo() {
    Image(
        painter = painterResource(Res.drawable.acessaEduLogo),
        contentDescription = "AcessaEdu logo",
        modifier = Modifier
            .width(170.dp)
            .height(120.dp)
    )
}

@Composable
fun LoginScreen(
    onCreateAccountClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var termsError by remember { mutableStateOf<String?>(null) }

    AuthLayout {
        Text(
            text = "Vamos começar!",
            color = TextDark,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Entre na sua conta",
            color = TextDark,
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
            leadingIcon = Res.drawable.mail ,
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
                Res.drawable.iconamoon_eye_light
            } else {
                Res.drawable.iconamoon_eye_light
            },
            onTrailingClick = {
                showPassword = !showPassword
            },
            maxLength = 24,
            error = passwordError
        )

        Spacer(modifier = Modifier.height(6.dp))

        TermsRow(
            checked = acceptedTerms,
            onCheckedChange = {
                acceptedTerms = it
                termsError = null
            },
            error = termsError
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryAuthButton(
            text = "Entrar",
            onClick = {
                emailError = null
                passwordError = null
                termsError = null

                var hasError = false

                if (email.isBlank() || !email.contains("@")) {
                    emailError = "E-mail inválido ou não cadastrado."
                    hasError = true
                }

                if (password.isBlank()) {
                    passwordError = "Senha incorreta. Tente novamente."
                    hasError = true
                }

                if (!acceptedTerms) {
                    termsError = "Você precisa aceitar os termos para continuar."
                    hasError = true
                }

                if (!hasError) {
                    println("Login can be requested now")
                }
            }
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Ou continue com",
            color = TextMuted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialButton(
                icon = Res.drawable.apple_icon,
                contentDescription = "Continue with Google",
                onClick = {
                    println("Google login")
                }
            )

            Spacer(modifier = Modifier.width(20.dp))

            SocialButton(
                icon = Res.drawable.google_icon,
                contentDescription = "Continue with Apple",
                onClick = {
                    println("Apple login")
                }
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row {
            Text(
                text = "Esqueceu sua senha? ",
                color = TextMuted,
                fontSize = 12.sp
            )

            Text(
                text = "Recuperar",
                color = PrimaryBlue,
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
                color = TextMuted,
                fontSize = 12.sp
            )

            Text(
                text = "Criar conta",
                color = PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onCreateAccountClick()
                }
            )
        }
    }
}


@Composable
fun CreateAccountScreen(
    onBackToLogin: () -> Unit,
    onCodeSent: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var termsError by remember { mutableStateOf<String?>(null) }

    AuthLayout {
        Text(
            text = "Criar conta",
            color = TextDark,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Cadastre-se para continuar",
            color = TextDark,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

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
            visualTransformation = PasswordVisualTransformation(),
            maxLength = 8,
            error = passwordError
        )

        Spacer(modifier = Modifier.height(6.dp))

        TermsRow(
            checked = acceptedTerms,
            onCheckedChange = {
                acceptedTerms = it
                termsError = null
            },
            error = termsError
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryAuthButton(
            text = "Enviar código",
            onClick = {
                nameError = null
                emailError = null
                passwordError = null
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

                if (password.length < 6) {
                    passwordError = "A senha deve ter pelo menos 6 caracteres."
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
                color = TextMuted,
                fontSize = 12.sp
            )

            Text(
                text = "Entrar",
                color = PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onBackToLogin()
                }
            )
        }
    }
}



@Composable
fun VerifyCodeScreen(
    email: String,
    onBack: () -> Unit
) {
    var code by remember { mutableStateOf("") }
    var codeError by remember { mutableStateOf<String?>(null) }

    AuthLayout {
        Text(
            text = "Verificar e-mail",
            color = TextDark,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Digite o código enviado para:",
            color = TextDark,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = email,
            color = PrimaryBlue,
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

        PrimaryAuthButton(
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
                color = PrimaryBlue
            )
        }
    }
}

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: DrawableResource,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: DrawableResource? = null,
    onTrailingClick: (() -> Unit)? = null,
    maxLength: Int? = null,
    error: String? = null
) {
    val hasError = error != null
    val activeColor = if (hasError) ErrorRed else TextMuted
    val lineColor = if (hasError) ErrorRed else LineGray

    Column(modifier = modifier.fillMaxWidth()) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (maxLength == null || newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                color = TextDark,
                fontSize = 13.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(leadingIcon),
                        contentDescription = label,
                        modifier = Modifier.size(18.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(activeColor)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = label,
                                color = activeColor,
                                fontSize = 12.sp
                            )
                        }

                        innerTextField()
                    }

                    if (trailingIcon != null) {
                        Image(
                            painter = painterResource(trailingIcon),
                            contentDescription = "Mostrar ou ocultar senha",
                            modifier = Modifier
                                .size(18.dp)
                                .clickable {
                                    onTrailingClick?.invoke()
                                },
                            contentScale = ContentScale.Fit,
                            colorFilter = ColorFilter.tint(TextMuted)
                        )
                    }
                }
            }
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = lineColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (error != null) {
                Text(
                    text = error,
                    color = ErrorRed,
                    fontSize = 10.sp,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            if (maxLength != null) {
                Text(
                    text = "${value.length} / ${maxLength.toString().padStart(2, '0')}",
                    color = if (hasError) ErrorRed else TextMuted,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun TermsRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    error: String?
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Eu concordo com os termos e condições",
                color = TextDark,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (error != null) {
            Text(
                text = error,
                color = ErrorRed,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 38.dp)
            )
        }
    }
}

@Composable
fun PrimaryAuthButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(PrimaryBlue)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 6.dp)
                .size(42.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "→",
                color = PrimaryBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SocialButton(
    icon: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFECECEC),
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(22.dp),
            contentScale = ContentScale.Fit
        )
    }
}

fun validatePassword(password: String): String? {
    if (password.isBlank()) {
        return "Informe sua senha."
    }

    if (password.length < 8) {
        return "A senha deve ter pelo menos 8 caracteres."
    }

    if (!password.any { it.isUpperCase() }) {
        return "A senha deve ter pelo menos uma letra maiúscula."
    }

    if (!password.any { it.isLowerCase() }) {
        return "A senha deve ter pelo menos uma letra minúscula."
    }

    if (!password.any { it.isDigit() }) {
        return "A senha deve ter pelo menos um número."
    }

    val hasSpecialChar = password.any { !it.isLetterOrDigit() }

    if (!hasSpecialChar) {
        return "A senha deve ter pelo menos um caractere especial."
    }

    return null
}