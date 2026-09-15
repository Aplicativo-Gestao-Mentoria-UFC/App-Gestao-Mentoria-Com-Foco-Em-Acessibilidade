package com.qxd.acessaedu.features.calendar.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.YearMonth

@Composable
fun CalendarCard(
    selectedDate: LocalDate,
    eventDates: Set<LocalDate>,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val daysOfWeek = remember { daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY) }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    val coroutineScope = rememberCoroutineScope()

    val visibleMonth by remember {
        derivedStateOf {
            state.firstVisibleMonth.yearMonth
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFD8D8D8),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        CalendarMonthHeader(
            visibleMonth = visibleMonth,
            onPreviousClick = {
                coroutineScope.launch {
                    state.animateScrollToMonth(visibleMonth.minusMonths(1))
                }
            },
            onNextClick = {
                coroutineScope.launch {
                    state.animateScrollToMonth(visibleMonth.plusMonths(1))
                }
            }
        )

        Spacer(modifier = Modifier.height(6.dp))

        HorizontalCalendar(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            userScrollEnabled = true,
            monthHeader = {
                WeekDaysHeader(daysOfWeek = daysOfWeek)
            },
            dayContent = { day ->
                CalendarDayContent(
                    day = day,
                    selected = day.date == selectedDate,
                    hasEvent = eventDates.contains(day.date),
                    onClick = {
                        onDateSelected(day.date)
                    }
                )
            }
        )
    }
}

@Composable
private fun CalendarMonthHeader(
    visibleMonth: YearMonth,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Mês anterior",
            tint = Color(0xFF0B5ED7),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onPreviousClick()
                }
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF14108A))
                .padding(horizontal = 12.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = visibleMonth.monthNamePt(),
                fontSize = 11.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Próximo mês",
            tint = Color(0xFF0B5ED7),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onNextClick()
                }
        )
    }
}

@Composable
private fun WeekDaysHeader(
    daysOfWeek: List<DayOfWeek>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.shortNamePt(),
                modifier = Modifier.weight(1f),
                fontSize = 10.sp,
                color = if (dayOfWeek.isWeekend()) {
                    Color(0xFF0B8EED)
                } else {
                    Color(0xFFB5B5B5)
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CalendarDayContent(
    day: CalendarDay,
    selected: Boolean,
    hasEvent: Boolean,
    onClick: () -> Unit
) {
    val isMonthDate = day.position == DayPosition.MonthDate

    val backgroundColor = when {
        selected -> Color(0xFF14108A)
        hasEvent && isMonthDate -> Color(0xFF0B5ED7)
        else -> Color.Transparent
    }

    val textColor = when {
        selected -> Color.White
        hasEvent && isMonthDate -> Color.White
        !isMonthDate -> Color(0xFFC8C8C8)
        day.date.dayOfWeek.isWeekend() -> Color(0xFF0B8EED)
        else -> Color(0xFF9A9A9A)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable(
                enabled = isMonthDate,
                onClick = {
                    onClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.day.toString(),
            fontSize = 10.sp,
            fontWeight = if (selected || hasEvent) FontWeight.Bold else FontWeight.Normal,
            color = textColor
        )
    }
}

private fun DayOfWeek.shortNamePt(): String {
    return when (this) {
        DayOfWeek.MONDAY -> "seg."
        DayOfWeek.TUESDAY -> "ter."
        DayOfWeek.WEDNESDAY -> "qua."
        DayOfWeek.THURSDAY -> "qui."
        DayOfWeek.FRIDAY -> "sex."
        DayOfWeek.SATURDAY -> "sáb."
        DayOfWeek.SUNDAY -> "dom."
    }
}

private fun DayOfWeek.isWeekend(): Boolean {
    return this == DayOfWeek.SATURDAY || this == DayOfWeek.SUNDAY
}

private fun YearMonth.monthNamePt(): String {
    return when (this.month) {
        Month.JANUARY -> "janeiro"
        Month.FEBRUARY -> "fevereiro"
        Month.MARCH -> "março"
        Month.APRIL -> "abril"
        Month.MAY -> "maio"
        Month.JUNE -> "junho"
        Month.JULY -> "julho"
        Month.AUGUST -> "agosto"
        Month.SEPTEMBER -> "setembro"
        Month.OCTOBER -> "outubro"
        Month.NOVEMBER -> "novembro"
        Month.DECEMBER -> "dezembro"
    }
}