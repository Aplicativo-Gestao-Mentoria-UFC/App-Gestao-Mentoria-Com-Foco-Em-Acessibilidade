package com.qxd.acessaedu.core.presentation.components

import acessaedu.shared.generated.resources.Res
import acessaedu.shared.generated.resources.logo_acessaedu
import acessaedu.shared.generated.resources.header_img
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.ui.theme.DefaultColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.VolumeUp

enum class MainTab(
    val label: String,
    val icon: ImageVector
) {
    HOME("HOME", Icons.Filled.Home),
    TURMAS("TURMAS", Icons.Filled.School),
    CALENDARIO("CALENDÁRIO", Icons.Filled.CalendarMonth),
    MATERIAIS("MATERIAIS", Icons.Filled.Folder),
    PERFIL("PERFIL", Icons.Filled.Person)
}

@Composable
fun AcessaEduScaffold(
    selectedTab: MainTab,
    onTabClick: (MainTab) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    var isAccessibilityOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultColors.DarkBlue)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Top + WindowInsetsSides.Horizontal
                )
            )
    ) {
        AppHeader()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 28.dp,
                        topEnd = 28.dp
                    )
                )
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            content()
        }

        AccessibilityPanel(
            isOpen = isAccessibilityOpen,
            onToggleClick = {
                isAccessibilityOpen = !isAccessibilityOpen
            }
        )

        BottomNavigationBar(
            selectedTab = selectedTab,
            onTabClick = onTabClick
        )
    }
}
@Composable
private fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.header_img),
            contentDescription = "Header AcessaEdu",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(Res.drawable.logo_acessaedu),
                contentDescription = "Logo AcessaEdu",
                modifier = Modifier
                    .width(165.dp)
                    .height(70.dp),
                contentScale = ContentScale.Fit
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificações",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "G",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DefaultColors.DarkBlue
                    )
                }
            }
        }
    }
}
@Composable
private fun BottomNavigationBar(
    selectedTab: MainTab,
    onTabClick: (MainTab) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E7EB)
                )
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MainTab.entries.forEach { tab ->
                BottomNavItem(
                    tab = tab,
                    selected = selectedTab == tab,
                    onClick = {
                        onTabClick(tab)
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color = if (selected) {
        Color(0xFF0B5ED7)
    } else {
        Color(0xFF666666)
    }

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = tab.icon,
            contentDescription = tab.label,
            modifier = Modifier.size(28.dp),
            tint = color
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = tab.label,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = color
        )
    }
}

@Composable
private fun AccessibilityPanel(
    isOpen: Boolean,
    onToggleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isOpen) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 26.dp,
                            topEnd = 26.dp
                        )
                    )
                    .background(Color(0xFFF7F7F7))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD5D5D5),
                        shape = RoundedCornerShape(
                            topStart = 26.dp,
                            topEnd = 26.dp
                        )
                    )
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 12.dp,
                        bottom = 16.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Acessibilidade rápida:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF263244)
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Fechar acessibilidade",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onToggleClick()
                            },
                        tint = Color(0xFF0B5ED7)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AccessibilityQuickButton(
                        icon = Icons.Filled.TextFields,
                        label = "Aumentar\nFonte",
                        iconBackground = Color(0xFFDCEEFF),
                        iconColor = Color(0xFF1D5FBF),
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    )

                    AccessibilityQuickButton(
                        icon = Icons.Filled.Accessibility,
                        label = "Libras",
                        iconBackground = Color(0xFFDFF7DD),
                        iconColor = Color(0xFF2BA84A),
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    )

                    AccessibilityQuickButton(
                        icon = Icons.Filled.VolumeUp,
                        label = "Leitura em\nvoz alta",
                        iconBackground = Color(0xFFDCEEFF),
                        iconColor = Color(0xFF0B5ED7),
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .width(102.dp)
                    .height(18.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 12.dp
                        )
                    )
                    .background(Color(0xFF0B5ED7))
                    .clickable {
                        onToggleClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Abrir acessibilidade",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

@Composable
private fun AccessibilityQuickButton(
    icon: ImageVector,
    label: String,
    iconBackground: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(46.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFC8C8C8),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(21.dp),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = label,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF263244),
            lineHeight = 10.sp
        )
    }
}