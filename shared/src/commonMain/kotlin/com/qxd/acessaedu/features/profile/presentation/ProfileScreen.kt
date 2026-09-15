package com.qxd.acessaedu.features.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
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
import com.qxd.acessaedu.core.presentation.components.AcessaEduScaffold
import com.qxd.acessaedu.core.presentation.components.MainTab

@Composable
fun ProfileScreen(
    onTabClick: (MainTab) -> Unit,
    onLogoutClick: () -> Unit
) {
    AcessaEduScaffold(
        selectedTab = MainTab.PERFIL,
        onTabClick = onTabClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp)
                .padding(top = 14.dp, bottom = 25.dp)
        ) {
            Text(
                text = "Perfil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileColors.TextDark
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Gerencie seus dados e preferências.",
                fontSize = 12.sp,
                color = ProfileColors.TextMuted
            )

            Spacer(modifier = Modifier.height(12.dp))

            StudentProfileCard()

            Spacer(modifier = Modifier.height(14.dp))

            SectionTitle(text = "Minha conta:")

            Spacer(modifier = Modifier.height(6.dp))

            ProfileMenuCard()

            Spacer(modifier = Modifier.height(14.dp))

            SectionTitle(text = "Acessibilidade:")

            Spacer(modifier = Modifier.height(8.dp))

            AccessibilityPreferenceCard()

            Spacer(modifier = Modifier.height(82.dp))

            LogoutButton(
                onClick = {
                    onLogoutClick()
                }
            )
        }
    }
}

@Composable
private fun StudentProfileCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 178.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(ProfileColors.ProfileCardBackground)
            .border(
                width = 1.dp,
                color = ProfileColors.Border,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            StudentAvatar()

            Spacer(modifier = Modifier.width(18.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Geraldo O. Pinho",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileColors.TextDark
                )

                Spacer(modifier = Modifier.height(5.dp))

                StudentBadge()

                Spacer(modifier = Modifier.height(12.dp))

                StudentInfoRow(
                    icon = Icons.Filled.School,
                    text = "Matrícula: 888888"
                )

                Spacer(modifier = Modifier.height(6.dp))

                StudentInfoRow(
                    icon = Icons.Filled.Phone,
                    text = "Telefone: (88)99999-9999"
                )

                Spacer(modifier = Modifier.height(6.dp))

                StudentInfoRow(
                    icon = Icons.Filled.Email,
                    text = "E-mail: geraldoual@gmail.com"
                )

                Spacer(modifier = Modifier.height(16.dp))

                EditProfileButton(
                    onClick = {
                        // TODO: navegar para edição de perfil
                    }
                )
            }
        }
    }
}

@Composable
private fun StudentAvatar() {
    Box(
        modifier = Modifier
            .size(86.dp)
            .clip(CircleShape)
            .background(ProfileColors.AvatarBackground),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Foto do estudante",
            modifier = Modifier.size(52.dp),
            tint = Color.White
        )
    }
}

@Composable
private fun StudentBadge() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(ProfileColors.BadgeBackground)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Estudante",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = ProfileColors.PrimaryBlue
        )
    }
}

@Composable
private fun StudentInfoRow(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = ProfileColors.IconMuted
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            fontSize = 11.sp,
            color = ProfileColors.TextMuted
        )
    }
}

@Composable
private fun EditProfileButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .widthIn(min = 116.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = ProfileColors.PrimaryBlue,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Editar perfil",
            modifier = Modifier.size(18.dp),
            tint = ProfileColors.PrimaryBlue
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Editar perfil",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = ProfileColors.PrimaryBlue
        )
    }
}

@Composable
private fun SectionTitle(
    text: String
) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = ProfileColors.TextDark
    )
}

@Composable
private fun ProfileMenuCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = ProfileColors.Border,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        ProfileMenuItem(
            icon = Icons.Filled.Person,
            title = "Dados pessoais",
            onClick = {
                // TODO: navegar para dados pessoais
            }
        )

        ProfileDivider()

        ProfileMenuItem(
            icon = Icons.Filled.Settings,
            title = "Preferências",
            onClick = {
                // TODO: navegar para preferências
            }
        )

        ProfileDivider()

        ProfileMenuItem(
            icon = Icons.Filled.Notifications,
            title = "Notificações",
            onClick = {
                // TODO: navegar para notificações
            }
        )

        ProfileDivider()

        ProfileMenuItem(
            icon = Icons.Filled.Lock,
            title = "Segurança",
            onClick = {
                // TODO: navegar para segurança
            }
        )
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(ProfileColors.IconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(20.dp),
                tint = ProfileColors.PrimaryBlue
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = ProfileColors.TextDark
        )
    }
}

@Composable
private fun ProfileDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(start = 44.dp)
            .background(ProfileColors.Divider)
    )
}

@Composable
private fun AccessibilityPreferenceCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = ProfileColors.Purple,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable {
                // TODO: navegar para preferências de acessibilidade
            }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(ProfileColors.Purple),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Accessibility,
                contentDescription = "Acessibilidade",
                modifier = Modifier.size(22.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Preferências de Acessibilidade",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileColors.TextDark
            )

            Text(
                text = "Fontes, contrastes, libras, cores",
                fontSize = 11.sp,
                color = ProfileColors.TextMuted
            )
        }

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Abrir",
            modifier = Modifier.size(24.dp),
            tint = ProfileColors.IconMuted
        )
    }
}

@Composable
private fun LogoutButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = ProfileColors.Red,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Logout,
            contentDescription = "Sair da conta",
            modifier = Modifier.size(20.dp),
            tint = ProfileColors.Red
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Sair da conta",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = ProfileColors.Red
        )
    }
}

private object ProfileColors {
    val TextDark = Color(0xFF202340)
    val TextMuted = Color(0xFF666666)
    val PrimaryBlue = Color(0xFF0057FF)
    val ProfileCardBackground = Color(0xFFDCE7FA)
    val BadgeBackground = Color(0xFFC7DBFF)
    val AvatarBackground = Color(0xFFB7B2C8)
    val IconMuted = Color(0xFF686868)
    val IconBackground = Color(0xFFEAF2FF)
    val Border = Color(0xFFD0D0D0)
    val Divider = Color(0xFFBDBDBD)
    val Purple = Color(0xFF8B3DFF)
    val Red = Color(0xFFFF1F1F)
}