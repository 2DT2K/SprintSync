package com.sprintsync.ui.components.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomIconButton

@Composable
fun HomePageViews() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Views", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF1D192B)
                )
            )
            Row(
                modifier = Modifier.background(
                    color = Color(0xFFF3EDF7),
                    shape =
                    RoundedCornerShape(8.dp)
                )
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(id = R.drawable.three_dot),
                        contentDescription = ""
                    )
                }
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomIconButton(
                    iconID = R.drawable.folder,
                    content = "Project",
                    backgroundColor = 0xFFFF71D1,
                    modifier = Modifier
                        .weight(1f)
                        .shadow(
                            elevation = 8.dp,
                            spotColor = Color(0x59FF6BCF),
                            ambientColor = Color(0x59FF6BCF)
                        )
                )
                CustomIconButton(
                    iconID = R.drawable.task,
                    content = "Task",
                    backgroundColor = 0xFFD47AFE,
                    modifier = Modifier
                        .weight(1f)
                        .shadow(
                            elevation = 8.dp,
                            spotColor = Color(0x59D272FF),
                            ambientColor = Color(0x59D272FF)
                        )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomIconButton(
                    iconID = R.drawable.timelapse,
                    content = "Timeline",
                    backgroundColor = 0xFFFF829E,
                    modifier = Modifier
                        .weight(1f)
                        .shadow(
                            elevation = 8.dp,
                            spotColor = Color(0x59FF829E),
                            ambientColor = Color(0x59FF829E)
                        )
                )
                CustomIconButton(
                    iconID = R.drawable.calendar,
                    content = "Calendar",
                    backgroundColor = 0xFFFFA874,
                    modifier = Modifier
                        .weight(1f)
                        .shadow(
                            elevation = 8.dp,
                            spotColor = Color(0x59FFA874),
                            ambientColor = Color(0x59FFA874)
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuViewsPreview() {
    HomePageViews()
}