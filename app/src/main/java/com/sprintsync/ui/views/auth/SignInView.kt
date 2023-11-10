package com.sprintsync.ui.views.auth

import android.app.Activity
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.auth.AuthViewModel
import com.sprintsync.auth.Authenticator
import com.sprintsync.ui.components.auth.EmailField
import com.sprintsync.ui.components.auth.PasswordField
import com.sprintsync.ui.components.auth.PromptRow
import com.sprintsync.ui.components.auth.SignInButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme
import kotlinx.coroutines.launch

@Composable
fun SignInView(
	context: Context? = null,
	navController: NavController? = null
) {
	val scope = rememberCoroutineScope()

	val authenticator = context?.let { Authenticator(it) }

	val authVM = viewModel<AuthViewModel>()
	val authState by authVM.state.collectAsStateWithLifecycle()

	LaunchedEffect(authState.signedIn) {
		if (authState.signedIn) navController?.navigate("home")
	}

	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.StartIntentSenderForResult()
	) { result ->
		if (result.resultCode == Activity.RESULT_OK) {
			scope.launch {
				authenticator
					?.signInWithIntent(result.data ?: return@launch)
					?.let { authVM.update(it) }
			}
		}
	}

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	Surface {
		// TODO: remove padding when we have main scaffold
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
					modifier = Modifier.requiredSize(240.dp),
					painter = painterResource(id = R.drawable.logo),
					contentDescription = null
				)
			}

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.3f)
			) {
				Title(
					title = "Hi, Welcome Back! ",
					subtitle = "Hello again, we missed you <3"
				)
			}

			Column(
				modifier = Modifier.wrapContentHeight(),
				verticalArrangement = Arrangement.spacedBy(20.dp)
			) {
				EmailField(
					onValueChange = { email = it },
					errorText = "Email is incorrect"
//                    Có nhiều loại lỗi nên sẽ phải tùy trường hợp để đặt errorText là gì
//                    Có thể gọi isError để hiển thị lỗi và tắt hiểu thị lỗi khi người dùng bắt
//                    đầu nhập lại
				)

				PasswordField(
					onValueChange = { password = it },
					errorText = "Password is incorrect"
				)

				Box(
					modifier = Modifier.fillMaxWidth(),
					contentAlignment = Alignment.BottomEnd
				) {
					PromptRow(
						modifier = Modifier.height(20.dp),
						normalText = "Forgot Password?",
						highlightedText = "Reset",
						highlightColor = Red80,
						onClick = { navController?.navigate("password_reset") }
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
				SignInButtonGroup(
					signInWithPassword = {
						authenticator?.let {
							authVM.signInWithPassword(scope, it, email, password)
						}
					},
					signInWithGoogle = {
						authenticator?.let {
							authVM.signInWithGoogle(scope, it, launcher)
						}
					},
					signUp = { navController?.navigate("sign_up") }
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
	SprintSyncTheme {
		SignInView()
	}
}
