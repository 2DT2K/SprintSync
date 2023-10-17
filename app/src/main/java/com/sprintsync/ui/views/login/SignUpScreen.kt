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
import com.sprintsync.ui.components.CustomTextField
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
                Column {
                    Text(
                        text = "Create an account ",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Purple40,
                            fontWeight = FontWeight(800),
                        ),
                    )
                    Text(
                        text = "Join us today !",
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
                    placeholder = "Enter Your Email",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                )

                SelectCountryWithCountryCode()

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
                    },
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
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
                        text = "Sign Up",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .weight(1f)
                                .background(Purple40)
                        ) {}
                        ClickableText(
                            text = AnnotatedString("Or With"),
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
                            .fillMaxWidth(),
                    ) {
                        CustomButton(
                            type = "outlined",
                            text = "Google",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
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
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier.clickable(interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = true,
                            radius = 250.dp
                        ),
                        onClick = {}
                    ),
                    text = "Login",
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

@Composable
fun SelectCountryWithCountryCode() {
    val context = LocalContext.current
//    val phonecode = getLibCountries().single { it.countryCode == "+84" }
    var phoneCode by remember { mutableStateOf("+84") }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    var defaultLang by rememberSaveable { mutableStateOf("Vietnam") }
    var isValidPhone by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CountryCodePicker(
            pickedCountry = {
                phoneCode = "it.countryPhoneCode"
                defaultLang = "it.countryCode"
            },
            defaultCountry = CountryData("fdsa", "fdsa", "fdafds"),
            error = !isValidPhone,
            text = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            searchFieldPlaceHolderTextStyle = MaterialTheme.typography.bodyMedium,
            searchFieldTextStyle = MaterialTheme.typography.bodyMedium,
            phonenumbertextstyle = MaterialTheme.typography.bodyMedium,
            countrytextstyle = MaterialTheme.typography.bodyMedium,
            countrycodetextstyle = MaterialTheme.typography.bodyMedium,
            showErrorText = true,
            showCountryCodeInDIalog = true,
            showDropDownAfterFlag = false,
            textFieldShapeCornerRadiusInPercentage = 16,
            searchFieldShapeCornerRadiusInPercentage = 16,
            appbartitleStyle = MaterialTheme.typography.titleLarge,
            countryItemBgShape = RoundedCornerShape(5.dp),
            showCountryFlag = true,
            showCountryCode = true,
            isEnabled = true,
            colors = ccpDefaultColors(
                primaryColor = MaterialTheme.colorScheme.primary,
                errorColor = MaterialTheme.colorScheme.error,
                backgroundColor = MaterialTheme.colorScheme.background,
                surfaceColor = MaterialTheme.colorScheme.surface,
                outlineColor = Purple40,
                disabledOutlineColor = Purple40,
                unfocusedOutlineColor = Purple40,
                textColor = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                cursorColor = MaterialTheme.colorScheme.primary,
                topAppBarColor = MaterialTheme.colorScheme.surface,
                countryItemBgColor = MaterialTheme.colorScheme.surface,
                searchFieldBgColor = MaterialTheme.colorScheme.surface,
                dialogNavIconColor = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                dropDownIconTint = MaterialTheme.colorScheme.onBackground.copy(0.7f)
            ),
        )

        val fullPhoneNumber = "$phoneCode${phoneNumber.value}"
//        val checkPhoneNumber = checkPhoneNumber(
//            phone = phoneNumber.value,
//            fullPhoneNumber = fullPhoneNumber,
//            countryCode = defaultLang
//        )
//        OutlinedButton(
//            onClick = {
//                isValidPhone = checkPhoneNumber
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//                .height(
//                    50.dp
//                )
//        ) {
//            Text(text = "Phone Verify")
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SprintSyncTheme {
        SignUpScreen(Modifier)
    }
}
