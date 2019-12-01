package id.shobrun.footballleague.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.room.AppDatabase
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    internal fun provideRoomInstance(application: Application) = AppDatabase.getInstance(application)

}