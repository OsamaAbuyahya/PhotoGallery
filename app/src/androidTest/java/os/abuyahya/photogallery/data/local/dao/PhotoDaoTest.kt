package os.abuyahya.photogallery.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import os.abuyahya.photogallery.data.local.PhotoDatabase
import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.model.PhotoUrl
import os.abuyahya.photogallery.model.ProfileImage
import os.abuyahya.photogallery.model.User

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@SmallTest
class PhotoDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var listPhotos = listOf(
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
    )

    private lateinit var photoDatabase: PhotoDatabase
    private lateinit var photoDao: PhotoDao

    @Before
    fun setup() {
        photoDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
                PhotoDatabase::class.java,
        ).allowMainThreadQueries().build()
        photoDao = photoDatabase.photoDao()
    }

    @After
    fun teardown() {
        photoDatabase.close()
    }

    @Test
    fun testAddAllListPhotos() = runBlocking {
        photoDao.addAllListPhotos(listPhotos)
        val allListPhotos = photoDao.getListPhotos()
        Assert.assertEquals(allListPhotos, listPhotos)
    }

    @Test
    fun testAddPhotoToFav() = runBlocking {
        val photo = listPhotos[0]
        photoDao.addPhotoToFav(photo)
        val allListFavPhotos = photoDao.getListFavPhotos()
        assertThat(allListFavPhotos.contains(photo)).isTrue()
    }

    @Test
    fun testRemovePhotoFromFav() = runBlocking {
        val photo = listPhotos[0]
        photoDao.removePhotoFromFav(photo.id)
        val allListFavPhotos = photoDao.getListFavPhotos()
        assertThat(allListFavPhotos.contains(photo)).isFalse()
    }

}
