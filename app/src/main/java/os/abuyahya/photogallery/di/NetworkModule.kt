package os.abuyahya.photogallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.MediaType
import okhttp3.OkHttpClient
import os.abuyahya.photogallery.data.remote.PhotoApi
import os.abuyahya.photogallery.util.Constants.BASE_URL
import os.abuyahya.photogallery.util.Constants.CONNECT_TIMEOUT
import os.abuyahya.photogallery.util.Constants.READ_TIMEOUT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePhotoApi(retrofit: Retrofit): PhotoApi {
        return retrofit.create(PhotoApi::class.java)
    }
}
