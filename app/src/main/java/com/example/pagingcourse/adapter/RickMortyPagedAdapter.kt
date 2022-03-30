package com.example.pagingcourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pagingcourse.adapter.RickMortyPagedAdapter.*
import com.example.pagingcourse.databinding.RickMortyLayoutBinding
import com.example.pagingcourse.models.RickMorty

class RickMortyPagedAdapter : PagingDataAdapter<RickMorty, MyViewModel>(diffCallback) {


    inner class MyViewModel(val binding: RickMortyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>() {
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val currentItem = getItem(position)

        // this method getItem() is from PagingDataAdapter...

        holder.binding.apply {
            textView.text = "${currentItem?.name}"
            val imageLink = currentItem?.image

            // here I'm using coroutine image loader(coil) to display images
            // but you can also use glide if you can
            imageView.load(imageLink) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        return MyViewModel(
            RickMortyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}