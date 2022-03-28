package os.abuyahya.photogallery.data.local

import androidx.room.TypeConverter
import com.google.gson.JsonObject
import org.json.JSONObject
import os.abuyahya.photogallery.model.PhotoUrl
import os.abuyahya.photogallery.model.ProfileImage
import os.abuyahya.photogallery.model.User

class DatabaseConverter {

    @TypeConverter
    fun fromUser(user: User): String {
        return JSONObject().apply {
            put("id", user.id)
            put("username", user.username)
            put("name", user.name)
            put("profileImage", fromProfileImage(user.profileImage))
        }.toString()
    }

    @TypeConverter
    fun toUser(user: String): User {
        val json = JSONObject(user)
        return User(
            json.getString("id"),
            json.getString("username"),
            json.getString("name"),
            toProfileImage(json.getString("profileImage")),
        )
    }

    @TypeConverter
    fun fromProfileImage(profileImage: ProfileImage): String {
        return JSONObject().apply {
            put("small", profileImage.small)
            put("medium", profileImage.medium)
            put("large", profileImage.large)
        }.toString()
    }

    @TypeConverter
    fun toProfileImage(profileImage: String): ProfileImage {
        val json = JSONObject(profileImage)
        return ProfileImage(
            json.getString("small"),
            json.getString("medium"),
            json.getString("large"),
        )
    }


    @TypeConverter
    fun fromPhotoUrl(photoUrl: PhotoUrl): String {
        return JSONObject().apply {
            put("raw", photoUrl.raw)
            put("full", photoUrl.full)
            put("regular", photoUrl.regular)
            put("small", photoUrl.small)
            put("thumb", photoUrl.thumb)
        }.toString()
    }

    @TypeConverter
    fun toPhotoUrl(photoUrl: String): PhotoUrl {
        val json = JSONObject(photoUrl)
        return PhotoUrl(
            json.getString("raw"),
            json.getString("full"),
            json.getString("regular"),
            json.getString("small"),
            json.getString("thumb"),
        )
    }

}
