package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.auth.Email
import com.sprintsync.ui.components.auth.Password
import com.sprintsync.ui.components.auth.SignInButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun LogInScreen(modifier: Modifier = Modifier) {
    Surface() {
// TODO: remove padding when we have main scaffold reddy for login and signup
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize()
                    .weight(0.75f),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "LOGO",
                    modifier = modifier
                        .requiredSize(240.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                Title(
                    "Hi, Welcome Back! ",
                    "Hello again, we missed you <3"
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Email()
                Password()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(
                                bounded = true,
                                radius = 250.dp
                            ),
                            onClick = {}
                        ), contentAlignment = Alignment.BottomEnd
                ) {
                    Row {
                        Text(
                            text = "Forgot Password?",
                            color = Grey40
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Reset",
                            color = Red80,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInButtonGroup()
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = "Don't have an account?",
                    color = Grey40
                )

                Spacer(modifier = Modifier.width(5.dp))

                CustomText(
                    modifier = Modifier.clickable(interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = true,
                            radius = 250.dp
                        ),
                        onClick = {}
                    ),
                    text = "Sign Up",
                    color = Purple40,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    SprintSyncTheme {
        LogInScreen(Modifier)
    }
}
