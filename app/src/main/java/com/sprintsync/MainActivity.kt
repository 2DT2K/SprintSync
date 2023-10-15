package com.sprintsync

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.sprintsync.ui.theme.SprintSyncTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SprintSyncTheme {
				MainContent()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainContent() {
	val context = LocalContext.current

	var acc by remember { mutableStateOf(GoogleSignIn.getLastSignedInAccount(context)) }

	val launcher =
		rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
			val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
			acc =
				try {
					task.getResult(ApiException::class.java)
				}
				catch (e: ApiException) {
					Toast
						.makeText(context, "Sign in failed", Toast.LENGTH_SHORT)
						.show()
					null
				}
		}

	val gsc = GoogleSignIn.getClient(
		context,
		GoogleSignInOptions
			.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestProfile()
			.requestScopes(
				Scope("https://www.googleapis.com/auth/gmail.send")
			)
			.build()
	)

	if (acc != null) User(acc = acc!!) {
		gsc
			.signOut()
			.addOnCompleteListener {
				Toast
					.makeText(context, "Sign out successfully", Toast.LENGTH_SHORT)
					.show()
				acc = null
			}
	}
	else SignInScreen { launcher.launch(gsc.signInIntent) }
}