package com.project.pjlinkcontroller.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.pjlinkcontroller.constant.TAB_SIZE
import com.project.pjlinkcontroller.fragments.ControlFragment
import com.project.pjlinkcontroller.fragments.SearchFragment
import java.lang.RuntimeException

class ViewPageAdapter(fragmentManager: FragmentManager, lifeCycle:Lifecycle):FragmentStateAdapter(fragmentManager,lifeCycle) {
    private val fragmentMap = hashMapOf<Int,Fragment>();

    private fun getFragment(position:Int) : Fragment{
        return when(position)
        {
            0 -> ControlFragment()
            1 -> SearchFragment()
            else -> throw RuntimeException()
        }
    }

    override fun getItemCount(): Int {
        return TAB_SIZE
    }

    override fun createFragment(position: Int): Fragment {
       val fragment = getFragment(position)
        fragmentMap[position] = fragment
        return fragment
    }

}