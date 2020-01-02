package id.shobrun.footballleague.api

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.network.TeamRecordsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamRecordApi {
    @GET("lookuptable.php")
    fun getStandingLeague(@Query("l") idLeague: Int,@Query("s") season:Int) : LiveData<ApiResponse<TeamRecordsResponse>>
}