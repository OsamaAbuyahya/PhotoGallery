package os.abuyahya.photogallery.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.repository.Repository
import os.abuyahya.photogallery.util.Constants.CLIENT_ID


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    private val _listPhotos = MutableLiveData<List<Photo>>()
    val listPhotos: LiveData<List<Photo>> = _listPhotos

    private val _listFavPhotos = MutableLiveData<List<Photo>>()
    val listFavPhotos: LiveData<List<Photo>> = _listFavPhotos

    init {
    }

    fun addPhotoToFav(photo: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPhotoToFav(photo)
        }
    }

    fun removePhotoFromFav(photoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removePhotoFromFav(photoId)
        }
    }

    fun getListFavPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _listFavPhotos.postValue(repository.getListFavPhotos())
        }
    }

    fun getListPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val listPhotosFromDB = repository.getListPhotosFromDB()
            val photos = listPhotosFromDB.ifEmpty {
                val listPhotosFromNetwork = repository.getListPhotosFromNetwork(CLIENT_ID)
                repository.addAllListPhotos(listPhotosFromNetwork)
                listPhotosFromNetwork
            }
            _listPhotos.postValue(photos)
        }
    }
}
