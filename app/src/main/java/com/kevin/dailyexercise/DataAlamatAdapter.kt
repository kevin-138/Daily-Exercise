package com.kevin.dailyexercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAlamatAdapter(private val dataList: MutableList<DataAlamatUser>):RecyclerView.Adapter<DataAlamatAdapter.DataAlamatHolder>() {
    var itemListener :interfaceView? = null
    interface interfaceView {
        fun ubahItemRecyclerListner(position:Int)
        fun deleteItemRecyclerListner(position:Int)
    }
    class DataAlamatHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvLabel = itemView.findViewById<TextView>(R.id.tvLabel)
        val tvUserInfo = itemView.findViewById<TextView>(R.id.tvUserInfo)
        val tvAddrDetail = itemView.findViewById<TextView>(R.id.tvAddrLabel)
        val tvAddr = itemView.findViewById<TextView>(R.id.tvUserAddr)
        val btUbah = itemView.findViewById<Button>(R.id.btnUbah)
        val btDelete = itemView.findViewById<Button>(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAlamatHolder {
        return  DataAlamatHolder(LayoutInflater.from(parent.context).inflate(R.layout.alamat_list_design, parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataAlamatHolder, position: Int) {
        holder.tvLabel.text = dataList[position].dataLabel
        "${dataList[position].dataPenerima} - ${dataList[position].dataHandphone}".also { holder.tvUserInfo.text = it }
        holder.tvAddrDetail.text = dataList[position].dataDetail
        holder.tvAddr.text = dataList[position].dataAlamat

        holder.btUbah.setOnClickListener {
            itemListener?.ubahItemRecyclerListner(position)
        }

        holder.btDelete.setOnClickListener {
            itemListener?.deleteItemRecyclerListner(position)
        }
    }
}