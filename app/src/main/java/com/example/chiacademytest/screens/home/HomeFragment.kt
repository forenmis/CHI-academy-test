package com.example.chiacademytest.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.chiacademytest.base.BaseFragment
import com.example.chiacademytest.databinding.FragmentHomeBinding
import com.example.chiacademytest.screens.home.entity.Pages

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val callback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomBar.selectedItemId = Pages.findPageByPosition(position).menuId
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeAdapter = HomeAdapter(
            items = listOf(Pages.All, Pages.Favorite),
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )
        with(binding.vpContainerHome) {
            adapter = homeAdapter
            isUserInputEnabled = false
        }
        binding.bottomBar.setOnItemSelectedListener { item ->
            binding.vpContainerHome.currentItem = Pages.findPageByMenuId(item.itemId).position
            return@setOnItemSelectedListener true
        }
    }

    override fun onStart() {
        super.onStart()
        binding.vpContainerHome.registerOnPageChangeCallback(callback)
    }

    override fun onStop() {
        super.onStop()
        binding.vpContainerHome.unregisterOnPageChangeCallback(callback)
    }
}