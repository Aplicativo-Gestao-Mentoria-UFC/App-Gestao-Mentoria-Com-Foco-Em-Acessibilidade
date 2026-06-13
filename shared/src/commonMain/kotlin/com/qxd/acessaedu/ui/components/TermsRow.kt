package com.qxd.acessaedu.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxd.acessaedu.ui.theme.DefaultColors

@Composable
fun TermsRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    error: String?
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Eu concordo com os termos e condições",
                color = DefaultColors.TextDark,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (error != null) {
            Text(
                text = error,
                color = DefaultColors.ErrorRed,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 38.dp)
            )
        }
    }
}