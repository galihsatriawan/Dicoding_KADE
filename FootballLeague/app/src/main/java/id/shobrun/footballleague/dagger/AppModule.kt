package id.shobrun.footballleague.dagger

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRequestOptions():RequestOptions{
        return RequestOptions().placeholder(android.R.drawable.screen_background_light)
            .error(android.R.drawable.screen_background_light)
    }
    @Singleton
    @Provides
    fun provideGlideInstance(application: Application,requestOptions: RequestOptions):RequestManager{
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    internal fun someString():String = "test"

    // Can access application from App Component so the return is false (is not null)
    @Singleton
    @Provides @Named("isAppNull")
    internal fun getApp(application: Application) : Boolean = application==null
}