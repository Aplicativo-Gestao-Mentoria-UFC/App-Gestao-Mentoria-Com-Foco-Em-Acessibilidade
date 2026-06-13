package com.qxd.acessaedu.features.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.ui.theme.DefaultColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


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
    val activeColor = if (hasError) DefaultColors.ErrorRed else DefaultColors.TextMuted
    val lineColor = if (hasError) DefaultColors.ErrorRed else DefaultColors.LineGray

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
                color = DefaultColors.TextDark,
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
                            colorFilter = ColorFilter.tint(DefaultColors.TextMuted)
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
                    color = DefaultColors.ErrorRed,
                    fontSize = 10.sp,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            if (maxLength != null) {
                Text(
                    text = "${value.length} / ${maxLength.toString().padStart(2, '0')}",
                    color = if (hasError) DefaultColors.ErrorRed else DefaultColors.TextMuted,
                    fontSize = 10.sp
                )
            }
        }
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