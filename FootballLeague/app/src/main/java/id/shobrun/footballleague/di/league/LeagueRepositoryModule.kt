package id.shobrun.footballleague.di.league

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.room.LeagueDao

@Module
class LeagueRepositoryModule {
    @Provides
    fun provideInstanceRepository(service : LeagueApi, local : LeagueDao, application: Application) : LeagueRepository {
        return LeagueRepository(service,local,application)
    }
}