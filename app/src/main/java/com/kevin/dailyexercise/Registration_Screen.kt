package com.kevin.dailyexercise

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import java.util.*

class Registration_Screen : AppCompatActivity() {
    private lateinit var nama: EditText
    private lateinit var email: EditText
    private lateinit var alamat: EditText
    private lateinit var registerBtn: Button
    private lateinit var output: TextView
    private lateinit var genderRg: RadioGroup
    private lateinit var maleRd: RadioButton
    private lateinit var femaleRd: RadioButton
    private lateinit var dateBt: Button
    private lateinit var dateTv: TextView
    lateinit var accDATA: AccountInfo
    lateinit var pendidikan: Spinner
    var pickedDay = 0
    var pickedMonth = 0
    var pickedYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)
        nama = findViewById(R.id.etNama)
        email = findViewById(R.id.etEmail)
        genderRg = findViewById(R.id.rgGender)
        alamat = findViewById(R.id.etAlamat)
        output = findViewById(R.id.tvOutput)
        registerBtn = findViewById(R.id.btRegistration)
        maleRd = findViewById(R.id.rbmale)
        femaleRd = findViewById(R.id.rbfemale)
        dateBt = findViewById(R.id.btDate)
        dateTv = findViewById(R.id.tvDate)
        pendidikan = findViewById(R.id.spPendidikan)

        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        var datePicker = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->

                pickedDay = dayOfMonth
                pickedMonth = monthOfYear
                pickedYear = year

                val dateFinal = "$dayOfMonth - $monthOfYear - $year"
                dateTv.text = dateFinal

            },
            year,
            month,
            day
        )

        val stringArray: ArrayList<String> =  resources.getStringArray(R.array.pendidikan).toList() as ArrayList<String>

        val userSavedData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.intent.getParcelableExtra("user_data",AccountInfo::class.java)
        } else {
            this.intent.getParcelableExtra("user_data")
        }

        if (userSavedData != null) {
            nama.setText(userSavedData.accountName)
            email.setText(userSavedData.accountEmail)
            alamat.setText(userSavedData.accountAlamat)
            if (userSavedData.accountGender == R.id.rbmale){
                genderRg.check(R.id.rbmale)
            } else {
                genderRg.check(R.id.rbfemale)
            }
            datePicker.updateDate(userSavedData.accountYear, userSavedData.accountMonth, userSavedData.accountDay);
            dateTv.setText("${userSavedData.accountDay} - ${userSavedData.accountMonth} - ${userSavedData.accountYear}")
            pendidikan.setSelection(stringArray.indexOf(userSavedData.accountStudy))
        }

        dateBt.setOnClickListener {
            datePicker.show()
        }

        registerBtn.setOnClickListener {
            validRegistration()
        }

    }

    fun validRegistration() {
        val number = Regex("[0-9]")
        val symbols = Regex("[\\p{P}\\p{S}]")
        when {
            nama.text.isBlank() -> output.text = getString(R.string.val_reg1)
            number.containsMatchIn(nama.text) -> output.text =
                getString(R.string.val_reg2)
            symbols.containsMatchIn(nama.text) -> output.text =
                getString(R.string.val_reg3)

            email.text.isBlank() -> output.text = getString(R.string.val_reg4)
            !emailValidate(email.text.toString()) -> output.text =
                getString(R.string.val_reg5)

            !maleRd.isChecked && !femaleRd.isChecked -> output.text =
                getString(R.string.val_reg6)

            pickedDay == 0 || pickedYear == 0 || pickedMonth == 0 -> output.text =
                getString(R.string.val_reg7)

            pickedYear in 2020..2023 -> output.text =
                getString(R.string.val_reg8)

            alamat.text.isBlank() -> output.text = getString(R.string.val_reg9)

            else -> {
                val gender = genderRg.checkedRadioButtonId
                val pickedPendidikan = pendidikan.selectedItem.toString()
                accDATA = AccountInfo(nama.text.toString(),email.text.toString(),gender,pickedDay,pickedMonth,pickedYear,alamat.text.toString(),pickedPendidikan)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user_data", accDATA)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun emailValidate(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}