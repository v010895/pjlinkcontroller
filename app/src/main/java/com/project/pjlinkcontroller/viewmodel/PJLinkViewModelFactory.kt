package com.project.pjlinkcontroller.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PJLinkViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PJLinkViewModel::class.java))
        {
            return PJLinkViewModel() as T
        }
        throw IllegalArgumentException("viewmodelFactory: Invalid class.")
    }
}