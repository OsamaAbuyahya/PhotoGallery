package os.abuyahya.photogallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import os.abuyahya.photogallery.util.Constants.PHOTO_TABLE_NAME

@Entity(tableName = PHOTO_TABLE_NAME)
data class Photo(
    @PrimaryKey
    @SerializedName("id")
    var id: String
)
