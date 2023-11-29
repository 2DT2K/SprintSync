package com.sprintsync.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun TwoIconTopAppBar(
    title: String,
    backgroundColor: Color = Color(0xFFFFFFFF),
    navigationOnclick: () -> Unit,
    threeDotContent: @Composable () -> Unit = {}
) {
    var isThreeDotOpen by remember {mutableStateOf(false)}
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = navigationOnclick) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = {
                isThreeDotOpen = !isThreeDotOpen
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.three_dot),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            if (isThreeDotOpen) {
                threeDotContent()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarTopAppBar(
    title: String,
    backgroundColor: Color = Color(0xFFFFFFFF),
    turnAvatar: Boolean = true
) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            if (turnAvatar) {
                IconButton(
                    onClick = { /*TODO*/ },
                    Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.avataricon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            } else null
        },
        actions = {
            Surface(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0x99CDCDCD),
                        shape = RoundedCornerShape(size = 20.dp)
                    ),
                color = Color.Transparent
            )
            {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    )
}