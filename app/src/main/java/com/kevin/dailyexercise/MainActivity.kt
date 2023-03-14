package com.kevin.dailyexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.kevin.dailyexercise.databinding.ActivityMainBinding
import com.kevin.dailyexercise.fragment.AlamatFragment
import com.kevin.dailyexercise.fragment.LoginFragment
import com.kevin.dailyexercise.fragment.inputAlamatFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTab()

        setupFragment(0)
    }

    private fun setupTab(){
        val tabLayout = binding.tlMain
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when {
                    tab?.position == 0 -> {
                        setupFragment(0)
                    }
                    tab?.position == 1 -> {
                        setupFragment(1)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Write code to handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Write code to handle tab reselect
            }

        })
    }

    fun setupFragment(fragSelected: Int) {
        when (fragSelected) {
            0 -> {
                val lgnFragment = LoginFragment()
                val lgnFragmentManager = supportFragmentManager
                lgnFragmentManager.beginTransaction().apply {
                    replace(binding.frameLayoutMain.id, lgnFragment, LoginFragment::class.java.simpleName)
                    commit()
                }
            }
            1 -> {
                val alamatFragment = AlamatFragment()
                val alamatFragmenttManager = supportFragmentManager
                alamatFragmenttManager.beginTransaction().apply {
                    replace(binding.frameLayoutMain.id, alamatFragment, LoginFragment::class.java.simpleName)
                    commit()
                }
            }
        }
    }
}