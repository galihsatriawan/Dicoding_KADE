package id.shobrun.footballleague.di.league

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.room.AppDatabase

@Module
class LeaguePersistenceModule {
    /**
     * Persistence
     */

    @Provides
    fun provideLeagueDao(database: AppDatabase) = database.leagueDao()
}