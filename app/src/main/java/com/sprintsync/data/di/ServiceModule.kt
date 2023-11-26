package com.sprintsync.data.di

import com.sprintsync.data.api.AttachmentAPI
import com.sprintsync.data.api.AuthAPI
import com.sprintsync.data.api.CommentAPI
import com.sprintsync.data.api.MemberAPI
import com.sprintsync.data.api.ProjectAPI
import com.sprintsync.data.api.SprintAPI
import com.sprintsync.data.api.TaskAPI
import com.sprintsync.data.api.TeamAPI
import com.sprintsync.data.retrofit.RetrofitSingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {
	@Provides
	@ViewModelScoped
	fun provideAuthService() = RetrofitSingleton
		.getInstance()
		.createService(AuthAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideMemberService() = RetrofitSingleton
		.getInstance()
		.createService(MemberAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideProjectService() = RetrofitSingleton
		.getInstance()
		.createService(ProjectAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideTeamService() = RetrofitSingleton
		.getInstance()
		.createService(TeamAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideSprintService() = RetrofitSingleton
		.getInstance()
		.createService(SprintAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideTaskService() = RetrofitSingleton
		.getInstance()
		.createService(TaskAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideCommentService() = RetrofitSingleton
		.getInstance()
		.createService(CommentAPI::class.java)

	@Provides
	@ViewModelScoped
	fun provideAttachmentService() = RetrofitSingleton
		.getInstance()
		.createService(AttachmentAPI::class.java)
}