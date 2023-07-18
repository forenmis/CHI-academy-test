package com.example.chiacademytest.screens.images

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chiacademytest.R
import com.example.chiacademytest.databinding.ItemImageBinding
import com.example.chiacademytest.entity.Image
import com.example.chiacademytest.utils.setImage

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.VH>() {
    private val images = mutableListOf<Image>()
    var onFavoriteClick: ((Image) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val image = images[position]
        holder.feelItem(image)
        holder.binding.ivFavorite.setOnClickListener {
            image.isFavorite = !image.isFavorite
            onFavoriteClick?.invoke(image)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = images.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newList: List<Image>) {
        images.clear()
        images.addAll(newList)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun feelItem(image: Image) {
            with(binding) {
                ivImage.setImage(image.url)
                ivFavorite.setImageResource(if (image.isFavorite) R.drawable.ic_star_fill else R.drawable.ic_star_border)
            }
        }
    }
}