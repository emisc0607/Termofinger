package com.example.termofinger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        binding.bSave.setOnClickListener {
            context?.let {
                Toast.makeText(context, "Hello Termofinger", Toast.LENGTH_SHORT).show()
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
        binding.etMenstruacion.setOnClickListener {
            showDate(0)
        }
        binding.etDateFiebre.setOnClickListener {
            showDate(1)
        }
        binding.radioGroup2.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbFever1 -> {
                    binding.etDateFiebre.visibility = View.GONE
                    binding.ilDateFiebre.visibility = View.GONE
                    true
                }
                R.id.rbFever2 -> {
                    binding.etDateFiebre.visibility = View.VISIBLE
                    binding.ilDateFiebre.visibility = View.VISIBLE
                    true
                }
                else -> false
            }
        }
    }

    //TimePicker
    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(childFragmentManager, "time")
    }

    private fun onTimeSelected(time: String) {
        binding.etTimePicker.setText(time)
    }

    private fun showDate(pickerId: Int) {
        val datePicker =
            DatePickerFragment { day, month, year -> onDaySelected(day, month, year, pickerId) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    private fun onDaySelected(day: Int, month: Int, year: Int, pickerId: Int) {
        val date = "$day/${month + 1}/$year"
        when (pickerId) {
            0 -> {
                binding.etMenstruacion.setText(date)
            }
            1 -> {
                binding.etDateFiebre.setText(date)
            }
            else -> false
        }
    }
}