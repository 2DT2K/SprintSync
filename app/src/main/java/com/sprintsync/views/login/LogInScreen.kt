package com.sprintsync.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.components.CustomButton
import com.sprintsync.components.CustomTextField
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun LogInScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "LOGO",
                modifier = modifier
                    .requiredWidth(width = 144.dp)
                    .requiredHeight(height = 139.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {
            Column {
                Text(
                    text = "Hi, Welcome Back! ",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                )
                Text(
                    text = "Hello again, we missed you <3",
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                )
            }
        }
        Column(modifier = Modifier
            .weight(1f)
        ) {
            CustomTextField(
                label = "Email",
                placeholder = "Please Enter Your Email",
                modifier = Modifier.fillMaxWidth()
            )
            CustomTextField(
                label = "Password",
                placeholder = "Please Enter Your Password",
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd
            ) {
                Text(text = "Forgot Password")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(type = "filled", text = "Login", modifier = Modifier.fillMaxWidth())
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .weight(1f)
                        .background(Color.Gray)
                ) {}
                ClickableText(
                    text = AnnotatedString("Show replies"),
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                )
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .weight(1f)
                        .background(Color.Gray)
                ) {}
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomButton(type = "filled", text = "Login", modifier = Modifier.weight(1f))
                CustomButton(type = "filled", text = "Login", modifier = Modifier.weight(1f))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?")
            Text(text = "Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    SprintSyncTheme {
        LogInScreen(Modifier)
    }
}
