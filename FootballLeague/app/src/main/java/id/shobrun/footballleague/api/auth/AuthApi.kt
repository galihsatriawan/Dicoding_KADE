package id.shobrun.footballleague.api.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi{
    @GET
    fun dummyAuth() : Call<ResponseBody>
}