package os.abuyahya.photogallery.ui.fragment.imagesFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import os.abuyahya.photogallery.R
import os.abuyahya.photogallery.databinding.FragmentImagesBinding
import os.abuyahya.photogallery.databinding.FragmentMainBinding

class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(layoutInflater, container, false)


        return binding.root
    }
}
