package id.shobrun.footballleague.api

import id.shobrun.footballleague.models.League
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface LeagueApi {
    @GET("lookupleague.php?id={idLeague}")
    fun getLeagueById(@Path("idLeague") id : Int): Response<League>
}