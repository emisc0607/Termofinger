package com.example.termofinger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.termofinger.databinding.FragmentDataBinding

class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.rbSex1.isChecked = true
        binding.bSave.setOnClickListener {
            context?.let {
                Toast.makeText(context, "Hola Termofinger", Toast.LENGTH_SHORT).show()
            }
        }
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbSex1 -> {
                    binding.etMenstruacion.visibility = View.GONE
                    binding.ilMenstruacion.visibility = View.GONE
                    true
                }
                R.id.rbSex2 -> {
                    binding.etMenstruacion.visibility = View.VISIBLE
                    binding.ilMenstruacion.visibility = View.VISIBLE
                    true
                }
                else -> false
            }
        }
        binding.etTimePicker.setOnClickListener {
            showTimePickerDialog()
        }
    }
//TimePicker
    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(childFragmentManager, "time")
    }

    private fun onTimeSelected(time: String) {
        binding.etTimePicker.setText("$time")
    }
}