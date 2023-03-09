package com.kevin.dailyexercise

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListingAlamat : AppCompatActivity(), DataAlamatAdapter.interfaceView {
    lateinit var btnAdd:Button
    var userSaveData: DataAlamatUser? = null
    lateinit var recyclerViewAlamat: RecyclerView
    lateinit var dataAdapter: DataAlamatAdapter
    val dataAlamatList = mutableListOf<DataAlamatUser>()

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data!=null) {
                val intent = result.data
                userSaveData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent?.getParcelableExtra("user_data", DataAlamatUser::class.java)!!
                } else {
                    intent?.getParcelableExtra("user_data")!!
                }
                val positionCheck = intent.getIntExtra("position", -1)
                if (positionCheck == -1){
                    listingAlamat(dataAlamatList.size, true)
                } else {
                    listingAlamat(positionCheck, false)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataAdapter = DataAlamatAdapter(dataAlamatList)
        dataAdapter.itemListener = this
        recyclerViewAlamat = findViewById(R.id.listAlamatPengguna)
        recyclerViewAlamat.layoutManager = LinearLayoutManager(this)
        recyclerViewAlamat.adapter = dataAdapter
        btnAdd = findViewById(R.id.btnAddAlamat)
        btnAdd.setOnClickListener {
            val intent = Intent(this, InputAlamat::class.java)
            intent.putExtra("user_data", userSaveData)
            intent.putExtra("Position",dataAlamatList.size+1)
            startForResult.launch(intent)
        }
    }

    fun listingAlamat(itemNum: Int, nambah:Boolean){
        var dataAlamat = userSaveData!!.dataAlamat
        var dataDetail = userSaveData!!.dataDetail
        var dataLabel = userSaveData!!.dataLabel
        var dataPenerima = userSaveData!!.dataPenerima
        var dataHandphone = userSaveData!!.dataHandphone
        var dataSwitch = userSaveData!!.dataSwitch

        if (nambah){
            dataAlamatList.add(DataAlamatUser(dataAlamat,dataDetail,dataLabel,dataPenerima,dataHandphone,dataSwitch))
        } else {
            dataAlamatList[itemNum] = (DataAlamatUser(dataAlamat,dataDetail,dataLabel,dataPenerima,dataHandphone,dataSwitch))
        }
        dataAdapter.notifyItemChanged(itemNum)
    }

    override fun ubahItemRecyclerListner(position: Int) {
        Log.d("ubah Itme", userSaveData.toString())
        val intent = Intent(this, InputAlamat::class.java)
        intent.putExtra("user_data", dataAlamatList[position])
        intent.putExtra("Position", position)
        startForResult.launch(intent)
    }

    override fun deleteItemRecyclerListner(position: Int) {
       dataAlamatList.removeAt(position)
       dataAdapter.notifyDataSetChanged()
    }
}