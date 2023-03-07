package com.kevin.dailyexercise

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

val dataAkun = mapOf(
    "global1234" to "glob123!!",
    "loyalty5678" to "l0y@lTyS67B",
    "indonesia1945" to "inD0n35!a19AS",
    "alfagift2023" to "alF@g1fTz0zE"
)

class LoginScreen : AppCompatActivity() {
    lateinit var etusername: EditText
    lateinit var etpassword: EditText
    lateinit var loginBt: Button
    lateinit var tvLoginOut: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etusername = findViewById(R.id.etUsername)
        etpassword = findViewById(R.id.etPassword)
        loginBt = findViewById(R.id.btLogin)
        tvLoginOut = findViewById(R.id.tvLoginOutput)

        loginBt.setOnClickListener {
            validateLogin((etusername.text.toString()), (etpassword.text.toString()))
        }
    }

    fun validateLogin(userInput: String, passInput: String) {
        val number = Regex("[0-9]")
        val alphabet = Regex("[a-zA-Z]")
        val symbols = Regex("[\\p{P}\\p{S}]")

        when {
            userInput.isBlank() || passInput.isBlank() -> errorOutput(R.string.val_log1)
            (userInput.length !in 6..15) -> errorOutput(R.string.val_log2)
            userInput.contains(" ") || passInput.contains(" ") -> errorOutput(R.string.val_log3)
            !number.containsMatchIn(userInput) || !alphabet.containsMatchIn(userInput) -> errorOutput(
                R.string.val_log4
            )
            symbols.containsMatchIn(userInput) -> errorOutput(R.string.val_log4_1)
            passInput.length !in 8..20 -> errorOutput(R.string.val_log5)
            !symbols.containsMatchIn(passInput) || !number.containsMatchIn(passInput) || !alphabet.containsMatchIn(
                passInput
            ) ->
                errorOutput(R.string.val_log6)
            else -> {
                val validasiAkun = dataAkun[userInput] == passInput
                if (validasiAkun) {
                    tvLoginOut.setTextColor(Color.GREEN)
                    errorOutput(R.string.log_success)
                    val intent = Intent(this, LoginSuccessScreen::class.java)
                    intent.putExtra("USER_NAME", userInput)
                    startActivity(intent)
                    finish()
                } else {
                    errorOutput(R.string.val_log7)
                }
            }
        }
    }

    fun errorOutput(errorId: Int) {
        tvLoginOut.text = getString(errorId)
    }
}