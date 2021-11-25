package com.chsd.currencyapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chsd.currencyapp.R
import com.chsd.currencyapp.databinding.RvItemBinding
import com.chsd.currencyapp.entity.CurrencyData
import com.squareup.picasso.Picasso

class RvAdapter(var list: List<CurrencyData>, var context: Context, var onpress: onPress) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemview: RvItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        fun Bind(currencyData: CurrencyData, position: Int) {
            if (currencyData.img != "xd") {
                Picasso.get().load("https://flagcdn.com/60x45/${currencyData.img}.png")
                    .into(itemview.img)
            } else itemview.img.setImageResource(R.drawable.download)
            itemview.name.text = currencyData.Ccy
            itemview.lastName.text = currencyData.CcyNm_EN
            itemview.container.setOnClickListener {
                onpress.onclick(currencyData, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.Bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface onPress {
        fun onclick(currencyData: CurrencyData, position: Int)
    }
}