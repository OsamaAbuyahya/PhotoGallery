package os.abuyahya.photogallery.data.remote

import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.model.SearchResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    
    @GET("photos?")
    suspend fun getListPhotos(
        @Query("client_id") clientId: String
    ): List<Photo>

    @GET("search/photos??")
    suspend fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") query: String
    ): SearchResponseApi

}
