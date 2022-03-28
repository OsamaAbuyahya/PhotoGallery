package os.abuyahya.photogallery.ui.fragment.imagesFrag

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import os.abuyahya.photogallery.R
import os.abuyahya.photogallery.adapter.PhotoAdapter
import os.abuyahya.photogallery.databinding.FragmentImagesBinding
import os.abuyahya.photogallery.ui.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment(), TabLayout.OnTabSelectedListener {

    lateinit var viewModel: MainViewModel

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        subscribeToObservers()
        setupRecyclerView()
        getPhotos()

        binding.tabLayout.addOnTabSelectedListener(this)

        return binding.root
    }

    private fun getPhotos() {
        viewModel.getListPhotos()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        binding.recPhoto.layoutManager = if (tab?.position == 0) {
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

    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    private fun setupRecyclerView() {
        binding.recPhoto.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = photoAdapter
        }

        photoAdapter.setIconFavClickListener{ photo, isFavorite ->
            if (isFavorite) {
                viewModel.addPhotoToFav(photo)
                Toast.makeText(requireContext(), "Added To Fav", Toast.LENGTH_SHORT).show()
            }
            else
                viewModel.removePhotoFromFav(photo.id)
        }
    }

    private fun subscribeToObservers() {
        viewModel.listPhotos.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                photoAdapter.listPhotos = it
            } else {
                Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
