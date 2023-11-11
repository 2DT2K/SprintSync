package com.sprintsync.api

import com.sprintsync.auth.Authenticator
import okhttp3.Interceptor
import okhttp3.Response

/**
 * An interceptor that adds a Firebase-UID header containing Firebase's UID to requests.
 * @author Vo Tin Du
 * */
class RetrofitInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val uid: String = Authenticator.signedInUser?.uid ?: ""
		println("Firebase-UID: $uid")
		val request = chain.request().newBuilder()
			.addHeader("Firebase-UID", uid)
			.build()
		return chain.proceed(request)
	}
}