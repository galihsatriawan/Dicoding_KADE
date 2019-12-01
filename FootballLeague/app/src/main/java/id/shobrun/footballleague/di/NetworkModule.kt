package id.shobrun.footballleague.di

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.LiveDataCallAdapterFactory
import id.shobrun.footballleague.api.RequestInterceptor
import id.shobrun.footballleague.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    internal fun providePrivateOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor()).build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }
}
