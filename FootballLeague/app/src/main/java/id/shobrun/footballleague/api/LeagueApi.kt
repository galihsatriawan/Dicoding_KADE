package id.shobrun.footballleague.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface LeagueApi {
    @GET
    fun dummyApi(): Call<ResponseBody>
}