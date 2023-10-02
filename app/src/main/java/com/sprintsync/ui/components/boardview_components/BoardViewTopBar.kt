package com.sprintsync.ui.components.boardview_components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun BoardViewTopBar() {
    Row(
        modifier = Modifier
            .background(color = Color(0xFFEADDFF))
            .fillMaxWidth()
            .padding(start = 25.dp, top = 8.dp, end = 25.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.back_icon), contentDescription = "")
            }
            Text(
                text = "Board View", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF21005D)
                )
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.three_dot),
                contentDescription = "",
                contentScale = ContentScale.None
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardViewTopBarPreview() {
    BoardViewTopBar()
}