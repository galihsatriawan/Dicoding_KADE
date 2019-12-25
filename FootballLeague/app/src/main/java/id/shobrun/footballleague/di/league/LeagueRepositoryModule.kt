package id.shobrun.footballleague.di.league

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.repository.LeagueRepository
import id.shobrun.footballleague.room.LeagueDao

@Module
class LeagueRepositoryModule {
    @Provides
    fun provideInstanceRepository(appExecutors: AppExecutors,service : LeagueApi, local : LeagueDao, application: Application) : LeagueRepository {
        return LeagueRepository(appExecutors,service,local,application)
    }
}