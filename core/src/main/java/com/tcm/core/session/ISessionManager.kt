package com.tcm.core.session

import kotlinx.coroutines.flow.StateFlow

interface ISessionManager {
    fun saveTheme(theme: Int)
    fun getTheme(): StateFlow<Int>
}