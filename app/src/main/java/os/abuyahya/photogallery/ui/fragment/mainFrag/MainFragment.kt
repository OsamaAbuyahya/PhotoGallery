package os.abuyahya.photogallery.ui.fragment.mainFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import os.abuyahya.photogallery.R
import os.abuyahya.photogallery.adapter.ViewPagerAdapter
import os.abuyahya.photogallery.databinding.FragmentMainBinding
import os.abuyahya.photogallery.ui.fragment.imagesFrag.ImagesFragment
import os.abuyahya.photogallery.ui.fragment.myFavoriteFrag.MyFavoriteFragment

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        setupViewPager()

        binding.edtSearch.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }
        return binding.root
    }

    private fun setupViewPager() {
        val listTitles = listOf(getString(R.string.images), getString(R.string.my_favourite))
        val listFrag = listOf(ImagesFragment(), MyFavoriteFragment())

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle, listFrag)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tap, position ->
            tap.text = listTitles[position]
        }.attach()
        setMarginBetweenTap(binding.tabLayout)
    }

    private fun setMarginBetweenTap(tabLayout: TabLayout) {
        for (i in 0 until tabLayout.tabCount) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 8, 32, 8)
            tab.requestLayout()
        }
    }

}
