package com.sprintsync.ui.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ripple.rememberRipple
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
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.CustomTextField
import com.sprintsync.ui.components.authentication.Email
import com.sprintsync.ui.components.authentication.Password
import com.sprintsync.ui.components.authentication.PromptRow
import com.sprintsync.ui.components.authentication.SignInButtonGroup
import com.sprintsync.ui.components.authentication.Title
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Grey80
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
                    .weight(0.7f),
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
                    .weight(0.3f)
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
                    PromptRow(
                        normalText = "Forgot Password?",
                        highlightedText = "Reset",
                        highlightColor = Red80,
                        onClick = {},
                        modifier = Modifier.height(20.dp)
                    )
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
