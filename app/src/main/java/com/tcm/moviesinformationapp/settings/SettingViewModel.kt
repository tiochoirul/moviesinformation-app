package com.tcm.moviesinformationapp.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tcm.core.session.SessionManager

class SettingViewModel(private val sessionManager: SessionManager) : ViewModel() {

    val theme: LiveData<Int> = sessionManager.getTheme().asLiveData()

    fun saveTheme(theme: Int) = sessionManager.saveTheme(theme)
}