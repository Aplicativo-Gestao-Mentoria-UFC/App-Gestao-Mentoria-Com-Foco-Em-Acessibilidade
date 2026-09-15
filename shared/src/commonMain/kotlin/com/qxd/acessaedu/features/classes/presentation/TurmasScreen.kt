package com.qxd.acessaedu.features.classes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.FloatingActionButton
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

@Composable
fun TurmasScreen(
    onTabClick: (MainTab) -> Unit,
    onAddClassClick: () -> Unit,
    onClassDetailsClick: () -> Unit
) {
    AcessaEduScaffold(
        selectedTab = MainTab.TURMAS,
        onTabClick = onTabClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Minhas Turmas",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF263244)
            )

            Text(
                text = "Acompanhe todas as turmas que você participa.",
                fontSize = 12.sp,
                color = Color(0xFF4F5563)
            )

            Spacer(modifier = Modifier.height(15.dp))

            SearchClassBar()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Turmas ativas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF263244)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFBFD6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "3",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0B5ED7)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 90.dp)
                ) {
                    TurmaCard(
                        nomeTurma = "Banco de dados",
                        nomeMonitor = "Pedro",
                        dataEncontro = "Sexta, 14h00",
                        icon = Icons.Filled.Folder,
                        iconBackgroundColor = Color(0xFFEAF3FF),
                        iconColor = Color(0xFF0B5ED7),
                        buttonColor = Color(0xFF0B5ED7),
                        onEntrarClick = onClassDetailsClick
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TurmaCard(
                        nomeTurma = "Desenvolvimento Web",
                        nomeMonitor = "Pedro",
                        dataEncontro = "Sexta, 14h00",
                        icon = Icons.Filled.School,
                        iconBackgroundColor = Color(0xFFEAF9EA),
                        iconColor = Color(0xFF39A935),
                        buttonColor = Color(0xFF39A935),
                        onEntrarClick = onClassDetailsClick
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TurmaCard(
                        nomeTurma = "Desenvolvimento Web",
                        nomeMonitor = "Pedro",
                        dataEncontro = "Sexta, 14h00",
                        icon = Icons.Filled.School,
                        iconBackgroundColor = Color(0xFFEAF9EA),
                        iconColor = Color(0xFF39A935),
                        buttonColor = Color(0xFF39A935),
                        onEntrarClick = onClassDetailsClick
                    )
                }

                FloatingActionButton(
                    onClick = onAddClassClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 18.dp)
                        .size(58.dp),
                    containerColor = Color(0xFF31C853),
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Adicionar turma",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchClassBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFF0B5ED7),
                shape = RoundedCornerShape(13.dp)
            )
            .padding(start = 14.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Buscar",
            tint = Color(0xFF1E1E1E),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Buscar turma",
            fontSize = 14.sp,
            color = Color(0xFFA7ADB7),
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF5278C9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Tune,
                contentDescription = "Filtro",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}