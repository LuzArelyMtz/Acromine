package com.luz.acromine.di

import com.luz.acromine.repository.AcromineRepository
import com.luz.acromine.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun  bindAcromineRepository(impl : AcromineRepository): Repository

}