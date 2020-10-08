package com.example.pagingjetpackguilda

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_magic_card.view.*

sealed class MagicCardsViewHolders {

    class MagicCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MagicCardEntity) {
            val name = "Name: " + item.name
            val type = "Type: " + item.type
            val rarity = "Rarity: " + item.rarity

            itemView.tvName.text = name
            itemView.tvType.text = type
            itemView.tvRarity.text = rarity
        }

    }
}

