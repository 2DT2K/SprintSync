package com.sprintsync.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSingleton private constructor() {
	private val okHttpClient = okhttp3.OkHttpClient
		.Builder()
		.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
		.addInterceptor(RetrofitInterceptor())
		.build()

	private val retrofit: Retrofit = Retrofit
		.Builder()
		.baseUrl("https://e21f-58-186-162-35.ngrok-free.app/")
		.client(okHttpClient)
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	companion object {
		@Volatile
		private var instance: RetrofitSingleton? = null

		fun getInstance(): RetrofitSingleton =
			instance ?: synchronized(this) {
				instance ?: RetrofitSingleton().also { instance = it }
			}
	}

	fun <T> createService(service: Class<T>): T {
		return retrofit.create(service)
	}
}