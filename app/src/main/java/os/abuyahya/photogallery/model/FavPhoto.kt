package os.abuyahya.photogallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import os.abuyahya.photogallery.util.Constants.FAV_PHOTO_TABLE_NAME

@Entity(tableName = FAV_PHOTO_TABLE_NAME)
data class FavPhoto(
    @PrimaryKey
    var id: String,
    var created_at: String,
    var updated_at: String,
    var color: String,
    var description: String?,
    var user: User,
    var urls: PhotoUrl,
    var isFavorite: Boolean
)
