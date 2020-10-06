package com.example.pagingjetpackguilda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_magic_card.view.*

class MagicCardListAdapter : RecyclerView.Adapter<MagicCardViewHolder>() {

    private var data: List<MagicCardEntity> = listOf()

    fun updateData(cards: List<MagicCardEntity>){
        data = cards
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagicCardViewHolder {
        return MagicCardViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_magic_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MagicCardViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}


class MagicCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MagicCardEntity) {
        itemView.tvName.text = item.name
        itemView.tvType.text = item.type
        itemView.tvRarity.text = item.rarity
    }

}