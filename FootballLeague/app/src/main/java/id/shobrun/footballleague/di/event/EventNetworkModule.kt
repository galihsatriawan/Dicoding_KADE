package id.shobrun.footballleague.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.EventApi
import retrofit2.Retrofit

@Module
class EventNetworkModule {
    @Provides
    fun provideEventApi(retrofit: Retrofit) : EventApi{
     return retrofit.create(EventApi::class.java)
    }
}