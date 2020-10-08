package com.example.pagingjetpackguilda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingjetpackguilda.paging2.PagingMagicCardAdapter
import kotlinx.android.synthetic.main.item_footer_network.view.*
import kotlinx.android.synthetic.main.item_magic_card.view.*

sealed class MagicCardsViewHolders(view: View): RecyclerView.ViewHolder(view) {

    class MagicCardViewHolder(view: View) : MagicCardsViewHolders(view) {
        fun bind(item: MagicCardEntity) {
            val name = "Name: " + item.name
            val type = "Type: " + item.type
            val rarity = "Rarity: " + item.rarity

            itemView.tvName.text = name
            itemView.tvType.text = type
            itemView.tvRarity.text = rarity
        }

    }

    class FooterNetworkViewHolder(view: View) : MagicCardsViewHolders(view) {
        fun bind(isLoading: Boolean) {
            if (isLoading) {
                itemView.errorGroup.visibility = View.GONE
                itemView.footerProgressBar.visibility = View.VISIBLE
            } else {
                itemView.errorGroup.visibility = View.VISIBLE
                itemView.footerProgressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        fun create(viewType: Int, parent: ViewGroup): MagicCardsViewHolders {
            return when (viewType) {
                PagingMagicCardAdapter.ITEM_FOOTER_TYPE -> FooterNetworkViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_footer_network, parent, false)
                )

                else -> MagicCardViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_magic_card, parent, false)
                )
            }
        }

    }

}

