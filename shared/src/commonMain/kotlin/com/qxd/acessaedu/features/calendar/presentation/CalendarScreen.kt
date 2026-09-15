package com.qxd.acessaedu.features.calendar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.core.presentation.components.AcessaEduScaffold
import com.qxd.acessaedu.core.presentation.components.MainTab
import com.qxd.acessaedu.features.calendar.presentation.components.CalendarCard
import com.qxd.acessaedu.features.classes.presentation.components.TurmaCard
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun CalendarScreen(
    onTabClick: (MainTab) -> Unit
) {
    val today = remember {
        Clock.System.todayIn(TimeZone.currentSystemDefault())
    }

    val selectedDate = remember {
        mutableStateOf(today)
    }

    val meetings = remember(today) {
        listOf(
            CalendarMeetingUi(
                id = "1",
                turma = "Banco de dados",
                monitor = "Pedro Perikles",
                date = today,
                dateLabel = "Hoje, 14h",
                icon = Icons.Filled.Folder,
                iconBackgroundColor = Color(0xFFEAF3FF),
                iconColor = Color(0xFF0B5ED7),
                buttonColor = Color(0xFF0B5ED7)
            ),
            CalendarMeetingUi(
                id = "2",
                turma = "Desenvolvimento Web",
                monitor = "Pedro",
                date = LocalDate(
                    year = today.year,
                    month = today.month,
                    dayOfMonth = 19
                ),
                dateLabel = "Dia 19, 14h",
                icon = Icons.Filled.School,
                iconBackgroundColor = Color(0xFFEAF9EA),
                iconColor = Color(0xFF39A935),
                buttonColor = Color(0xFF39A935)
            )
        )
    }

    val meetingsByDate = remember(meetings) {
        meetings.groupBy { meeting -> meeting.date }
    }

    val selectedMeetings = meetingsByDate[selectedDate.value].orEmpty()

    AcessaEduScaffold(
        selectedTab = MainTab.CALENDARIO,
        onTabClick = onTabClick
    ) {
        Text(
            text = "Calendário",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF263244)
        )

        Text(
            text = "Veja seus encontros",
            fontSize = 12.sp,
            color = Color(0xFF4F5563)
        )

        Spacer(modifier = Modifier.height(18.dp))

        CalendarCard(
            selectedDate = selectedDate.value,
            eventDates = meetingsByDate.keys,
            onDateSelected = { date ->
                selectedDate.value = date
            }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Encontros:",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF263244)
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (selectedMeetings.isEmpty()) {
            EmptyMeetingsCard()
        } else {
            selectedMeetings.forEachIndexed { index, meeting ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                TurmaCard(
                    nomeTurma = meeting.turma,
                    nomeMonitor = meeting.monitor,
                    dataEncontro = meeting.dateLabel,
                    icon = meeting.icon,
                    iconBackgroundColor = meeting.iconBackgroundColor,
                    iconColor = meeting.iconColor,
                    buttonColor = meeting.buttonColor
                )
            }
        }
    }
}

@Composable
private fun EmptyMeetingsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFD6E0EF),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Nenhum encontro marcado para este dia.",
            fontSize = 13.sp,
            color = Color(0xFF7B7F89)
        )
    }
}

private data class CalendarMeetingUi(
    val id: String,
    val turma: String,
    val monitor: String,
    val date: LocalDate,
    val dateLabel: String,
    val icon: ImageVector,
    val iconBackgroundColor: Color,
    val iconColor: Color,
    val buttonColor: Color
)
