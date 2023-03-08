package com.kevin.dailyexercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class DataAlamatAdapter(private val dataList: MutableList<DataAlamatUser>):RecyclerView.Adapter<DataAlamatAdapter.DataAlamatHolder>() {
    class DataAlamatHolder(itemView: View): ViewHolder(itemView) {
            val tvLabel = itemView.findViewById<TextView>(R.id.tvLabel)
            val tvUserInfo = itemView.findViewById<TextView>(R.id.tvUserInfo)
            val tvAddrDetail = itemView.findViewById<TextView>(R.id.tvAddrLabel)
            val tvAddr = itemView.findViewById<TextView>(R.id.tvUserAddr)
            val swUt = itemView.findViewById<Switch>(R.id.swAlamat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAlamatHolder {
       return  DataAlamatHolder(LayoutInflater.from(parent.context).inflate(R.layout.alamat_list_design, parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataAlamatHolder, position: Int) {
//        var stringPenerima = getContext().getResources().getString(R.string.dataPenerima, dataList[position].dataPenerima, dataList[position].dataHandphone)

        holder.tvLabel.text = dataList[position].dataLabel
        holder.tvUserInfo.text = "${dataList[position].dataPenerima} - ${dataList[position].dataHandphone}"
        holder.tvAddrDetail.text = dataList[position].dataLabel
        holder.tvAddr.text = dataList[position].dataLabel
    }
}