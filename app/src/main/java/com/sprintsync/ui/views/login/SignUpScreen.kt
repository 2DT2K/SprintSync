package com.sprintsync.ui.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomTextField
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 25.dp, end = 25.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.65f), contentAlignment = Alignment.TopCenter
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
                        text = "Create an account ",
                        style = TextStyle(
                            fontSize = 25.sp
                        ),
                    )
                    Text(
                        text = "Join us today !",
                        style = TextStyle(
                            fontSize = 14.sp
                        ),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CustomTextField(
                    label = "Email",
                    placeholder = "Enter Your Email",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                            contentDescription = "email icon"
                        )
                    }
                )
                CustomTextField(
                    label = "Phone number",
                    placeholder = "Enter Your Phone Number",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                            contentDescription = "phone icon"
                        )
                    }
                )
                CustomTextField(
                    type = "hidden",
                    label = "Password",
                    placeholder = "Please Enter Your Password",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                            contentDescription = "password icon"
                        )
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(type = "filled", text = "Sign Up", modifier = Modifier.fillMaxWidth())
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .weight(1f)
                            .background(Color.Gray)
                    ) {}
                    ClickableText(
                        text = AnnotatedString("Or With"),
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
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    CustomButton(type = "outlined", text = "Google", modifier = Modifier.weight(1f))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.25f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account?")
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF160062),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SprintSyncTheme {
        SignUpScreen(Modifier)
    }
}
