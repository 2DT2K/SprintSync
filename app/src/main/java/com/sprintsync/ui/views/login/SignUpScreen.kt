package com.sprintsync.ui.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.ccpDefaultColors
import com.sprintsync.R
import com.sprintsync.ui.components.CountryCodePicker
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.CustomTextField
import com.sprintsync.ui.components.authentication.Email
import com.sprintsync.ui.components.authentication.Password
import com.sprintsync.ui.components.authentication.SelectCountryWithCountryCode
import com.sprintsync.ui.components.authentication.SignUpButtonGroup
import com.sprintsync.ui.components.authentication.Title
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
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
                    .weight(0.5f), contentAlignment = Alignment.Center
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

                SelectCountryWithCountryCode()

                Password()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignUpButtonGroup()
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.25f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = "Already have an account ?",
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
                    text = "Login",
                    color = Purple40,
                    fontWeight = FontWeight.Bold
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
