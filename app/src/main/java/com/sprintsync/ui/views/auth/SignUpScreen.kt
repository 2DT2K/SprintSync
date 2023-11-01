package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.auth.Email
import com.sprintsync.ui.components.auth.Password
import com.sprintsync.ui.components.auth.SignUpButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f), contentAlignment = Alignment.Center
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
                Title(mainTitle = "Create an account ", description = "Join us today !")
            }

            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Email()

                Password()

                Password("confirm")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.6f))
                Surface(modifier = Modifier.weight(1f)) {
                    SignUpButtonGroup()
                }
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
