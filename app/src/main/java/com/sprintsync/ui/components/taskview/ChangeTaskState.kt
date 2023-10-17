package com.sprintsync.ui.components.taskview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeTaskStateButton(taskState: String) {
    Button(
        onClick = { /*TODO*/ },
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
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
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = taskState,
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
}

@Preview(showBackground = true)
@Composable
fun ChangeStatePreview() {
    ChangeTaskStateButton(taskState = "IN PROGRESS")
}
