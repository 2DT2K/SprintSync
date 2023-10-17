package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommentTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        1.dp,
                        Color(0xFF969EBD),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            innerTextField()
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun CustomTextFieldPreview() {
//    CommentTextField(
//        placeholder = "Add a comment",
//        value = "",
//        onValueChange = {},
//        modifier = Modifier
//            .weight(1f)
//    )
//}