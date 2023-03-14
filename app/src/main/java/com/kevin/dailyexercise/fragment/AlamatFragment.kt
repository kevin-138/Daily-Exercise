package com.kevin.dailyexercise.fragment

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.dailyexercise.adapter.DataAlamatAdapter
import com.kevin.dailyexercise.data_model.DataAlamatUser
import com.kevin.dailyexercise.R
import com.kevin.dailyexercise.data.DataAlamat
import com.kevin.dailyexercise.databinding.FragmentAlamatBinding

class AlamatFragment : Fragment(), DataAlamatAdapter.InterfaceView {
    lateinit var binding: FragmentAlamatBinding
    lateinit var dataAdapter: DataAlamatAdapter
    var dataAlamatList = mutableListOf<DataAlamatUser>()
    var dataUser: DataAlamatUser? = null
    var position: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlamatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        initAdapter()

        receiver()

    }

    fun deleteConfirmation(position: Int) {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setMessage(getString(R.string.confirm))
        dialogBuilder.setCancelable(false)
        dialogBuilder.setPositiveButton(getString(R.string.pb1)) { dialog, which ->
            dataAlamatList.removeAt(position)
            dataAdapter.notifyItemRemoved(position)
            dataAdapter.notifyItemRangeChanged(position, dataAdapter.itemCount)
        }
        dialogBuilder.setNegativeButton(getString(R.string.nb1)) { dialog, which ->
            dialog.cancel()
        }
        val deleteDialog = dialogBuilder.create()
        deleteDialog.setTitle(getString(R.string.dltwrn))
        deleteDialog.setIcon(R.drawable.baseline_warning_amber_24)
        deleteDialog.show()
    }

    override fun ubahItemRecyclerListner(position: Int) {
        val inputFragment = inputAlamatFragment()
        val bundle = Bundle()
        val inputFragmentManager = parentFragmentManager
        bundle.putParcelable("userAlamat", dataAlamatList[position])
        bundle.putInt("itemPosition", position)
        inputFragment.arguments = bundle
        inputFragmentManager.beginTransaction().apply {
            replace(
                binding.RelativeLayoutContainer.id,
                inputFragment,
                inputAlamatFragment::class.java.simpleName
            )
            commit()
        }
    }

    override fun deleteItemRecyclerListner(position: Int) {
        deleteConfirmation(position)
    }

    fun initData() {
        dataAlamatList.clear()
        dataAlamatList.addAll(DataAlamat.AlamatList)
    }

    fun initAdapter() {
        dataAdapter = DataAlamatAdapter(dataAlamatList)
        dataAdapter.itemListener = this
        binding.listAlamatPengguna.layoutManager = LinearLayoutManager(context)
        binding.listAlamatPengguna.adapter = dataAdapter
    }

    fun receiver() {
        val argument = this.arguments
        if (argument != null) {
            dataUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                argument.getParcelable("UserAlamat", DataAlamatUser::class.java)
            } else {
                argument.getParcelable("UserAlamat")
            }
            position = argument.getInt("ItemPosition")
            dataAlamatList[position!!] = dataUser!!
            dataAdapter.notifyItemChanged(position!!)
            Toast.makeText(context,"Data berhasil diubah!",Toast.LENGTH_SHORT).show()
        }
    }

}