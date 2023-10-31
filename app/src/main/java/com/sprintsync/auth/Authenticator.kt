package com.sprintsync.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sprintsync.R
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class Authenticator(context: Context) {
	// <--- Firebase Auth --->
	private val auth = Firebase.auth
	val signedInUser
		get() = auth.currentUser?.run {
			UserData(uid, displayName, email, isEmailVerified, photoUrl)
		}

	//	<--- One Tap sign-in for Google Auth --->
	private val oneTapClient = Identity.getSignInClient(context)
	private val signInRequest =
		BeginSignInRequest
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
			AuthState(
				signedIn = auth
					.createUserWithEmailAndPassword(email, password)
					.await()
					.user != null
			)
		}
		catch (e: Exception) {
			Log.e("Password Auth", "Failed to sign up", e)
			if (e is CancellationException) throw e
			AuthState(errorMessage = e.message)
		}
	}

	suspend fun signIn(email: String, password: String): AuthState {
		return try {
			AuthState(
				signedIn = auth
					.signInWithEmailAndPassword(email, password)
					.await()
					.user != null
			)
		}
		catch (e: Exception) {
			Log.e("Password Auth", "Failed to sign in", e)
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
			Log.e("Password Auth", "Failed to reset password", e)
			if (e is CancellationException) throw e
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
			Log.e("Google Auth", "Failed to get intent sender", e)
			if (e is CancellationException) throw e
			null
		}?.pendingIntent?.intentSender
	}

	suspend fun signInWithIntent(intent: Intent): AuthState {
		val token = oneTapClient.getSignInCredentialFromIntent(intent).googleIdToken
		val googleCredential = GoogleAuthProvider.getCredential(token, null)
		return try {
			AuthState(
				signedIn = auth
					.signInWithCredential(googleCredential)
					.await()
					.user != null
			)
		}
		catch (e: Exception) {
			Log.e("Google Auth", "Failed to sign in with intent", e)
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
			Log.e("Email Auth", "Failed to verify email", e)
			if (e is CancellationException) throw e
		}
	}

	//	<--- Sign out --->
	suspend fun signOut() {
		try {
			oneTapClient
				.signOut()
				.await()
			auth.signOut()
		}
		catch (e: Exception) {
			Log.e("Auth", "Failed to sign out", e)
			if (e is CancellationException) throw e
		}
	}
}