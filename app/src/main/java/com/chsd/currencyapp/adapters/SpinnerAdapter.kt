package com.chsd.currencyapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.chsd.currencyapp.R
import com.chsd.currencyapp.entity.CurrencyData

class SpinnerAdapter(var list: List<CurrencyData>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var item: View =
            convertView ?: LayoutInflater.from(parent!!.context)
                .inflate(R.layout.spinner_item, parent, false)
        item.findViewById<TextView>(R.id.name_spinner).text = list[position].Ccy
        return item
    }
}