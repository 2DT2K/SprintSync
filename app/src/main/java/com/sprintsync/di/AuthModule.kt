package com.sprintsync.di

import android.content.Context
import com.sprintsync.auth.Authenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
	@Provides
	@Singleton
	fun provideAuthenticator(@ApplicationContext context: Context) = Authenticator(context)
}