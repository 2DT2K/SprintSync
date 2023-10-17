package com.sprintsync.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoIconTopAppBar(title: String, backgroundColor: Color = Color(0xFFFFFFFF)) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Text(
                text = title, style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF21005D)
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.three_dot),
                    contentDescription = ""
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(title: String, backgroundColor: Color = Color(0xFFFFFFFF)) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Text(
                text = title, style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF21005D)
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.avataricon),
                    contentDescription = ""
                )
            }
        },
        actions = {
            Surface(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0x99CDCDCD),
                        shape = RoundedCornerShape(size = 100.dp)
                    ),
                color = Color.Transparent
            )
            {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = ""
                    )
                }
            }
        }
    )
}