package com.sprintsync.ui.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomTextField
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun LogInScreen(modifier: Modifier = Modifier) {
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize()
                    .weight(0.75f), contentAlignment = Alignment.TopCenter
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
                            fontSize = 24.sp,
                            color = Purple40,
                            fontWeight = FontWeight(800),
                        ),
                    )
                    Text(
                        text = "Hello again, we missed you <3",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Grey40
                        ),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CustomTextField(
                    label = "Email",
                    placeholder = "Please Enter Your Email",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                )

                CustomTextField(
                    type = "hidden",
                    label = "Password",
                    placeholder = "Please Enter Your Password",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.key),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.visibility),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                )
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd
                ) {
                    Text(text = "Forgot Password", color = Red80)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomButton(
                        type = "filled",
                        text = "Login",
                        surfaceModifier = Modifier.fillMaxWidth()
                    )
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .weight(1f)
                                .background(Purple40)
                        ) {}
                        ClickableText(
                            text = AnnotatedString("Show replies"),
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                color = Purple40
                            ),
                        )
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .weight(1f)
                                .background(Purple40)
                        ) {}
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        CustomButton(
                            type = "outlined",
                            text = "Google",
                            modifier = Modifier.fillMaxWidth(),
                            surfaceModifier = Modifier
                                .weight(1f),
                            icon = {
                                Image(
                                    painter = painterResource(id = R.drawable.google),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        )

                        CustomButton(
                            type = "outlined",
                            text = "Phone",
                            modifier = Modifier.fillMaxWidth(),
                            surfaceModifier = Modifier
                                .weight(1f),
                            icon = {
                                Image(
                                    painter = painterResource(id = R.drawable.phone),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account?")
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF160062),
                    )
                )
            }
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
