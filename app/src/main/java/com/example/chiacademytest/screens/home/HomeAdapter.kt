package com.example.chiacademytest.screens.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chiacademytest.screens.favorite_images.FavoriteFragment
import com.example.chiacademytest.screens.home.entity.Pages
import com.example.chiacademytest.screens.images.ImagesFragment

class HomeAdapter(
    private val items: List<Pages>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        return when (items[position]) {
            Pages.All -> ImagesFragment()
            Pages.Favorite -> FavoriteFragment()
        }
    }
}