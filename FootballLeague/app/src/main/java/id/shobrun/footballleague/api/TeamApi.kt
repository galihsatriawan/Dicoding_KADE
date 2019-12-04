package id.shobrun.footballleague.api

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.models.network.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface TeamApi {
    @GET("lookupteam.php")
    fun getTeamById(@Query("id") idTeam: Int) : LiveData<ApiResponse<TeamsResponse>>
}