package com.kevin.dailyexercise

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class inputAlamatFragment : Fragment() {
    lateinit var edTAlamat: TextInputEditText
    lateinit var edTDetail: TextInputEditText
    lateinit var edTLabel: TextInputEditText
    lateinit var edTNama: TextInputEditText
    lateinit var edTNoHp: TextInputEditText

    lateinit var teVAlamat: TextView
    lateinit var teVDetail: TextView
    lateinit var teVLabel: TextView
    lateinit var teVNama: TextView
    lateinit var teVNoHp: TextView
    lateinit var saveBtn: Button
    var dataUser:DataAlamatUser? = null
    var position:Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_alamat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edTAlamat = view.findViewById(R.id.etAlamat)
        edTDetail = view.findViewById(R.id.etDetail)
        edTLabel = view.findViewById(R.id.etLabel)
        edTNama = view.findViewById(R.id.etNama)
        edTNoHp = view.findViewById(R.id.etNoHp)

        teVAlamat = view.findViewById(R.id.tvAlamat)
        teVDetail = view.findViewById(R.id.tvDetail)
        teVLabel = view.findViewById(R.id.tvLabelList)
        teVNama = view.findViewById(R.id.tvNama)
        teVNoHp = view.findViewById(R.id.tvNoHp)

        saveBtn = view.findViewById(R.id.btnSave)
        val argument = this.arguments
        if (argument != null){
            dataUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                argument.getParcelable("userAlamat", DataAlamatUser::class.java)
            } else {
                argument.getParcelable("userAlamat")
            }
            position = argument.getInt("itemPosition")
        }

        initData()

        saveBtn.setOnClickListener {
            validateInput()
        }
    }

    fun validateInput() {
            when{
                edTAlamat.text!!.isBlank() -> edTAlamat.setError(getString(R.string.input_val1))
                edTDetail.text!!.isBlank() -> edTDetail.setError(getString(R.string.input_val2))
                edTLabel.text!!.isBlank() -> edTLabel.setError(getString(R.string.input_val3))
                edTNama.text!!.isBlank() -> edTNama.setError(getString(R.string.input_val4))
                edTNoHp.text!!.isBlank() -> edTNoHp.setError(getString(R.string.input_val5))
                else -> {
                    val accData = DataAlamatUser(
                        edTAlamat.text.toString(),
                        edTDetail.text.toString(),
                        edTLabel.text.toString(),
                        edTNama.text.toString(),
                        edTNoHp.text.toString(),
                    )
                    val inputFragment = AlamatFragment()
                    val bundle = Bundle()
                    val inputFragmentManager = parentFragmentManager
                    bundle.putParcelable("UserAlamat",accData)
                    bundle.putInt("ItemPosition",position!!)
                    inputFragment.arguments = bundle
                    inputFragmentManager.beginTransaction().apply {
                        replace(R.id.RelativeLayoutContainer, inputFragment, AlamatFragment::class.java.simpleName)
                        commit()
                    }
//                    getFragmentManager()?.beginTransaction()?.remove(this)?.commitAllowingStateLoss();
                }
            }
    }

    fun initData(){
        if (dataUser != null){
            edTAlamat.setText(dataUser?.dataAlamat)
            edTDetail.setText(dataUser?.dataDetail)
            edTLabel.setText(dataUser?.dataLabel)
            edTNama.setText(dataUser?.dataPenerima)
            edTNoHp.setText(dataUser?.dataHandphone)
        }
    }
}