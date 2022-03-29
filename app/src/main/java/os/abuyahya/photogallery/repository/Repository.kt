package os.abuyahya.photogallery.repository

import os.abuyahya.photogallery.data.local.dao.PhotoDao
import os.abuyahya.photogallery.data.remote.PhotoApi
import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.model.SearchResponseApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val photoApi: PhotoApi,
    private val photoDao: PhotoDao
) {

    suspend fun getListPhotosFromNetwork(clientId: String): List<Photo> {
        return photoApi.getListPhotos(clientId)
    }

    suspend fun searchPhotos(clientId: String, query: String): SearchResponseApi {
        return photoApi.searchPhotos(clientId, query)
    }

    suspend fun getListPhotosFromDB(): List<Photo> {
        return photoDao.getListPhotos()
    }

    suspend fun addAllListPhotos(listPhotos: List<Photo>) {
        photoDao.addAllListPhotos(listPhotos)
    }

    suspend fun addPhotoToFav(photo: Photo) {
        photoDao.addPhotoToFav(photo)
    }

    suspend fun removePhotoFromFav(photoId: String) {
        photoDao.removePhotoFromFav(photoId)
    }

    suspend fun getListFavPhotos(): List<Photo> {
        return photoDao.getListFavPhotos()
    }
}
