package os.abuyahya.photogallery.ui.fragment.imagesFrag

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import os.abuyahya.photogallery.R
import os.abuyahya.photogallery.databinding.FragmentImagesBinding


class ImagesFragment : Fragment(), TabLayout.OnTabSelectedListener {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(layoutInflater, container, false)

        binding.tabLayout.addOnTabSelectedListener(this)

        return binding.root
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }
}
