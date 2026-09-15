package com.qxd.acessaedu.features.classes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClassDetailsScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Top + WindowInsetsSides.Horizontal
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp)
            .padding(top = 18.dp, bottom = 32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(31.dp)
                    .clickable { onBackClick() },
                tint = ClassDetailsColors.TextDark
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Detalhes da turma",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = ClassDetailsColors.TextDark
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        ClassHeaderCard()

        Spacer(modifier = Modifier.height(14.dp))

        NextMeetingCard()

        Spacer(modifier = Modifier.height(16.dp))

        ContentTabsCard()
    }
}

@Composable
private fun ClassHeaderCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = ClassDetailsColors.LightBlueBorder,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(ClassDetailsColors.PrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Description,
                contentDescription = "Ícone da turma",
                modifier = Modifier.size(42.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Banco de dados",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ClassDetailsColors.TextDark
            )

            Spacer(modifier = Modifier.height(6.dp))

            InfoLine(
                icon = Icons.Filled.Person,
                text = "Professor: Geraldo Oliveira"
            )

            Spacer(modifier = Modifier.height(4.dp))

            InfoLine(
                icon = Icons.Filled.Person,
                text = "Monitor: João Batista"
            )

            Spacer(modifier = Modifier.height(7.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(ClassDetailsColors.GreenBadge)
                    .padding(horizontal = 13.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Turma ativa",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClassDetailsColors.GreenText
                )
            }
        }
    }
}

@Composable
private fun NextMeetingCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = ClassDetailsColors.Border,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(ClassDetailsColors.CalendarBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Calendário",
                    modifier = Modifier.size(40.dp),
                    tint = ClassDetailsColors.PrimaryBlue
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(ClassDetailsColors.BlueBadge)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Próximo encontro",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ClassDetailsColors.PrimaryBlue
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Banco de dados",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClassDetailsColors.TextDark
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccessTime,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = ClassDetailsColors.PrimaryBlue
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Hoje, 14h00",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ClassDetailsColors.PrimaryBlue
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                InfoLine(
                    icon = Icons.Filled.Person,
                    text = "Monitor: João Batista"
                )
            }
        }

        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            ListenButton()
        }
    }
}

@Composable
private fun ListenButton() {
    Row(
        modifier = Modifier
            .height(35.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(ClassDetailsColors.PrimaryBlue)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.VolumeUp,
            contentDescription = "Ouvir detalhes",
            modifier = Modifier
                .size(14.dp)
                .padding(top = 10.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "Ouvir detalhes",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun ContentTabsCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = ClassDetailsColors.Border,
                shape = RoundedCornerShape(14.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabItem(
                text = "Conteúdos",
                selected = true,
                modifier = Modifier.weight(1f)
            )

            TabItem(
                text = "Atividades",
                selected = false,
                modifier = Modifier.weight(1f)
            )

            TabItem(
                text = "Encontros",
                selected = false,
                modifier = Modifier.weight(1f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ClassDetailsColors.Border)
        )

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            ContentItem(
                icon = Icons.Filled.PictureAsPdf,
                iconBackground = ClassDetailsColors.PdfBackground,
                iconColor = ClassDetailsColors.Red,
                title = "Aula 05 - Slides da aula",
                type = "PDF",
                typeBackground = ClassDetailsColors.PdfBadge,
                typeColor = ClassDetailsColors.Red,
                date = "Adicionada hoje"
            )

            Spacer(modifier = Modifier.height(7.dp))

            ContentItem(
                icon = Icons.Filled.Videocam,
                iconBackground = ClassDetailsColors.VideoBackground,
                iconColor = ClassDetailsColors.Purple,
                title = "Gravação da monitoria",
                type = "Vídeo",
                typeBackground = ClassDetailsColors.VideoBadge,
                typeColor = ClassDetailsColors.PurpleDark,
                date = "Ontem"
            )
        }
    }
}

@Composable
private fun TabItem(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.height(46.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = ClassDetailsColors.PrimaryBlue
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .width(86.dp)
                .height(3.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(
                    if (selected) {
                        ClassDetailsColors.PrimaryBlue
                    } else {
                        Color.Transparent
                    }
                )
        )
    }
}

@Composable
private fun ContentItem(
    icon: ImageVector,
    iconBackground: Color,
    iconColor: Color,
    title: String,
    type: String,
    typeBackground: Color,
    typeColor: Color,
    date: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = ClassDetailsColors.Border,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(23.dp),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ClassDetailsColors.TextDark,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(typeBackground)
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = type,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = typeColor
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = ClassDetailsColors.TextMuted
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = ClassDetailsColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun InfoLine(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(13.dp),
            tint = ClassDetailsColors.TextMuted
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            fontSize = 12.sp,
            color = ClassDetailsColors.TextMuted
        )
    }
}

private object ClassDetailsColors {
    val TextDark = Color(0xFF202340)
    val TextMuted = Color(0xFF777777)
    val PrimaryBlue = Color(0xFF0057D9)
    val LightBlueBorder = Color(0xFF8DB7FF)
    val Border = Color(0xFFCFCFCF)

    val CalendarBackground = Color(0xFFB9F3F4)
    val BlueBadge = Color(0xFFC8DDFF)

    val GreenBadge = Color(0xFF95EC8E)
    val GreenText = Color(0xFF007C22)

    val PdfBackground = Color(0xFFFFE3E3)
    val PdfBadge = Color(0xFFFFD3D3)
    val Red = Color(0xFFFF1F1F)

    val VideoBackground = Color(0xFFE3D5FF)
    val VideoBadge = Color(0xFFE6C8FF)
    val Purple = Color(0xFF8B63CE)
    val PurpleDark = Color(0xFF6F259B)
}
