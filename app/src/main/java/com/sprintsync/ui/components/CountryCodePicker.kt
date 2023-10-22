package com.sprintsync.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.simon.xmaterialccp.component.MaterialCodePicker
import com.simon.xmaterialccp.data.CCPColors
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.ccpDefaultColors
import com.sprintsync.R
import com.sprintsync.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CountryCodePicker(
	text: String = "",
	onValueChange: (String) -> Unit,
	modifier: Modifier = Modifier,
	showCountryCode: Boolean = true,
	showCountryFlag: Boolean = true,
	defaultCountry: CountryData,
	pickedCountry: (CountryData) -> Unit,
	error: Boolean = false,
	showErrorText: Boolean = true,
	flagPadding: PaddingValues = PaddingValues(horizontal = 0.dp, vertical = 10.dp),
	countryItemBgShape: RoundedCornerShape = RoundedCornerShape(0.dp),
	phonenumbertextstyle: TextStyle = MaterialTheme.typography.bodyMedium,
	phonehintnumbertextstyle: TextStyle = MaterialTheme.typography.bodyMedium,
	searchFieldPlaceHolderTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
	searchFieldTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
	searchFieldShapeCornerRadiusInPercentage: Int = 30,
	textFieldShapeCornerRadiusInPercentage: Int = 30,
	errorTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
	appbartitleStyle: TextStyle = MaterialTheme.typography.titleLarge,
	countryItemVerticalPadding: Dp = 0.dp,
	countryItemHorizontalPadding: Dp = 0.dp,
	countrytextstyle: TextStyle = MaterialTheme.typography.bodyMedium,
	dialogcountrycodetextstyle: TextStyle = MaterialTheme.typography.bodyMedium,
	showCountryCodeInDIalog: Boolean = true,
	countrycodetextstyle: TextStyle = MaterialTheme.typography.bodyMedium,
	showDropDownAfterFlag: Boolean = false,
	isEnabled: Boolean = true,
	isReadOnly: Boolean = false,
	flagShape: CornerBasedShape = RoundedCornerShape(16),
	@DrawableRes errorIcon: Int? = null,
	@DrawableRes dropDownIcon: Int? = null,
	showErrorIcon: Boolean = true,
	errorText: String = "Invalid Phone Number",
	errorModifier: Modifier = Modifier,
	colors: CCPColors
) {
	var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = text)) }
	val textFieldValue = textFieldValueState.copy(text = text)
	val keyboardController = LocalSoftwareKeyboardController.current


	Column {
		OutlinedTextField(
			isError = error,
			modifier = modifier
				.padding()
				.fillMaxWidth()
				.clip(RoundedCornerShape(textFieldShapeCornerRadiusInPercentage))
				.clipToBounds(),
			shape = RoundedCornerShape(textFieldShapeCornerRadiusInPercentage),
			value = textFieldValue,
			label = { Text(text = "Phone Number") },
			textStyle = phonenumbertextstyle,
			colors = OutlinedTextFieldDefaults.colors(
				focusedContainerColor = colors.surfaceColor,
				unfocusedContainerColor = colors.surfaceColor,
				disabledContainerColor = colors.surfaceColor,
				cursorColor = colors.cursorColor,
				focusedBorderColor = if (error) colors.errorColor
				else if (isEnabled) colors.outlineColor
				else colors.unfocusedOutlineColor,
				unfocusedBorderColor = if (error) colors.errorColor else colors.unfocusedOutlineColor,
			),
			onValueChange = {
				textFieldValueState = it
				if (text != it.text) {
					onValueChange(it.text)
				}
			},
			readOnly = isReadOnly,
			enabled = isEnabled,
			singleLine = true,
			visualTransformation = if (text.isEmpty())
				PlaceholderTransformation("Enter Your Phone Number")
			else VisualTransformation.None,
			placeholder = {
				Text(
					text = "Enter Your Phone Number"
				)
			},
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.NumberPassword,
				autoCorrect = true,
			),
			keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

			leadingIcon = {
				Row(
					modifier = Modifier.padding(start = 10.dp),
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically
				) {
					val dialog = MaterialCodePicker()
					Surface(
						modifier = Modifier
							.height(24.dp)
							.wrapContentWidth()
					) {
						dialog.MaterialCodeDialog(
							pickedCountry = pickedCountry,
							defaultSelectedCountry = defaultCountry,
							showCountryCode = showCountryCode,
							searchFieldPlaceHolderTextStyle = searchFieldPlaceHolderTextStyle,
							searchFieldTextStyle = searchFieldTextStyle,
							showCountryFlag = showCountryFlag,
							searchFieldShapeCornerRadiusInPercentage = searchFieldShapeCornerRadiusInPercentage,
							appbartitleStyle = appbartitleStyle,
							countryItemBgShape = countryItemBgShape,
							countryItemVerticalPadding = countryItemVerticalPadding,
							countryItemHorizontalPadding = countryItemHorizontalPadding,
							countrytextstyle = countrytextstyle,
							dialogcountrycodetextstyle = dialogcountrycodetextstyle,
							showCountryCodeInDIalog = showCountryCodeInDIalog,
							countrycodetextstyle = countrycodetextstyle,
							showDropDownAfterFlag = showDropDownAfterFlag,
							colors = colors,
							dropDownIcon = dropDownIcon,
							flagShape = flagShape,
							isEnabled = isEnabled,
						)
					}

					Surface(
						modifier = Modifier
							.padding(1.dp)
							.width(24.dp)
							.height(24.dp)
					) {
						Image(
							painter = painterResource(id = R.drawable.phone),
							contentDescription = "phone number",
						)
					}
				}
			},
		)
		if (error && showErrorText) {
			Text(
				text = errorText,
				color = colors.errorColor,
				style = errorTextStyle,
				modifier = errorModifier
			)
		}
	}
}

@Composable
fun SelectCountryWithCountryCode() {
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
	}
}



