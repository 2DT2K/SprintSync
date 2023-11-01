package com.sprintsync.ui.components.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.ccpDefaultColors
import com.sprintsync.ui.components.CountryCodePicker
import com.sprintsync.ui.theme.Purple40


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
