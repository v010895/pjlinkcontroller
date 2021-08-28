package com.project.pjlinkcontroller.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.project.pjlinkcontroller.databinding.FragmentSearchBinding
import com.project.pjlinkcontroller.viewmodel.PJLinkViewModel

class SearchFragment: Fragment() {
    private lateinit var binding:FragmentSearchBinding
    private val pjLinkViewModel:PJLinkViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pjLinkViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            binding.searchTxt.text = it;
        })
        setComponent();
    }

    private fun setComponent(){
        binding.startSearch.setOnClickListener{
            pjLinkViewModel.startSearch()
        }
    }
}