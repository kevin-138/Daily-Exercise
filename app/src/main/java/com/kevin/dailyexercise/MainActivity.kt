package com.kevin.dailyexercise

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var btnRegist:Button
    lateinit var tvRegist:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegist = findViewById(R.id.btRegist)
        tvRegist = findViewById(R.id.tvRegist)

        val userSaveData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.intent.getParcelableExtra("user_data", AccountInfo::class.java)
        } else {
            this.intent.getParcelableExtra("user_data")
        }

        if (userSaveData != null) {
            tvRegist.text = getResources().getString(R.string.tvUbahData)
            btnRegist.text = getResources().getString(R.string.btUpd)
        }

        btnRegist.setOnClickListener {
            val intent = Intent(this, Registration_Screen::class.java)
            startActivity(intent)
        }



    }
}