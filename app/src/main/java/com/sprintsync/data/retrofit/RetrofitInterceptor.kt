package com.sprintsync.data.retrofit

import com.sprintsync.data.auth.Authenticator
import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val uid = Authenticator.signedInUser?.uid ?: ""
		val request = chain
			.request()
			.newBuilder()
			.addHeader("Fire-id", uid)
			.build()
		return chain.proceed(request)
	}
}