package com.qxd.acessaedu.features.classes.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.heightIn

@Composable
fun TurmaCard(
    nomeTurma: String,
    nomeMonitor: String,
    dataEncontro: String,
    icon: ImageVector,
    iconBackgroundColor: Color,
    iconColor: Color,
    buttonColor: Color,
    modifier: Modifier = Modifier,
    onEntrarClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 108.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFBFD6FF),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(CircleShape)
                .background(iconBackgroundColor)
                .border(
                    width = 2.dp,
                    color = iconColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = nomeTurma,
                modifier = Modifier.size(34.dp),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = nomeTurma,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF263244)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Monitor",
                    modifier = Modifier.size(12.dp),
                    tint = Color(0xFF7B7F89)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Monitor: $nomeMonitor",
                    fontSize = 12.sp,
                    color = Color(0xFF7B7F89)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Schedule,
                    contentDescription = "Próximo encontro",
                    modifier = Modifier.size(12.dp),
                    tint = Color(0xFF7B7F89)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Próximo encontro:",
                    fontSize = 12.sp,
                    color = Color(0xFF7B7F89)
                )
            }

            Text(
                text = dataEncontro,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = buttonColor
            )
        }

        Button(
            onClick = onEntrarClick,
            modifier = Modifier
                .height(34.dp)
                .widthIn(min = 96.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(
                width = 0.dp,
                color = buttonColor
            )
        ) {
            Text(
                text = "Ver detalhes",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}