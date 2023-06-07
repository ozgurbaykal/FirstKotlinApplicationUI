package com.ozgurbaykal.firstkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozgurbaykal.firstkotlinapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "_MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}