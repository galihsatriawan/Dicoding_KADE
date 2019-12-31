package id.shobrun.footballleague.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.R
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions().placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    // In this method, the value of requestOptions will be found automatically from Module that provide it
    @Singleton
    @Provides
    fun provideRequestManager(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideLogo(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo) as Drawable
    }
}