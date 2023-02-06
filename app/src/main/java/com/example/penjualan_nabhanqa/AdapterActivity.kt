package com.example.penjualan_nabhanqa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan_nabhanqa.room.tbbarang
import kotlinx.android.synthetic.main.activity_adapter.view.*
import kotlinx.android.synthetic.main.activity_edit.view.*

class AdapterActivity (private val barang:ArrayList<tbbarang>, private val listener: onAdapterListener)
    : RecyclerView.Adapter<AdapterActivity.BarangViewHolder>() {
    class BarangViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate (R.layout.activity_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val brg = barang[position]
        holder.view.TId.text = brg.id.toString()
        holder.view.TNama.text = brg.nmBrg
        holder.view.TNama.setOnClickListener{listener.onClick(brg)}
        holder.view.icon_edit.setOnClickListener{listener.onUp(brg)}
        holder.view.icon_delete.setOnClickListener{listener.onDelt(brg)}
    }

    override fun getItemCount() = barang.size

    fun setData(list: List<tbbarang>){
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }
    interface onAdapterListener{
        fun onClick(tbbar: tbbarang)
        fun onUp(tbbar: tbbarang)
        fun onDelt(tbbar: tbbarang)
    }

}
