package os.abuyahya.photogallery.ui.fragment.searchFrag

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import os.abuyahya.photogallery.R
import os.abuyahya.photogallery.adapter.PhotoAdapter
import os.abuyahya.photogallery.databinding.FragmentSearchBinding
import os.abuyahya.photogallery.ui.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), TextWatcher {

    lateinit var viewModel: MainViewModel

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        subscribeToObservers()
        setupRecyclerView()

        binding.edtSearch.addTextChangedListener(this)
        binding.icBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {
        if (p0.toString().isNotEmpty())
            searchPhotos(p0.toString())
    }

    private fun searchPhotos(query: String) {
        binding.progressBar.isVisible = true
        viewModel.searchPhotos(query)
    }

    private fun subscribeToObservers() {
        viewModel.listSearchPhotos.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            binding.tvNoResult.isVisible = it.isEmpty()
            photoAdapter.setList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.recSearchPhoto.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = photoAdapter
        }
    }
}
