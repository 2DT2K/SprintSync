package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.theme.Grey60
import com.sprintsync.ui.theme.Grey80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun StoryPoint(point: Int, color: Color = Grey80) {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        CustomText(
            text = point.toString(),
            fontSize = 10.sp,
            fontWeight = FontWeight(400),
        )
    }
}

@Preview
@Composable
fun StoryPointPreview() {
    SprintSyncTheme {
        StoryPoint(11000)
    }
}