package com.example.minipratyaksh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.minipratyaksh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels{ MainActivityViewModelFactory(application) }
    private lateinit var _binding :ActivityMainBinding
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.idLaptopChargeSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                viewModel.startTimer()
                Toast.makeText(this, "Timer Start", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.cancelTimer()
                Toast.makeText(this, "Timer Stopped", Toast.LENGTH_SHORT).show()
            }
        }


    }

}