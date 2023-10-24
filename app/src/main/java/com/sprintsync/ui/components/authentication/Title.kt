package com.sprintsync.ui.components.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40

@Composable
fun Title(mainTitle: String, description: String) {
    Surface {
        Column {
            Text(
                text = mainTitle,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Purple40,
                    fontWeight = FontWeight(800),
                ),
            )
            Text(
                text = description,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Grey40
                ),
            )
        }
    }
}