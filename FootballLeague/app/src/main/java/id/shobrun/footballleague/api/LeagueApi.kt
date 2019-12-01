package id.shobrun.footballleague.api

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.entity.League
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface LeagueApi {
    @GET("lookupleague.php")
    fun getLeagueById(@Query("id") id : Int): LiveData<ApiResponse<League>>
}