package com.kevin.dailyexercise

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private val dataAkun = mapOf(
    "global1234" to "glob123!!",
    "loyalty5678" to "l0y@lTyS67B",
    "indonesia1945" to "inD0n35!a19AS",
    "alfagift2023" to "alF@g1fTz0zE"
)

class LoginFragment : Fragment() {
    lateinit var etusername: EditText
    lateinit var etpassword: EditText
    lateinit var loginBt: Button
    lateinit var tvLoginOut: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etusername = view.findViewById(R.id.etUsername)
        etpassword = view.findViewById(R.id.etPassword)
        loginBt = view.findViewById(R.id.btLogin)
        tvLoginOut = view.findViewById(R.id.tvLoginOutput)

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
                    tvLoginOut.text = getString(R.string.log_success)
                } else {
                    errorOutput(R.string.val_log7)
                }
            }
        }
    }

    fun errorOutput(errorId: Int) {
        tvLoginOut.setTextColor(Color.RED)
        tvLoginOut.text = getString(errorId)
    }
}