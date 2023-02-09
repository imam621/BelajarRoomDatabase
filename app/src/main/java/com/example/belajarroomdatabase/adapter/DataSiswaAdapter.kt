package com.example.belajarroomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarroomdatabase.R
import com.example.belajarroomdatabase.database.Siswa

class DataSiswaAdapter(private val list: ArrayList<Siswa>, private val listener: onAdapterListener) : RecyclerView.Adapter<DataSiswaAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val nis = itemView.findViewById<TextView>(R.id.dataNIS)
        val nama = itemView.findViewById<TextView>(R.id.dataNama)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.data_siswa_adapter, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val siswa = list[position]
        holder.nis.text = siswa.nis.toString()
        holder.nama.text = siswa.namasiswa
        holder.nis.setOnClickListener {
            listener.onClick(siswa)
        }
    }

    override fun getItemCount() = list.size

    fun setData(listData: List<Siswa>){
        list.clear()
        list.addAll(listData)
        notifyDataSetChanged()
    }

    interface onAdapterListener{
        fun onClick(
            siswa: Siswa
        )
    }
}