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
import os.abuyahya.photogallery.util.Resource


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    private val _listPhotos = MutableLiveData<Resource<List<Photo>>>()
    val listPhotos: LiveData<Resource<List<Photo>>> = _listPhotos

    private val _listFavPhotos = MutableLiveData<Resource<List<Photo>>>()
    val listFavPhotos: LiveData<Resource<List<Photo>>> = _listFavPhotos

    private val _listSearchPhotos = MutableLiveData<Resource<List<Photo>>>()
    val listSearchPhotos: LiveData<Resource<List<Photo>>> = _listSearchPhotos

    fun getListPhotos() {
        _listPhotos.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val listPhotosFromDB = repository.getListPhotosFromDB()
                val photos = listPhotosFromDB.ifEmpty {
                    val listPhotosFromNetwork = repository.getListPhotosFromNetwork(CLIENT_ID)
                    repository.addAllListPhotos(listPhotosFromNetwork)
                    listPhotosFromNetwork
                }
                _listPhotos.postValue(Resource.success(photos))
            } catch (e: Exception) {
                _listPhotos.postValue(Resource.error(e.message, null))
            }
        }
    }

    fun searchPhotos(query: String) {
        _listSearchPhotos.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val searchPhotosApi = repository.searchPhotos(CLIENT_ID, query)
                _listSearchPhotos.postValue(Resource.success(searchPhotosApi.results))
            } catch (e: Exception) {
                _listSearchPhotos.postValue(Resource.error(e.message, null))
            }
        }
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
        _listFavPhotos.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _listFavPhotos.postValue(Resource.success(repository.getListFavPhotos()))
            } catch (e: Exception) {
                _listFavPhotos.postValue(Resource.error(e.message, null))
            }
        }
    }

}
