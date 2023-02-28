package com.example.termofinger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.termofinger.databinding.FragmentTemperatureBinding


class TemperatureFragment : Fragment() {
    private lateinit var binding: FragmentTemperatureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTemperatureBinding.inflate(inflater, container, false)
        return binding.root
    }

}