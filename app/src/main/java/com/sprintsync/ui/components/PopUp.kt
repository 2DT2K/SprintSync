package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sprintsync.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUp(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() }, properties = DialogProperties(
            usePlatformDefaultWidth = false // disable the default size so that we can customize it
        )
    ) {
        var comment by remember {
            mutableStateOf("")
        }
        Box(modifier = Modifier
            .background(Color.Black.copy(alpha = 0.5f))
            .fillMaxSize().clickable { onDismissRequest() }) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .align(Alignment.Center)
            ) {
                Text(text = "Enter tag name")
                TextField(value = comment, onValueChange = {
                    comment = it
                })
            }
        }

    }
}