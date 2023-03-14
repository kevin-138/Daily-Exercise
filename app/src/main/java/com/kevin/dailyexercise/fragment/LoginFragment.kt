package com.kevin.dailyexercise.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kevin.dailyexercise.R
import com.kevin.dailyexercise.data.DataAkun
import com.kevin.dailyexercise.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    var toast: Toast? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btLogin.setOnClickListener {
            validateLogin(
                (binding.etUsername.text.toString()),
                (binding.etPassword.text.toString())
            )
        }

    }

    fun validateLogin(userInput: String, passInput: String) {
        val number = Regex("\\d")
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
            ) -> errorOutput(R.string.val_log6)
            else -> {
                val validasiAkun = DataAkun.credentials[userInput] == passInput
                if (validasiAkun) {
                    sucessOutput(R.string.log_success)
                } else {
                    errorOutput(R.string.val_log7)
                }
            }
        }
    }

    fun errorOutput(errorId: Int) {
            toast = Toast.makeText(context, getString(errorId), Toast.LENGTH_SHORT)
            toast?.show()
    }

    fun sucessOutput(textId: Int) {
        activity?.let {
            Snackbar.make(
                binding.clSnack,
                getString(textId),
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }
}