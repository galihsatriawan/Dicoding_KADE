package id.shobrun.footballleague.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.utils.Constants
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    internal fun provideRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun someString():String = "test"

    // Can access application from App Component so the return is false (is not null)
    @Singleton
    @Provides @Named("isAppNull")
    internal fun getApp(application: Application) : Boolean = application==null

}