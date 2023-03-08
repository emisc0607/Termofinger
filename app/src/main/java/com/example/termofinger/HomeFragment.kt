package com.example.termofinger

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.termofinger.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val REQUEST_CONNECTION_CODE = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bCheck.setOnClickListener {
            checkPermissions()
        }
    }

    private fun checkPermissions() {
        val p1 = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.BLUETOOTH
        )
        val p2 = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.BLUETOOTH_CONNECT
        )
        val p3 = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.BLUETOOTH_SCAN
        )
        val p4 = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.BLUETOOTH_ADMIN
        )
        val p5 = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.NEARBY_WIFI_DEVICES
        )
        if (p1 != PackageManager.PERMISSION_GRANTED ||
            p2 != PackageManager.PERMISSION_GRANTED ||
            p3 != PackageManager.PERMISSION_GRANTED ||
            p4 != PackageManager.PERMISSION_GRANTED ||
            p5 != PackageManager.PERMISSION_GRANTED
        ) {
            //Toast.makeText(requireContext(), "Permisos rechazados", Toast.LENGTH_SHORT).show()
            requestConnectionPermissions()
        } else {
            Toast.makeText(requireContext(), "Permisos aceptados", Toast.LENGTH_SHORT).show()
            binding.tvCheck.text = "Si se puede"
        }
    }

    private fun requestConnectionPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.BLUETOOTH,
                    android.Manifest.permission.BLUETOOTH_CONNECT,
                    android.Manifest.permission.BLUETOOTH_SCAN,
                    android.Manifest.permission.BLUETOOTH_ADMIN,
                    android.Manifest.permission.NEARBY_WIFI_DEVICES
                ).toString()
            )
        ) {
            Toast.makeText(
                requireContext(),
                "Permisos rechazados, vaya a Configuración y aceptelos",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    android.Manifest.permission.BLUETOOTH,
                    android.Manifest.permission.BLUETOOTH_CONNECT,
                    android.Manifest.permission.BLUETOOTH_SCAN,
                    android.Manifest.permission.BLUETOOTH_ADMIN,
                    android.Manifest.permission.NEARBY_WIFI_DEVICES
                ), REQUEST_CONNECTION_CODE
            )
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CONNECTION_CODE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                grantResults[1] == PackageManager.PERMISSION_GRANTED ||
                grantResults[2] == PackageManager.PERMISSION_GRANTED ||
                grantResults[3] == PackageManager.PERMISSION_GRANTED ||
                grantResults[4] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(requireContext(), "Permisos aceptados", Toast.LENGTH_SHORT).show()
                binding.tvCheck.text = "Si se puede"
            } else {
                Toast.makeText(requireContext(), "Permisos rechazados", Toast.LENGTH_SHORT).show()
                binding.tvCheck.text = "No se puede"
            }
        }
    }
}