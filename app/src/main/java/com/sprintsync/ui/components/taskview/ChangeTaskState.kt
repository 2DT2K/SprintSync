package com.sprintsync.ui.components.taskview

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeTaskStateButton(taskState: String) {

    var taskStateTest by remember {
        mutableStateOf("IN PROGRESS")
    }
    var expanded by remember { mutableStateOf(false) }
    val menuList = listOf<String>("To do", "In Progress", "Stuck", "Review", "Done")
    Box {
        Button(
            onClick = { expanded = !expanded },
            contentPadding = PaddingValues(start = 4.dp, end = 12.dp),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.defaultMinSize(minHeight = 46.dp),
            colors = buttonColors(
                containerColor = Color(0xFFEADDFF),
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier.padding(1.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = taskStateTest,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF1D192B),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentWidth()
        ) {
            menuList.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        taskStateTest = it
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeStatePreview() {
    ChangeTaskStateButton(taskState = "IN PROGRESS")
}
