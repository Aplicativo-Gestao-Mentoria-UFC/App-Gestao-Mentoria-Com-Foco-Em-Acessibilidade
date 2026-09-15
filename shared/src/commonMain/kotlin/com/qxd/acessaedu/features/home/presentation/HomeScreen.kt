package com.qxd.acessaedu.features.home.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.core.presentation.components.AcessaEduScaffold
import com.qxd.acessaedu.core.presentation.components.MainTab
import com.qxd.acessaedu.features.classes.presentation.components.TurmaCard
import androidx.compose.foundation.clickable

@Composable
fun HomeScreen(
    onTabClick: (MainTab) -> Unit
) {
    AcessaEduScaffold(
        selectedTab = MainTab.HOME,
        onTabClick = onTabClick
    ) {
        Text(
            text = "Olá, Geraldo",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Veja suas próximas monitorias",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        NextClassCard()

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Minhas turmas:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ver todas >",
                fontSize = 13.sp,
                color = Color(0xFF0B5ED7),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onTabClick(MainTab.TURMAS)
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TurmaCard(
            nomeTurma = "Banco de dados",
            nomeMonitor = "Pedro",
            dataEncontro = "Sexta, 14h00",
            icon = Icons.Filled.Folder,
            iconBackgroundColor = Color(0xFFEAF3FF),
            iconColor = Color(0xFF0B5ED7),
            buttonColor = Color(0xFF0B5ED7)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TurmaCard(
            nomeTurma = "Desenvolvimento Web",
            nomeMonitor = "Pedro",
            dataEncontro = "Sexta, 14h00",
            icon = Icons.Filled.School,
            iconBackgroundColor = Color(0xFFEAF9EA),
            iconColor = Color(0xFF39A935),
            buttonColor = Color(0xFF39A935)
        )
    }
}

@Composable
private fun NextClassCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFEAF3FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Calendário",
                    modifier = Modifier.size(32.dp),
                    tint = Color(0xFF0B5ED7)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Próximo encontro",
                    fontSize = 12.sp,
                    color = Color(0xFF0B5ED7),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Engenharia de Software",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Hoje, 14h00",
                    fontSize = 13.sp,
                    color = Color(0xFF0B5ED7),
                )

                Text(
                    text = "Monitor: Pedro Perikles",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.End
            )
        ) {
            Button(
                onClick = {},
                modifier = Modifier.height(30.dp),
                contentPadding = PaddingValues(
                    horizontal = 10.dp,
                    vertical = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF0B5ED7)
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF0B5ED7)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Ver detalhes",
                    fontSize = 12.sp,
                    color = Color(0xFF0B5ED7)
                )
            }

            Button(
                onClick = {},
                modifier = Modifier.height(30.dp),
                contentPadding = PaddingValues(
                    horizontal = 10.dp,
                    vertical = 0.dp
                )
            ) {
                Text(
                    text = "Ouvir detalhes",
                    fontSize = 12.sp
                )
            }
        }
    }
}