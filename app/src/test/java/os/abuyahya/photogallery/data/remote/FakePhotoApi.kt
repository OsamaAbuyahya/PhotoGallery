package os.abuyahya.photogallery.data.remote

import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.model.PhotoUrl
import os.abuyahya.photogallery.model.ProfileImage
import os.abuyahya.photogallery.model.SearchResponseApi
import os.abuyahya.photogallery.model.User

class FakePhotoApi: PhotoApi {
    private var listPhotos = listOf(
        Photo(
            id = "ooo",
            created_at = "",
            updated_at = "",
            color = "",
            description = "",
            user = User(
                id = "1",
                username = "o",
                name = "o",
                profileImage = ProfileImage(
                    small = "",
                    medium = "",
                    large = ""
                )
            ),
            urls = PhotoUrl(
                raw = "",
                full = "",
                regular = "",
                small = "",
                thumb = ""
            ),
            false
        ),
        Photo(
            id = "zzz",
            created_at = "",
            updated_at = "",
            color = "",
            description = "",
            user = User(
                id = "2",
                username = "s",
                name = "s",
                profileImage = ProfileImage(
                    small = "",
                    medium = "",
                    large = ""
                )
            ),
            urls = PhotoUrl(
                raw = "",
                full = "",
                regular = "",
                small = "",
                thumb = ""
            ),
            false
        ),
        Photo(
            id = "rrr",
            created_at = "",
            updated_at = "",
            color = "",
            description = "",
            user = User(
                id = "3",
                username = "a",
                name = "a",
                profileImage = ProfileImage(
                    small = "",
                    medium = "",
                    large = ""
                )
            ),
            urls = PhotoUrl(
                raw = "",
                full = "",
                regular = "",
                small = "",
                thumb = ""
            ),
            false
        )
    )

    override suspend fun getListPhotos(clientId: String): List<Photo> {
        return listPhotos
    }

    override suspend fun searchPhotos(clientId: String, query: String): SearchResponseApi {
        val searchedPhoto = listPhotos.filter { it.id.equals(query, ignoreCase = true) }
        return SearchResponseApi(
            total = 1,
            total_pages = 1,
            searchedPhoto
        )
    }
}
