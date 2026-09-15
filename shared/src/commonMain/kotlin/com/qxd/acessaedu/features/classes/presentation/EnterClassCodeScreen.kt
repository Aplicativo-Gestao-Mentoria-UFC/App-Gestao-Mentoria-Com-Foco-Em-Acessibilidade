package com.qxd.acessaedu.features.classes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnterClassCodeScreen(
    onBackClick: () -> Unit,
    onEnterClick: (String) -> Unit = {}
) {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 28.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        onBackClick()
                    },
                tint = Color(0xFF25263F)
            )

            Spacer(modifier = Modifier.size(14.dp))

            Text(
                text = "Entrar com código",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25263F)
            )
        }

        Spacer(modifier = Modifier.height(105.dp))

        Text(
            text = "Insira aqui o código da turma\ninformado pelo professor",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF595959),
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF25263F),
                unfocusedBorderColor = Color(0xFF25263F),
                cursorColor = Color(0xFF25263F),
                focusedTextColor = Color(0xFF25263F),
                unfocusedTextColor = Color(0xFF25263F)
            )
        )

        Spacer(modifier = Modifier.height(34.dp))

        EnterClassButton(
            onClick = {
                if (code.isNotBlank()) {
                    onEnterClick(code.trim())
                }
            }
        )
    }
}

@Composable
private fun EnterClassButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF4B72C8))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Entrar na turma",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(38.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Entrar",
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF4B72C8)
            )
        }
    }
}