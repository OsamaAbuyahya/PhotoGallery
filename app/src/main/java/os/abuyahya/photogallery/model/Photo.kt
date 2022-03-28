package os.abuyahya.photogallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import os.abuyahya.photogallery.util.Constants.PHOTO_TABLE_NAME

@Entity(tableName = PHOTO_TABLE_NAME)
data class Photo(
    @PrimaryKey
    @SerializedName("id")
    var id: String,
    @SerializedName("created_at")
    var created_at: String,
    @SerializedName("updated_at")
    var updated_at: String,
    @SerializedName("color")
    var color: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("user")
    var user: User,
    @SerializedName("urls")
    var urls: PhotoUrl,
    var isFavorite: Boolean = false
)
