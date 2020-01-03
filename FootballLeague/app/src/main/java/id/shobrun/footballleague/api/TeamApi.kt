package id.shobrun.footballleague.api

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.network.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface TeamApi {
    @GET("lookupteam.php")
    fun getTeamById(@Query("id") idTeam: Int): LiveData<ApiResponse<TeamsResponse>>

    @GET("searchteams.php")
    fun getSearchTeam(@Query("t") qry: String): LiveData<ApiResponse<TeamsResponse>>

    @GET("lookup_all_teams.php")
    fun getTeamsByLeagueId(@Query("id") idLeague: Int): LiveData<ApiResponse<TeamsResponse>>
}