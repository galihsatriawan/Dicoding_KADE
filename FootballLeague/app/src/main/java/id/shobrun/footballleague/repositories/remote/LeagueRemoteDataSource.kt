package id.shobrun.footballleague.repositories.remote

import id.shobrun.footballleague.api.LeagueApi
import javax.inject.Inject

class LeagueRemoteDataSource @Inject constructor(private val league: LeagueApi) : BaseDataSource(){
    suspend fun fetchDataLeagueById(id : Int) = getResult { league.getLeagueById(id) }
}