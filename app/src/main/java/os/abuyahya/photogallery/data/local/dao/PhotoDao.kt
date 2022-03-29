package os.abuyahya.photogallery.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import os.abuyahya.photogallery.model.FavPhoto
import os.abuyahya.photogallery.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM `photo table`")
    suspend fun getListPhotos(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Photo::class)
    suspend fun addAllListPhotos(listPhotos: List<Photo>)

    @Query("SELECT * FROM `favorite photo table`")
    suspend fun getListFavPhotos(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = FavPhoto::class)
    suspend fun addPhotoToFav(photo: Photo)

    @Query("DELETE FROM `favorite photo table` WHERE id = :photoId")
    suspend fun removePhotoFromFav(photoId: String)

}
