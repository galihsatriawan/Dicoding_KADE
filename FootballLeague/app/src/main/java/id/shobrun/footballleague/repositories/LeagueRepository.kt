package id.shobrun.footballleague.repositories

import android.content.Context
import id.shobrun.footballleague.repositories.local.LeagueLocalData
import id.shobrun.footballleague.repositories.remote.LeagueRemoteData
import javax.inject.Singleton

@Singleton
class LeagueRepository() {
    private val localData:LeagueLocalData
    private val remoteData:LeagueRemoteData
    init {
        localData = LeagueLocalData()
        remoteData = LeagueRemoteData()
    }
}