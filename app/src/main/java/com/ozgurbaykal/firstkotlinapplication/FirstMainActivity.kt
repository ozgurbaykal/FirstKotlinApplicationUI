package com.ozgurbaykal.firstkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ozgurbaykal.firstkotlinapplication.databinding.ActivityFirstMainBinding

class FirstMainActivity : AppCompatActivity() {

    private val TAG = "_FirstMainActivity"

    private lateinit var binding: ActivityFirstMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LoginFragment>(R.id.login_fragment_view)
            }
        }
    }
}