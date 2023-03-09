package com.kevin.dailyexercise

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlamatFragment : Fragment(),DataAlamatAdapter.interfaceView {
    lateinit var recyclerViewAlamat: RecyclerView
    lateinit var dataAdapter: DataAlamatAdapter
    lateinit var dataAlamatList: MutableList<DataAlamatUser>
    var dataUser:DataAlamatUser? = null
    var position:Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alamat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        dataAdapter = DataAlamatAdapter(dataAlamatList)

        receiver()

        dataAdapter.itemListener = this
        recyclerViewAlamat = view.findViewById(R.id.listAlamatPengguna)
        recyclerViewAlamat.layoutManager = LinearLayoutManager(context)
        recyclerViewAlamat.adapter = dataAdapter
    }

    override fun ubahItemRecyclerListner(position: Int) {
        val inputFragment = inputAlamatFragment()
        val bundle = Bundle()
        val inputFragmentManager = parentFragmentManager
        bundle.putParcelable("userAlamat",dataAlamatList[position])
        bundle.putInt("itemPosition",position)
        inputFragment.arguments = bundle
        inputFragmentManager.beginTransaction().apply {
            replace(R.id.RelativeLayoutContainer, inputFragment, inputAlamatFragment::class.java.simpleName)
            commit()
        }
    }

    override fun deleteItemRecyclerListner(position: Int) {
        dataAlamatList.removeAt(position)
        dataAdapter.notifyDataSetChanged()
    }

    fun initData(){
        dataAlamatList = mutableListOf<DataAlamatUser>(
            DataAlamatUser("Jl. tanjung duren timur 6 no 199", "3 rumah disebelah indomaret", "Rumah", "Kevin Goutama", "o89622373896"),
            DataAlamatUser("Jl. manggis XII no 12", "rumah ke 2 dari pintu masuk gang", "Rumah", "Aaron hartono", "08215637138"),
            DataAlamatUser("Jl. duku segar VI no 4", "Dekat martabak alay", "Rumah", "Jason Kenaz", "0875649575256"),
            DataAlamatUser("Jl. kyai pata No 5", "rumah paling besar", "Rumah", "Abrian Ishak", "087699865652"),
            DataAlamatUser("Jl. duku raya no 13", "dekap ayam gepuk pak gembus", "Rumah", "Marcell Aryadjie", "081275554866"),
            DataAlamatUser("Jl thamrin raya IV no 233", "sebelah monas", "Rumah", "Rafael timotius", "087554441122"),
            DataAlamatUser("Jl. Teuku umar no 15", "rumah berwarna abu abu", "Rumah", "Jaqueline season", "089997665666"),
            DataAlamatUser("Jl. tanjung duren raya no 83", "Dekat mixue", "Ruko", "Regaldy winata", "087755545121"),
            DataAlamatUser("Jl. kemanggisan raya no 16", "Dekat Universitas binus", "Rumah", "agnes chang", "087555459888"),
            DataAlamatUser("Jl. tanjung pinang no 17", "deket pohon pisang", "Rumah", "Tom jones", "089965555233"),
        )
    }

    fun receiver() {
        val argument = this.arguments
        if (argument != null){
            dataUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                argument.getParcelable("UserAlamat", DataAlamatUser::class.java)
            } else {
                argument.getParcelable("UserAlamat")
            }
            position = argument.getInt("ItemPosition")
            dataAlamatList[position!!] = dataUser!!
            dataAdapter.notifyItemChanged(position!!)
        }
    }

}