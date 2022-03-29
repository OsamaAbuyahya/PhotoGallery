package os.abuyahya.photogallery.ui.viewmodel

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import os.abuyahya.photogallery.data.local.dao.FakePhotoDao
import os.abuyahya.photogallery.data.local.dao.PhotoDao
import os.abuyahya.photogallery.data.remote.FakePhotoApi
import os.abuyahya.photogallery.data.remote.PhotoApi
import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.model.PhotoUrl
import os.abuyahya.photogallery.model.ProfileImage
import os.abuyahya.photogallery.model.SearchResponseApi
import os.abuyahya.photogallery.model.User
import os.abuyahya.photogallery.repository.Repository
import javax.inject.Inject
import kotlin.test.assertEquals

const val CLIENT_ID = "bBWuReTRwzrxgS2Sfeff91_2Y9wXf88b7RyVkGPVC48"

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var photoApi: PhotoApi
    private lateinit var photoDao: PhotoDao
    private lateinit var repository: Repository
    private lateinit var photos: List<Photo>

    @Before
    fun setup() {
        photoApi = FakePhotoApi()
        photoDao = FakePhotoDao()
        repository = Repository(photoApi, photoDao)
        photos = listOf(
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
    }

    @Test
    fun testGetListPhotos() = runBlockingTest {
        val listPhotoFromNetwork = repository.getListPhotosFromNetwork(CLIENT_ID)
        assertEquals(
            expected = photos,
            actual = listPhotoFromNetwork
        )
    }

    @Test
    fun testSearchPhotos() = runBlockingTest {
        val searchedPhotoFromNetwork = repository.searchPhotos(CLIENT_ID, "ooo")
        val expectedResult = listOf(
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
            )
        )
        assertEquals(
            expected = SearchResponseApi(
                total = 1,
                total_pages = 1,
                results = expectedResult
            ),
            actual = searchedPhotoFromNetwork
        )
    }

}
