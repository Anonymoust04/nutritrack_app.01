package com.fit2081.Tee_34570403.nutritrack.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

// Authentication Manager
object AuthManager {
    val _userId: MutableState<Int?> = mutableStateOf(null)
    val _appAdmin: MutableState<Boolean?> = mutableStateOf(null)

    fun login(userId: Int) {
        _userId.value = userId
    }

    fun logout() {
        _userId.value = null
    }

    fun getUserId(): Int? {
        return _userId.value
    }
}
