package com.kevin.dailyexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginSuccessScreen : AppCompatActivity() {
    lateinit var tvSucess: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success_screen)
        tvSucess = findViewById(R.id.tvLoginSuccess)
        val userName:String? = intent.getStringExtra("USER_NAME")

        tvSucess.text = """Login berhasil.
Selamat datang, ${userName}"""
    }
}