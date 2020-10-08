package com.example.pagingjetpackguilda.paging2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingjetpackguilda.*

class PagingMagicCardAdapter:
    PagedListAdapter<MagicCardEntity, MagicCardsViewHolders>(magicCardCallback) {

    private var isLoading = false
    private var networkState: NetworkState.Status? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagicCardsViewHolders {
        return MagicCardsViewHolders.create(viewType, parent)
    }

    override fun onBindViewHolder(holder: MagicCardsViewHolders, position: Int) {
        when (holder) {
            is MagicCardsViewHolders.FooterNetworkViewHolder ->
                holder.bind(isLoading)

            is MagicCardsViewHolders.MagicCardViewHolder ->
                getItem(position)?.let {
                    holder.bind(it)
                }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            ITEM_FOOTER_TYPE
        } else {
            super.getItemViewType(position)
        }
    }

    fun updateState(networkState: NetworkState.Status, isLoading: Boolean) {
        val previousState = networkState
        val previousExtraRow = hasExtraRow()

        this.networkState =  networkState
        val newExtraRow = hasExtraRow()

        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState != networkState) {
            notifyItemChanged(itemCount - 1)
        }

        this.isLoading = isLoading
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.Status.SUCCESS
    }

    companion object {
        const val ITEM_FOOTER_TYPE = 100

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



