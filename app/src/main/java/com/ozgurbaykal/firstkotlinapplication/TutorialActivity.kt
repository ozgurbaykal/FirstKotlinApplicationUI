package com.ozgurbaykal.firstkotlinapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ozgurbaykal.firstkotlinapplication.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity(), TutorialPageListener {
    private val TAG = "_MainActivity"

    private lateinit var viewPager : ViewPager2

    private lateinit var binding: ActivityTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        viewPager = binding.tutorialViewPager
        viewPager.adapter = TutorialPagerAdapter(this)

    }

    override fun onNextPage() {
        val currentItem = viewPager.currentItem
        if (currentItem < 2) {
            // Move to next page
            viewPager.currentItem = currentItem + 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
class TutorialPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TutorialPage1()
            1 -> TutorialPage2()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}