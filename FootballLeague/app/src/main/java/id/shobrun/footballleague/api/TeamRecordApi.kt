package id.shobrun.footballleague.api

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.network.TeamRecordsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamRecordApi {
    @GET("lookuptable.php")
    fun getStandingLeague(@Query("id") leagueId: Int) : LiveData<ApiResponse<TeamRecordsResponse>>
}