package com.kevin.dailyexercise

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var btnRegist: Button
    lateinit var tvRegist: TextView
    var userSaveData: AccountInfo? = null

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                userSaveData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent?.getParcelableExtra("user_data", AccountInfo::class.java)!!
                } else {
                    intent?.getParcelableExtra("user_data")!!
                }
                initUserData()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegist = findViewById(R.id.btRegist)
        tvRegist = findViewById(R.id.tvRegist)

        initUserData()

        btnRegist.setOnClickListener {
            val intent = Intent(this, Registration_Screen::class.java)
            intent.putExtra("user_data", userSaveData)
            startForResult.launch(intent)
        }
    }

    fun initUserData() {
        if (userSaveData != null) {
            """Anda Sudah Terdaftar,
                        |Data anda:
                        |Nama: ${userSaveData!!.accountName}
                        |Email: ${userSaveData!!.accountEmail}
                        |Tanggal Lahir:  ${
                getString(
                    R.string.date,
                    userSaveData!!.accountDay,
                    userSaveData!!.accountMonth + 1,
                    userSaveData!!.accountYear
                )
            }
                        |Alamat: ${userSaveData!!.accountAlamat}
                        |Pendidikan Terakhir: ${userSaveData!!.accountStudy}""".trimMargin()
                .also { tvRegist.text = it }
            btnRegist.text = getResources().getString(R.string.btUpd)
        }
    }

}