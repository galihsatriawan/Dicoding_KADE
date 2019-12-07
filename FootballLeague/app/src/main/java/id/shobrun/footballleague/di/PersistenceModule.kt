package id.shobrun.footballleague.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.database.MyDatabaseOpenHelper
import id.shobrun.footballleague.room.AppDatabase
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    internal fun provideRoomInstance(application: Application) = AppDatabase.getInstance(application)

    @Singleton
    @Provides
    internal fun provideSQLiteInstance(application: Application) = MyDatabaseOpenHelper.getInstance(application)
}