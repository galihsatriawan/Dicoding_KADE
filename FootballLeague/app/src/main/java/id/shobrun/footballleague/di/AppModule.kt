package id.shobrun.footballleague.di

import android.app.Application
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.RequestInterceptor
import id.shobrun.footballleague.room.AppDatabase
import id.shobrun.footballleague.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun someString():String = "test"

    // Can access application from App Component so the return is false (is not null)
    @Singleton
    @Provides @Named("isAppNull")
    internal fun getApp(application: Application) : Boolean = application==null

}