package com.kevin.dailyexercise

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toolbar

class InputAlamat : AppCompatActivity() {

    lateinit var edTAlamat: com.google.android.material.textfield.TextInputEditText
    lateinit var edTDetail: com.google.android.material.textfield.TextInputEditText
    lateinit var edTLabel: com.google.android.material.textfield.TextInputEditText
    lateinit var edTNama: com.google.android.material.textfield.TextInputEditText
    lateinit var edTNoHp: com.google.android.material.textfield.TextInputEditText

    lateinit var teVAlamat: TextView
    lateinit var teVDetail: TextView
    lateinit var teVLabel: TextView
    lateinit var teVNama: TextView
    lateinit var teVNoHp: TextView

    lateinit var switchAlamat: androidx.appcompat.widget.SwitchCompat
    lateinit var accData: DataAlamatUser

    lateinit var saveBtn: Button
    var userData: DataAlamatUser? = null
    var positionCheck:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_alamat)

        edTAlamat = findViewById(R.id.etAlamat)
        edTDetail = findViewById(R.id.etDetail)
        edTLabel = findViewById(R.id.etLabel)
        edTNama = findViewById(R.id.etNama)
        edTNoHp = findViewById(R.id.etNoHp)

        teVAlamat = findViewById(R.id.tvAlamat)
        teVDetail = findViewById(R.id.tvDetail)
        teVLabel = findViewById(R.id.tvLabelList)
        teVNama = findViewById(R.id.tvNama)
        teVNoHp = findViewById(R.id.tvNoHp)

        switchAlamat = findViewById(R.id.swAlamat)
        saveBtn = findViewById(R.id.btnSave)

//        var toolbar: androidx.appcompat.widget.Toolbar? = findViewById(R.id.Toolbar)
//        setSupportActionBar(toolbar)
//        if (supportActionBar != null) {
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.setNavigationOnClickListener{
//                onBackPressed()
//            }
//        }

        positionCheck = this.intent.getIntExtra("Position", -1)

        initData()

        saveBtn.setOnClickListener {
            validateInput()
        }

    }

    fun initData(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        userData = this.intent.getParcelableExtra("user_data", DataAlamatUser::class.java)
    } else {
        userData = this.intent.getParcelableExtra("user_data")
    }
        if (userData != null){
            edTAlamat.setText(userData?.dataAlamat)
            edTDetail.setText(userData?.dataDetail)
            edTLabel.setText(userData?.dataLabel)
            edTNama.setText(userData?.dataPenerima)
            edTNoHp.setText(userData?.dataHandphone)
            switchAlamat.isChecked=userData?.dataSwitch?:false
        }
    }

    fun validateInput() {
        val symbols = Regex("[\\p{P}\\p{S}]")
        val number = Regex("[0-9]")
        when {
            edTAlamat.text!!.isBlank() -> edTAlamat.setError(getString(R.string.input_val1))
            edTDetail.text!!.isBlank() -> edTDetail.setError(getString(R.string.input_val2))
            edTLabel.text!!.isBlank() -> edTLabel.setError(getString(R.string.input_val3))
            edTNama.text!!.isBlank() -> edTNama.setError(getString(R.string.input_val4))
            edTNoHp.text!!.isBlank() -> edTNoHp.setError(getString(R.string.input_val5))
//            symbols.containsMatchIn(edTNama.text) -> edTNama.setError(getString(R.string.input_val6))
//            number.containsMatchIn(edTNama.text) -> edTNama.setError(getString(R.string.input_val9))
//            !(edTNoHp.text.toString()
//                .matches(number)) -> edTNoHp.setError(getString(R.string.input_val7))
//            edTNoHp.text.length !in 12..13 -> edTNoHp.setError(getString(R.string.input_val8))
            else -> {
                accData = DataAlamatUser(
                    edTAlamat.text.toString(),
                    edTDetail.text.toString(),
                    edTLabel.text.toString(),
                    edTNama.text.toString(),
                    edTNoHp.text.toString(),
                    switchAlamat.isChecked
                )
                val intent = Intent(this, ListingAlamat::class.java)
                intent.putExtra("user_data", accData)
                intent.putExtra("Position",positionCheck)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}