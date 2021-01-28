package cz.tomashavlicek.space_x.di

import android.content.Context
import cz.tomashavlicek.space_x.db.LaunchesDao
import cz.tomashavlicek.space_x.vo.AppDatabase
import cz.tomashavlicek.space_x.db.RocketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideRocketsDao(appDatabase: AppDatabase): RocketDao {
        return appDatabase.rocketsDao()
    }

    @Provides
    fun provideLaunchesDao(appDatabase: AppDatabase): LaunchesDao {
        return appDatabase.launchesDao()
    }
}
