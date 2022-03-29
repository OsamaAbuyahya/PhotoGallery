package os.abuyahya.photogallery.data.local.dao

import os.abuyahya.photogallery.model.Photo

class FakePhotoDao: PhotoDao {
    override suspend fun getListPhotos(): List<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun addAllListPhotos(listPhotos: List<Photo>) {
        TODO("Not yet implemented")
    }

    override suspend fun getListFavPhotos(): List<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun addPhotoToFav(photo: Photo) {
        TODO("Not yet implemented")
    }

    override suspend fun removePhotoFromFav(photoId: String) {
        TODO("Not yet implemented")
    }
}
