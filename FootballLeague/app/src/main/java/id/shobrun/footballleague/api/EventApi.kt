package id.shobrun.footballleague.api

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.network.EventSearchResponse
import id.shobrun.footballleague.models.network.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {
    @GET("eventsnextleague.php")
    fun getNextEvents(@Query("id") idLeague: Int): LiveData<ApiResponse<EventsResponse>>

    @GET("eventspastleague.php")
    fun getPastEvents(@Query("id") idLeague: Int): LiveData<ApiResponse<EventsResponse>>

    @GET("lookupevent.php")
    fun getDetailEvents(@Query("id") idEvent: Int): LiveData<ApiResponse<EventsResponse>>

    @GET("searchevents.php")
    fun getSearchEvents(@Query("e") query: String): LiveData<ApiResponse<EventSearchResponse>>
}