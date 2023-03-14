package com.kevin.dailyexercise.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kevin.dailyexercise.MainActivity
import com.kevin.dailyexercise.data_model.DataAlamatUser
import com.kevin.dailyexercise.R
import com.kevin.dailyexercise.databinding.FragmentInputAlamatBinding

class inputAlamatFragment : Fragment() {
    lateinit var binding: FragmentInputAlamatBinding
    var dataUser: DataAlamatUser? = null
    var position: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputAlamatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()

        initData()

        binding.btnSave.setOnClickListener {
            validateInput()
        }
    }

    fun validateInput() {
        when {
            binding.etAlamat.text!!.isBlank() -> errorOutput(R.string.input_val1)
            binding.etDetail.text!!.isBlank() -> errorOutput(R.string.input_val2)
            binding.etLabel.text!!.isBlank() -> errorOutput(R.string.input_val3)
            binding.etNama.text!!.isBlank() -> errorOutput(R.string.input_val4)
            binding.etNoHp.text!!.isBlank() -> errorOutput(R.string.input_val5)
            else -> {
                saveData()
            }
        }
    }

    fun initData() {
        if (dataUser != null) {
            binding.etAlamat.setText(dataUser?.dataAlamat)
            binding.etDetail.setText(dataUser?.dataDetail)
            binding.etLabel.setText(dataUser?.dataLabel)
            binding.etNama.setText(dataUser?.dataPenerima)
            binding.etNoHp.setText(dataUser?.dataHandphone)
        }
    }

    fun errorOutput(errorType: Int) {
        Toast.makeText(context, getString(errorType), Toast.LENGTH_SHORT).show()
    }

    fun initArgument() {
        val argument = this.arguments
        if (argument != null) {
            dataUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                argument.getParcelable("userAlamat", DataAlamatUser::class.java)
            } else {
                argument.getParcelable("userAlamat")
            }
            position = argument.getInt("itemPosition")
        }
    }

    fun saveData() {
        val accData = DataAlamatUser(
            binding.etAlamat.text.toString(),
            binding.etDetail.text.toString(),
            binding.etLabel.text.toString(),
            binding.etNama.text.toString(),
            binding.etNoHp.text.toString(),
        )
        val inputFragment = AlamatFragment()
        val bundle = Bundle()
        val inputFragmentManager = parentFragmentManager
        bundle.putParcelable("UserAlamat", accData)
        bundle.putInt("ItemPosition", position!!)
        inputFragment.arguments = bundle
//        (requireActivity() as MainActivity).setupFragment(1)
        inputFragmentManager.beginTransaction().apply {
            replace(
                R.id.RelativeLayoutContainer,
                inputFragment,
                AlamatFragment::class.java.simpleName
            )
            commit()
        }
    }
}