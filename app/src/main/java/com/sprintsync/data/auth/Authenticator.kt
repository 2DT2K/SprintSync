package com.sprintsync.data.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sprintsync.R
import com.sprintsync.data.view_models.state.AuthState
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class Authenticator(context: Context) {
	// <--- Firebase Auth --->
	companion object {
		private val auth = Firebase.auth
		val signedIn
			get() = auth.currentUser != null
		val signedInUser
			get() = auth.currentUser?.run { UserData(uid, displayName!!, email!!, isEmailVerified) }
	}

	//	<--- One Tap sign-in for Google Auth --->
	private val oneTapClient = Identity.getSignInClient(context)
	private val signInRequest = BeginSignInRequest
		.builder()
		.setGoogleIdTokenRequestOptions(
			BeginSignInRequest.GoogleIdTokenRequestOptions
				.builder()
				.setSupported(true)
				.setFilterByAuthorizedAccounts(false)
				.setServerClientId(context.getString(R.string.web_client_id))
				.build()
		)
		.build()

	//	<--- Authenticate using Password-Based Account --->
	suspend fun signUp(email: String, password: String): AuthState {
		return try {
			val user = auth
				.createUserWithEmailAndPassword(email, password)
				.await()
				.user

			val profile = UserProfileChangeRequest
				.Builder()
				.setDisplayName(email.substringBefore('@'))
				.build()

			user
				?.updateProfile(profile)
				?.await()

			AuthState(user != null)
		}
		catch (e: Exception) {
			Log.e("Sign Up", e.message.toString())
			if (e is CancellationException) throw e
			AuthState(errorMessage = e.message)
		}
	}

	suspend fun signIn(email: String, password: String): AuthState {
		return try {
			val user = auth
				.signInWithEmailAndPassword(email, password)
				.await()
				.user

			AuthState(user != null)
		}
		catch (e: Exception) {
			Log.e("Sign In", e.message.toString())
			if (e is CancellationException) throw e
			AuthState(errorMessage = e.message)
		}
	}

	suspend fun resetPassword(email: String) {
		try {
			auth
				.sendPasswordResetEmail(email)
				.await()
		}
		catch (e: Exception) {
			Log.e("Reset Password", e.message.toString())
			if (e is CancellationException) throw e
			AuthState(errorMessage = e.message)
		}
	}

	//	<--- Authenticate using Google Account --->
	suspend fun getSignInIntentSender(): IntentSender? {
		return try {
			oneTapClient
				.beginSignIn(signInRequest)
				.await()
		}
		catch (e: Exception) {
			Log.e("Get Intent Sender", e.message.toString())
			if (e is CancellationException) throw e
			null
		}?.pendingIntent?.intentSender
	}

	suspend fun signInWithIntent(intent: Intent): AuthState {
		val token = oneTapClient.getSignInCredentialFromIntent(intent).googleIdToken
		val googleCredential = GoogleAuthProvider.getCredential(token, null)

		return try {
			val user = auth
				.signInWithCredential(googleCredential)
				.await()
				.user

			AuthState(user != null)
		}
		catch (e: Exception) {
			Log.e("Google Sign In", e.message.toString())
			if (e is CancellationException) throw e
			AuthState(errorMessage = e.message)
		}
	}

	//	<--- Verify Email --->
	suspend fun verifyEmail() {
		try {
			auth
				.currentUser
				?.sendEmailVerification()
				?.await()
		}
		catch (e: Exception) {
			Log.e("Verify Email", e.message.toString())
			if (e is CancellationException) throw e
		}
	}

	//	<--- Sign out --->
	suspend fun signOut() {
		try {
			auth.signOut()
			oneTapClient
				.signOut()
				.await()
		}
		catch (e: Exception) {
			Log.e("Sign Out", e.message.toString())
			if (e is CancellationException) throw e
		}
	}
}

data class UserData(
	val uid: String,
	val name: String,
	val email: String,
	val verified: Boolean
)