package com.example.termofinger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.termofinger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mActiveFragment: Fragment
    private lateinit var mFragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        mFragmentManager = supportFragmentManager
        val homeFragment = HomeFragment()
        val dataFragment = DataFragment()
        val temperatureFragment = TemperatureFragment()

        mActiveFragment = HomeFragment()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, temperatureFragment, TemperatureFragment::class.java.name)
            .hide(temperatureFragment)
            .commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, dataFragment, DataFragment::class.java.name)
            .hide(dataFragment)
            .commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, homeFragment, HomeFragment::class.java.name).commit()

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    binding.imageView.visibility = View.VISIBLE
                    binding.tvWelcome.visibility = View.VISIBLE
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(homeFragment)
                        .commit()
                    mActiveFragment = homeFragment
                    true
                }
                R.id.action_temp -> {
                    binding.imageView.visibility = View.GONE
                    binding.tvWelcome.visibility = View.GONE
                    mFragmentManager.beginTransaction().hide(mActiveFragment)
                        .show(temperatureFragment)
                        .commit()
                    mActiveFragment = temperatureFragment
                    true
                }
                R.id.action_data -> {
                    binding.imageView.visibility = View.GONE
                    binding.tvWelcome.visibility = View.GONE
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(dataFragment)
                        .commit()
                    mActiveFragment = dataFragment
                    true
                }
                else -> false
            }
        }
    }

}