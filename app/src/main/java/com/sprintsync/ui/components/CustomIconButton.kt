package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomIconButton(iconID: Int, content: String, backgroundColor: Long, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.Start
        ),
    ) {
        Row(
            modifier = Modifier.background(
                color = Color(0x4FF3FFFE),
                shape = RoundedCornerShape(size = 10.dp)
            )
        ) {
            Image(modifier=Modifier.padding(10.dp),painter = painterResource(id = iconID), contentDescription = "")
        }
        Text(text = content, style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFFFFFFF)
        ))
    }

}

