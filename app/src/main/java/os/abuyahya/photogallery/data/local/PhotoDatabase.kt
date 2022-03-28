package os.abuyahya.photogallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import os.abuyahya.photogallery.data.local.dao.PhotoDao
import os.abuyahya.photogallery.model.Photo

@Database(entities = [Photo::class], version = 1)
//@TypeConverters(DatabaseConverter::class)
abstract class PhotoDatabase: RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}
