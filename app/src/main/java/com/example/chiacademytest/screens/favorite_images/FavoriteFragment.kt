package com.example.chiacademytest.screens.favorite_images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chiacademytest.base.BaseFragment
import com.example.chiacademytest.databinding.FragmentFavoriteImagesBinding
import com.example.chiacademytest.screens.home.HomeViewModel
import com.example.chiacademytest.screens.images.ImagesAdapter

class FavoriteFragment : BaseFragment<FragmentFavoriteImagesBinding>() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val imagesAdapter by lazy { ImagesAdapter() }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteImagesBinding {
        return FragmentFavoriteImagesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvImages) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = imagesAdapter
        }
        homeViewModel.favoriteImagesLD.observe(viewLifecycleOwner) { imagesAdapter.updateItems(it) }
    }
}