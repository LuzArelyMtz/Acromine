package com.luz.acromine.di

import com.luz.acromine.api.AcromineAPIImpl
import com.luz.acromine.api.IAcromineAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ActivityComponent::class)
class AcromineApiModule {

    @Provides
    fun providesApi() = AcromineAPIImpl.provideRetrofit().create(IAcromineAPIService::class.java)
}