package com.example.pagingjetpackguilda.paging2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingjetpackguilda.FooterNetworkViewHolder
import com.example.pagingjetpackguilda.MagicCardEntity
import com.example.pagingjetpackguilda.MagicCardViewHolder
import com.example.pagingjetpackguilda.R

class PagingMagicCardAdapter:
    PagedListAdapter<MagicCardEntity, MagicCardViewHolder>(magicCardCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagicCardViewHolder {
        return MagicCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_magic_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MagicCardViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        val magicCardCallback = object : DiffUtil.ItemCallback<MagicCardEntity>() {
            override fun areItemsTheSame(
                oldItem: MagicCardEntity,
                newItem: MagicCardEntity
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: MagicCardEntity,
                newItem: MagicCardEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}



