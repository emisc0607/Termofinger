package com.example.termofinger

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.termofinger.databinding.FragmentTemperatureBinding


class TemperatureFragment : Fragment() {
    private lateinit var binding: FragmentTemperatureBinding
    private var timeSelected = 0
    private var timeCountDown: CountDownTimer? = null
    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var isStart = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTemperatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bPlay.setOnClickListener {
            setTime()
            binding.toogleButtonTemperature.clearChecked()
        }
        binding.bPause.setOnClickListener {
            isStart = false
            startTimerSetup()
            //binding.toogleButtonTemperature.clearChecked()
        }
        binding.bRestart.setOnClickListener {
            resetTime()
            binding.toogleButtonTemperature.clearChecked()
        }
    }
    private fun resetTime(){
        if (timeCountDown!=null){
            timeCountDown!!.cancel()
            timeProgress = 0
            timeSelected = 0
            pauseOffSet = 0
            timeCountDown =null
            binding.progressTimer.progress = 0
            binding.tvTimeLeft.text = 0.toString()
            isStart = true
        }
    }
    private fun timePause(){
        if (timeCountDown!=null){
            timeCountDown!!.cancel()
        }
    }
    private fun startTimerSetup(){
        if (timeSelected>timeProgress){
            if(isStart){
                startTimer(pauseOffSet)
                isStart = false
            }
            else{
                isStart = true
                timePause()
            }
        }
        else{
            Toast.makeText(requireContext(), "Enter time", Toast.LENGTH_SHORT).show()
        }
    }
    private fun startTimer(pauseOffsetL: Long) {
        binding.progressTimer.progress = timeProgress
        timeCountDown = object : CountDownTimer(
            ((timeSelected * 1000).toLong() - pauseOffsetL * 1000), 1000
        ) {
            override fun onTick(millisUntilFinished: Long) {
                timeProgress++
                pauseOffSet = timeSelected.toLong() - millisUntilFinished/1000
                binding.progressTimer.progress = timeSelected - timeProgress
                binding.tvTimeLeft.text = (timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                resetTime()
                Toast.makeText(requireContext(), "Muestreo finalizado", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun setTime() {
        binding.etTimeAdder.text?.let {
            if (it.isEmpty()) {
                Toast.makeText(requireContext(), "Enter duaration time", Toast.LENGTH_SHORT).show()
            } else {
                resetTime()
                binding.tvTimeLeft.text = binding.etTimeAdder.text
                timeSelected = binding.etTimeAdder.text.toString().toInt()
                binding.progressTimer.max = timeSelected
                isStart = true
                startTimerSetup()
            }
        }
    }
}