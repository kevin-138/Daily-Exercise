package com.kevin.dailyexercise

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tlMain)
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

        setupFragment(0)
    }

    fun setupFragment(fragSelected: Int) {
        when (fragSelected) {
            0 -> {
                val lgnFragment = LoginFragment()
                val lgnFragmentManager = supportFragmentManager
                lgnFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayoutMain, lgnFragment, LoginFragment::class.java.simpleName)
                    commit()
                }
            }
            1 -> {
                val alamatFragment = AlamatFragment()
                val alamatFragmenttManager = supportFragmentManager
                alamatFragmenttManager.beginTransaction().apply {
                    replace(
                        R.id.frameLayoutMain,
                        alamatFragment,
                        LoginFragment::class.java.simpleName
                    )
                    commit()
                }
            }

        }
    }
}