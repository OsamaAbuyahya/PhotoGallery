package os.abuyahya.photogallery.ui.fragment.myFavoriteFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import os.abuyahya.photogallery.adapter.PhotoAdapter
import os.abuyahya.photogallery.databinding.FragmentMyFavoriteBinding
import os.abuyahya.photogallery.model.Photo
import os.abuyahya.photogallery.ui.viewmodel.MainViewModel
import os.abuyahya.photogallery.util.Status
import javax.inject.Inject

@AndroidEntryPoint
class MyFavoriteFragment : Fragment(), TabLayout.OnTabSelectedListener {

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

        binding.tabLayout.addOnTabSelectedListener(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getFavoritePhoto()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) { setLayoutManger(tab?.position) }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    private fun getFavoritePhoto() {
        viewModel.getListFavPhotos()
    }

    private fun subscribeToObservers() {
        viewModel.listFavPhotos.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    setList(it.data!!)
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    private fun setList(photos: List<Photo>) {
        binding.tvNoResult.isVisible = photos.isEmpty()
        photoAdapter.setList(photos)
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

    private fun setLayoutManger(position: Int?) {
        binding.recFavPhoto.layoutManager = if (position == 0) {
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        } else {
            GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )
        }
    }

}
