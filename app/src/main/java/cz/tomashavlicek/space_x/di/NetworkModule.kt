package cz.tomashavlicek.space_x.di

import cz.tomashavlicek.space_x.api.SpaceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideSpaceXService(): SpaceService {
        return SpaceService.create()
    }
}