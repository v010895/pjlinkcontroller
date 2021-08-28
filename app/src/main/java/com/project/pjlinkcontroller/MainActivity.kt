package com.project.pjlinkcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.project.pjlinkcontroller.adapter.ViewPageAdapter
import com.project.pjlinkcontroller.databinding.ActivityMainBinding
import com.project.pjlinkcontroller.viewmodel.PJLinkViewModel
import com.project.pjlinkcontroller.viewmodel.PJLinkViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding;
    private lateinit var pjLinkViewModel:PJLinkViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = PJLinkViewModelFactory()
        pjLinkViewModel = ViewModelProvider(this,factory).get(PJLinkViewModel::class.java)
        initAdapter();
    }

    private fun initAdapter(){
        val viewPageAdapter = ViewPageAdapter(supportFragmentManager,lifecycle);
        binding.fragmentHolder.adapter = viewPageAdapter
        binding.fragmentHolder.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.mainTabHolder.getTabAt(position)?.select();
            }
        })

    }
}