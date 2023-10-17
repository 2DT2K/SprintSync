package com.sprintsync.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val RowModifier = Modifier.background(
    color = Color(0xFFDCCFE3),
    shape = RoundedCornerShape(size = 3.dp)
) .padding(start = 8.dp, top = 3.dp, end = 8.dp, bottom = 3.dp)

@Composable
fun TaskTag(tagName: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = RowModifier
    ) {
        Text(
            text = tagName,
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF49454F),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTagPreview() {
    TaskTag(tagName = "Login")
}