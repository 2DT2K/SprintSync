package com.sprintsync.ui.views.auth

import Indicator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.ui.components.auth.PromptRow
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing

@Composable
fun VerifyAccount(navController: NavController? = null) {
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    painter = painterResource(id = R.drawable.verified_amico),
                    contentDescription = null
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    modifier = Modifier.width(300.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your account \n" +
                                "is being verified",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                    )
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Indicator(
                    size = 48.dp,
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = MaterialTheme.spacing.default,
                )

                PromptRow(
                    normalText = "Don’t receive mail?",
                    highlightedText = "Re-send",
                    highlightColor = MaterialTheme.colorScheme.error,
                    onClick = { }
                )

                Button(
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(40),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = { }
                ) {
                    Text(
                        text = "Sign Out",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun VerifyAccountPreview() {
    SprintSyncTheme {
        VerifyAccount()
    }
}