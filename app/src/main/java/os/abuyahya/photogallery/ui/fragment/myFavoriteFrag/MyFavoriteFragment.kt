package os.abuyahya.photogallery.ui.fragment.myFavoriteFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import os.abuyahya.photogallery.adapter.PhotoAdapter
import os.abuyahya.photogallery.databinding.FragmentMyFavoriteBinding
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
        setOnAdapterClickListener()
        getFavoritePhoto()

        return binding.root
    }

    private fun getFavoritePhoto() {
        viewModel.getListFavPhotos()
    }

    private fun subscribeToObservers() {
        viewModel.listFavPhotos.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            if (it.isNotEmpty()) {
                photoAdapter.setList(it)
            } else {
                binding.tvNoResult.isVisible = true
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
    }

    private fun setOnAdapterClickListener() {
        photoAdapter.setIconFavClickListener { photo, isFavorite ->
            if (isFavorite)
                viewModel.addPhotoToFav(photo)
            else
                viewModel.removePhotoFromFav(photo.id)
        }
    }

}
