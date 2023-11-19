package com.sprintsync.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A singleton class that creates a Retrofit instance.
 * Use this class to create a Retrofit service.
 * @author Vo Tin Du
 */
class RetrofitSingleton private constructor() {
	private val okHttpClient = okhttp3.OkHttpClient
		.Builder()
		.addInterceptor(RetrofitInterceptor())
		.build()

	private val retrofit: Retrofit = Retrofit
		.Builder()
		.baseUrl("https://7317-2402-800-61cf-1b66-5542-2c28-4aa9-5771.ngrok-free.app/")
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