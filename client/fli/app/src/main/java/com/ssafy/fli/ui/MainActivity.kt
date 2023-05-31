package com.ssafy.fli.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ssafy.fli.R
import com.ssafy.fli.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMainUi()

    }

    private fun setMainUi() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.containerMain)?.findNavController()

        navController?.let {
            binding.navigationMain.setupWithNavController(it)
        }

        navController?.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id != binding.navigationMain.selectedItemId) {
                
                controller.backQueue.asReversed().drop(1).forEach { entry ->
                    binding.navigationMain.menu.forEach { item ->
                        if (entry.destination.id == item.itemId) {
                            item.isChecked = true
                            return@addOnDestinationChangedListener
                        }
                    }
                }
            }
        }
    }



}