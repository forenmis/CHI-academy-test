package com.example.chiacademytest.screens.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chiacademytest.base.BaseFragment
import com.example.chiacademytest.databinding.FragmentImagesBinding
import com.example.chiacademytest.screens.home.HomeViewModel
import com.example.chiacademytest.utils.OnPaginationScrollListener

class ImagesFragment : BaseFragment<FragmentImagesBinding>() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val viewModel by viewModels<ImagesViewModel>()
    private val imagesAdapter by lazy { ImagesAdapter() }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentImagesBinding {
        return FragmentImagesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageLayoutManager = GridLayoutManager(requireContext(), 2)
        imagesAdapter.onFavoriteClick = { homeViewModel.addFavoriteImage(it) }

        with(binding.rvImages) {
            layoutManager = imageLayoutManager
            adapter = imagesAdapter
            addOnScrollListener(OnPaginationScrollListener(imageLayoutManager) {
                viewModel.loadImages()
            })
        }

        with(viewModel) {
            imagesLD.observe(viewLifecycleOwner) { imagesAdapter.updateItems(it) }
            exceptionLD.observe(viewLifecycleOwner) { showToast(it.toString()) }
        }
    }
}