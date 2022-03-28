package os.abuyahya.photogallery.ui.fragment.myFavoriteFrag

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import os.abuyahya.photogallery.R
import os.abuyahya.photogallery.adapter.PhotoAdapter
import os.abuyahya.photogallery.databinding.FragmentImagesBinding
import os.abuyahya.photogallery.databinding.FragmentMyFavoriteBinding
import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.ui.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MyFavoriteFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    private var _binding: FragmentMyFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyFavoriteBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        subscribeToObservers()
        setupRecyclerView()
        getFavoritePhoto()

        return binding.root
    }

    private fun getFavoritePhoto() {
        viewModel.getListFavPhotos()
    }

    private fun subscribeToObservers() {
        viewModel.listFavPhotos.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                photoAdapter.listPhotos = it
            } else {
                Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recFavPhoto.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = photoAdapter
        }

        photoAdapter.setIconFavClickListener{ photo, isFavorite ->
            removePhotoFromFav(photo)

        }
    }

    private fun removePhotoFromFav(photo: Photo) {
        viewModel.removePhotoFromFav(photo.id)
        photoAdapter.removeItem(photo)
        Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show()
    }

}
