package com.sprintsync.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sprintsync.R
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
	private val context: Context,
	private val oneTapClient: SignInClient
) {
	private val auth = Firebase.auth

	fun getSignedInUser() = auth.currentUser?.run {
		UserData(
			userId = uid,
			userName = displayName,
			profilePictureUrl = photoUrl
		)
	}

	suspend fun signIn(): IntentSender? {
		val result = try {
			oneTapClient
				.beginSignIn(buildSignInRequest())
				.await()
		}
		catch (e: Exception) {
			e.printStackTrace()
			if (e is CancellationException) throw e
			null
		}

		return result?.pendingIntent?.intentSender
	}

	suspend fun signOut() {
		try {
			oneTapClient
				.signOut()
				.await()
			auth.signOut()
		}
		catch (e: Exception) {
			e.printStackTrace()
			if (e is CancellationException) throw e
		}
	}

	suspend fun signInWithIntent(intent: Intent): SignInResult {
		val credential = oneTapClient.getSignInCredentialFromIntent(intent)
		val idToken = credential.googleIdToken
		val googleCredential = GoogleAuthProvider.getCredential(idToken, null)
		return try {
			val user = auth
				.signInWithCredential(googleCredential)
				.await()
				.user

			SignInResult(
				data = user?.run {
					UserData(
						userId = uid,
						userName = displayName,
						profilePictureUrl = photoUrl
					)
				}
			)
		}
		catch (e: Exception) {
			e.printStackTrace()
			if (e is CancellationException) throw e
			SignInResult(errorMessage = e.message)
		}
	}

	private fun buildSignInRequest(): BeginSignInRequest {
		return BeginSignInRequest
			.builder()
			.setGoogleIdTokenRequestOptions(
				BeginSignInRequest.GoogleIdTokenRequestOptions
					.builder()
					.setSupported(true)
					.setFilterByAuthorizedAccounts(false)
					.setServerClientId(context.getString(R.string.web_client_id))
					.build()
			)
			.setAutoSelectEnabled(true)
			.build()
	}
}