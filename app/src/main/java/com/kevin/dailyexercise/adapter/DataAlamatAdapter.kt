package com.kevin.dailyexercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.dailyexercise.data_model.DataAlamatUser
import com.kevin.dailyexercise.databinding.AlamatListDesignBinding

class DataAlamatAdapter(private val dataList: MutableList<DataAlamatUser>):RecyclerView.Adapter<DataAlamatAdapter.DataAlamatHolder>() {
    var itemListener : InterfaceView? = null
    interface InterfaceView {
        fun ubahItemRecyclerListner(position:Int)
        fun deleteItemRecyclerListner(position:Int)
    }
    class DataAlamatHolder(binding: AlamatListDesignBinding): RecyclerView.ViewHolder(binding.root) {
        val tvLabel = binding.tvLabel
        val tvUserInfo = binding.tvUserInfo
        val tvAddrDetail = binding.tvAddrLabel
        val tvAddr = binding.tvUserAddr
        val btUbah = binding.btnUbah
        val btDelete = binding.btnHapus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAlamatHolder {
        val binding: AlamatListDesignBinding =
            AlamatListDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataAlamatHolder(binding)
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